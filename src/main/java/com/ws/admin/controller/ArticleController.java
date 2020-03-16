package com.ws.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.admin.entity.Article;
import com.ws.admin.entity.ArticleLabel;
import com.ws.admin.entity.User;
import com.ws.admin.mapper.ArticleLabelMapper;
import com.ws.admin.mapper.ArticleMapper;
import com.ws.admin.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private ArticleLabelMapper labelMapper;
    @Resource
    private UserMapper userMapper;
    Map<String, Object> map = new HashMap<>();

    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    @PostMapping("/addArticle")
    public Object addArticle(Article article) {

        int insert = articleMapper.insert(article);
        if (insert == 1) {
            map.put("code", 1);
            map.put("message", "文章发布成功");
        } else {
            map.put("code", 0);
            map.put("message", "文章发布失败");
        }
        return map;
    }

    /**
     * 获取文章列表  分页查询
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/articleList")
    public Object articleList(@RequestParam("current") Integer current, @RequestParam("size") Integer size) {
        Page<Article> page = articleMapper.findArticlePage(new Page(current, size));
        map.put("total", page.getTotal());
        map.put("articleList", page.getRecords());

        return map;
    }

    /**
     * 通过文章id查询文章数据
     *
     * @param id
     * @return
     */
    @GetMapping("/findArticleById")
    public Object findArticleById(@RequestParam("id") Integer id) {
        articleMapper.increaseReadCount(id); // 阅读数量+1
        Article article = articleMapper.selectById(id);
        // 分解标签字符串查询标签名生成数组
        String[] split = article.getArticleLabel().split(",");
        List<String> labelNameList = new ArrayList<>();
        for (String s : split) {
            int i = Integer.parseInt(s);
            ArticleLabel articleLabel = labelMapper.selectById(i);
            labelNameList.add(articleLabel.getLabelName());
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username").eq("id", article.getUserId());
        User user = userMapper.selectOne(queryWrapper);
        article.setUsername(user.getUsername());
        article.setAvatar(user.getAvatar());

        map.put("article", article);
        map.put("labelName", labelNameList);
        return map;
    }
}
