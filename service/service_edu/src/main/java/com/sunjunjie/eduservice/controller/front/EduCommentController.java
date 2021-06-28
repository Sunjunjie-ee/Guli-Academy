package com.sunjunjie.eduservice.controller.front;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunjunjie.commonutils.JwtUtils;
import com.sunjunjie.commonutils.R;
import com.sunjunjie.eduservice.bean.EduComment;
import com.sunjunjie.eduservice.bean.EduCourse;
import com.sunjunjie.eduservice.bean.vo.UcenterMemberVo;
import com.sunjunjie.eduservice.client.UcenterClient;
import com.sunjunjie.eduservice.service.EduCommentService;
import com.sunjunjie.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-25
 */
@RestController
@RequestMapping("/eduservice/comment")
public class EduCommentController {
    @Autowired
    private EduCommentService commentService;

    @Autowired
    private UcenterClient ucenterClient;

    @PostMapping("addComment")
    public R addComment(@RequestBody EduComment eduComment,
                        HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberId)){
            throw new GuliException(20001, "请先登录");
        }
        eduComment.setMemberId(memberId);
        UcenterMemberVo memberInfo = ucenterClient.getMemberInfoById(memberId);
        eduComment.setNickname(memberInfo.getNickname());
        eduComment.setAvatar(memberInfo.getAvatar());
        commentService.save(eduComment);

        return R.success();
    }
    //分页查询
    @GetMapping("pageComment/{page}/{limit}")
    public R pageListComment(@PathVariable long page,
                             @PathVariable long limit){
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        Page<EduComment> pageComment = new Page<>(page, limit);
        commentService.page(pageComment, wrapper);
        List<EduComment> records = pageComment.getRecords();
        long current = pageComment.getCurrent();
        long pages = pageComment.getPages();
        long size = pageComment.getSize();
        long total = pageComment.getTotal();
        boolean hasNext = pageComment.hasNext();//下一页
        boolean hasPrevious = pageComment.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return R.success().data(map);
    }

}

