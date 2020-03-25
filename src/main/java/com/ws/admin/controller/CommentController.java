package com.ws.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.admin.entity.Comment1;
import com.ws.admin.entity.Comment2;
import com.ws.admin.mapper.Comment1Mapper;
import com.ws.admin.mapper.Comment2Mapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Resource
    private Comment1Mapper comment1Mapper;
    @Resource
    private Comment2Mapper comment2Mapper;

    /**
     * 添加一级评论
     *
     * @param comment1
     * @return
     */
    @PostMapping("/addComment1")
    public Object addComment1(Comment1 comment1) {
        Map<Object, Object> map = new HashMap<>();
        int i = comment1Mapper.insert(comment1);
        if (i >= 1) {
            map.put("code", 1);
            map.put("message", "评论成功");
        } else {
            map.put("code", 0);
            map.put("message", "评论失败");
        }
        return map;
    }

    /**
     * 查找一级评论列表 分页
     *
     * @param current
     * @param size
     * @param articleId
     * @return
     */
    @GetMapping("/comment1List")
    public Object commentList(@RequestParam("current") Integer current,
                              @RequestParam("size") Integer size,
                              @RequestParam("articleId") Integer articleId) {

        Map<Object, Object> map = new HashMap<>();

        Page<Comment1> page = comment1Mapper.findComment1Page(new Page(current, size), articleId);

        List<Comment1> comment1List = page.getRecords();
        for (Comment1 comment1 : comment1List) {
            // 分别把每个一级评论的子评论查询出来添加到一级评论对象里
            List<Comment2> comment2List = comment2Mapper.findComment2Page(comment1.getId());
            comment1.setComment2List(comment2List);
        }

        map.put("total", page.getTotal());
        map.put("comment1List", comment1List);
        return map;
    }

    /**
     * 添加二级评论
     *
     * @param comment2
     * @return
     */
    @PostMapping("/addComment2")
    public Object addComment2(Comment2 comment2) {

        Map<Object, Object> map = new HashMap<>();
        int i = comment2Mapper.insert(comment2);
        if (i >= 1) {
            map.put("code", 1);
            map.put("message", "评论成功");
        } else {
            map.put("code", 0);
            map.put("message", "评论失败");
        }

        return map;
    }

    /**
     * 点赞+1
     *
     * @param commentLevel 评论级别 1-一级评论 2-二级评论
     * @param commentId    评论id
     * @return
     */
    @PostMapping("/addCompliment/{commentLevel}/{commentId}")
    public Object addCompliment(@PathVariable("commentLevel") Integer commentLevel,
                                @PathVariable("commentId") Integer commentId) {

        Map<Object, Object> map = new HashMap<>();
        Integer i = 0;
        if (commentLevel == 1) {
            QueryWrapper<Comment1> qw = new QueryWrapper<>();
            qw.eq("id", commentId);
            //先查询原本的点赞数 再+1修改
            Comment1 one = comment1Mapper.selectOne(qw);
            Integer complimentCount = one.getComplimentCount();
            Comment1 comment1 = new Comment1();
            comment1.setComplimentCount(++complimentCount);
            i = comment1Mapper.update(comment1, qw);
        } else {
            QueryWrapper<Comment2> qw = new QueryWrapper<>();
            qw.eq("id", commentId);
            Comment2 one = comment2Mapper.selectOne(qw);
            Integer complimentCount = one.getComplimentCount();
            Comment2 comment2 = new Comment2();
            comment2.setComplimentCount(++complimentCount);
            i = comment2Mapper.update(comment2, qw);
        }
        if (i >= 0) {
            map.put("code", 1);
            map.put("message", "点赞成功");
        } else {
            map.put("code", 0);
            map.put("message", "点赞失败");
        }

        return map;
    }

    @GetMapping("/getMyComment")
    public Object getMyComment(@RequestParam("userId") Integer userId,
                               @RequestParam("current") Integer current,
                               @RequestParam("size") Integer size) {
        Map<Object, Object> map = new HashMap<>();



        return map;
    }

}
