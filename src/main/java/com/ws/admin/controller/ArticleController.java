package com.ws.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

    /**
     * 添加文章
     *
     * @param article
     * @return
     */
    @PostMapping("/addArticle")
    public Object addArticle(Article article) {
        Map<String, Object> map = new HashMap<>();
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
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/articleList")
    public Object articleList(@RequestParam("current") Integer current,
                              @RequestParam("size") Integer size) {
        Page<Article> page = articleMapper.findArticlePage(new Page(current, size));
        Map<String, Object> map = new HashMap<>();
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
        Map<String, Object> map = new HashMap<>();
        map.put("article", article);
        map.put("labelName", labelNameList);
        return map;
    }

    /**
     * 查找需要修改的文章的数据
     * @param id
     * @return
     */
    @GetMapping("/findArticleByIdForEdit")
    public Object findArticleByIdForEdit(@RequestParam("id") Integer id) {
        Article article = articleMapper.selectById(id);

        String[] split = article.getArticleLabel().split(",");
        List<ArticleLabel> labelList = new ArrayList<>();
        for (String s : split) {
            int i = Integer.parseInt(s);
            ArticleLabel label = labelMapper.selectById(i);
            labelList.add(label);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("articlelabelArr", labelList);
        map.put("article", article);
        return map;
    }

    /**
     * 修改文章
     * @param article
     * @return
     */
    @PostMapping("/updateArticle")
    public Object updateArticle(Article article) {
        int i = articleMapper.updateById(article);
        Map<String, Object> map = new HashMap<>();
        if (i >= 1) {
            map.put("code", 1);
            map.put("message", "修改成功");
        } else {
            map.put("code", 0);
            map.put("message", "修改失败");
        }
        return map;
    }

    /**
     * 查询自己发表的文章
     * @param user
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/findMyArticle")
    public Object findMyArticle(User user,
                                @RequestParam("current") Integer current,
                                @RequestParam("size") Integer size) {
        QueryWrapper<Article> qw = new QueryWrapper<>();
        qw.eq("user_id", user.getId());
        IPage<Article> page = articleMapper.selectPage(new Page<Article>(current, size), qw);
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("articleList", page.getRecords());
        return map;
    }
}
