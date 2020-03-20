package com.ws.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.admin.entity.Comment1;
import com.ws.admin.mapper.Comment1Mapper;
import com.ws.admin.mapper.Comment2Mapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
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
     * @param current
     * @param size
     * @param articleId
     * @return
     */
    @GetMapping("/commentList")
    public Object commentList(@RequestParam("current") Integer current,
                              @RequestParam("size") Integer size,
                              @RequestParam("articleId") Integer articleId) {

        Map<Object, Object> map = new HashMap<>();

        Page<Comment1> page = comment1Mapper.findComment1Page(new Page(current, size), articleId);

        map.put("total", page.getTotal());
        map.put("commentList", page.getRecords());
        return map;
    }
}
