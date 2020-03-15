package com.ws.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.admin.entity.Article;
import com.ws.admin.mapper.ArticleMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    private ArticleMapper articleMapper;
    Map<String, Object> map = new HashMap<>();

    /**
     * 添加文章
     * @param article
     * @return
     */
    @PostMapping("/addArticle")
    public Object addArticle(Article article){

        int insert = articleMapper.insert(article);
        if(insert==1){
            map.put("code",1);
            map.put("message","文章发布成功");
        }else{
            map.put("code",0);
            map.put("message","文章发布失败");
        }
        return map;
    }

    @GetMapping("/articleList")
    public Object articleList(Article article,
                              @RequestParam("start") Integer start,
                              @RequestParam("limit") Integer limit
                              ){
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state",1) //未删除
                    .eq("user_id",article.getUserId())
                    .like("article_title",article.getArticleTitle()) //通过标题模糊查询
                    .orderByDesc("id"); //通过id倒序查询

        IPage<Article> iPage = articleMapper.selectPage(new Page<Article>(start, limit), queryWrapper);
        map.put("count",iPage.getTotal()); //总条数
        map.put("articleList",iPage.getRecords()); //数据



        return map;
    }
}
