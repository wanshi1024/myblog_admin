package com.ws.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.admin.entity.Article;
import com.ws.admin.entity.ArticleCollection;
import com.ws.admin.entity.ArticleLabel;
import com.ws.admin.entity.User;
import com.ws.admin.mapper.ArticleCollectionMapper;
import com.ws.admin.mapper.ArticleLabelMapper;
import com.ws.admin.mapper.ArticleMapper;
import com.ws.admin.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
@CrossOrigin(origins = "*")
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

        String[] strings = article.getArticleLabel().split(",");

        for (String s : strings) {
            //给文章使用的标签使用次数+1
            labelMapper.increaseUseCount(s);
        }

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
                              @RequestParam("size") Integer size,
                              Article article) {

        Page<Article> page = articleMapper.findArticlePage(new Page(current, size), article);
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("articleList", page.getRecords());

        return map;
    }

    /**
     * 获取我的收藏文章列表 分页查询
     *
     * @param current
     * @param size
     * @return
     */
    @GetMapping("/myCollectionArticleList")
    public Object myCollectionArticleList(@RequestParam("current") Integer current,
                                          @RequestParam("size") Integer size,
                                          @RequestParam("userId") Integer userId) {
        Page<Article> page = articleMapper.findArticlePageByMyCollection(new Page(current, size), userId);
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
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username").eq("id", article.getUserId());
        User user = userMapper.selectOne(queryWrapper);
        article.setUsername(user.getUsername());

        Map<String, Object> map = new HashMap<>();
        map.put("article", article);
        return map;
    }

    /**
     * 查找需要修改的文章的数据
     *
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
     *
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
     *
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
        qw.eq("user_id", user.getId()).eq("state", 1).orderByDesc("id");
        IPage<Article> page = articleMapper.selectPage(new Page<Article>(current, size), qw);
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("articleList", page.getRecords());
        return map;
    }

    /**
     * 通过id删除文章  实际修改state=0
     *
     * @param article
     * @return
     */
    @PostMapping("/deleteArticleById")
    public Object deleteArticleById(Article article) {

        article.setState(0);
        boolean b = article.updateById();
        Map<String, Object> map = new HashMap<>();
        if (b) {
            map.put("code", 1);
            map.put("message", "删除文章成功");
        } else {
            map.put("code", 0);
            map.put("message", "删除文章失败");
        }

        return map;
    }

    @Resource
    private ArticleCollectionMapper articleCollectionMapper;

    /**
     * 收藏文章
     *
     * @param articleCollection
     * @return
     */
    @PostMapping("/collectArticle")
    public Object collectArticle(ArticleCollection articleCollection) {

        int i = articleCollectionMapper.insert(articleCollection);
        Map<String, Object> map = new HashMap<>();
        if (i >= 1) {
            map.put("code", 1);
            map.put("message", "收藏文章成功");
        } else {
            map.put("code", 0);
            map.put("message", "收藏文章失败");
        }

        return map;
    }

    /**
     * 取消收藏
     *
     * @param articleCollection
     * @return
     */
    @PostMapping("/cancelCollectArticle")
    public Object cancelCollectArticle(ArticleCollection articleCollection) {

        QueryWrapper<ArticleCollection> qw = new QueryWrapper<>();
        qw.eq("userId", articleCollection.getUserId())
                .eq("articleId", articleCollection.getArticleId());
        boolean b = articleCollection.delete(qw);
        Map<String, Object> map = new HashMap<>();
        if (b) {
            map.put("code", 1);
            map.put("message", "取消收藏成功");
        } else {
            map.put("code", 0);
            map.put("message", "取消收藏失败");
        }
        return map;
    }

    /**
     * 查找是否已收藏
     *
     * @param articleCollection
     * @return
     */
    @GetMapping("/findIsCollected")
    public Object findIsCollected(ArticleCollection articleCollection) {
        Map<String, Object> map = new HashMap<>();

        QueryWrapper<ArticleCollection> qw = new QueryWrapper<>();
        qw.eq("userId", articleCollection.getUserId())
                .eq("articleId", articleCollection.getArticleId());
        Integer count = articleCollection.selectCount(qw);
        if (count >= 1) {
            map.put("code", 1);
            map.put("message", "已收藏");
        } else {
            map.put("code", 0);
            map.put("message", "未收藏");
        }

        return map;
    }

    /**
     * 查找相关文章
     *
     * @param articleId
     * @return
     */
    @GetMapping("/findRelatedArticle/{articleId}")
    public Object findRelatedArticle(@PathVariable("articleId") Integer articleId) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Article> qw = new QueryWrapper<>();
        qw.eq("id", articleId);
        Article article = articleMapper.selectOne(qw);
        List<String> split = Arrays.asList(article.getArticleLabel().split(","));
        List<Article> list = articleMapper.findRelatedArticle(split, articleId);
        map.put("articleList", list);
        return map;

    }

    /**
     * 通过标签名查询文章
     *
     * @param current
     * @param size
     * @param labelName
     * @return
     */
    @GetMapping("/findArticleByLabel")
    public Object findArticleByLabel(@RequestParam("current") Integer current,
                                     @RequestParam("size") Integer size,
                                     @RequestParam("labelName") String labelName) {
        Map<String, Object> map = new HashMap<>();

        return map;

    }
}
