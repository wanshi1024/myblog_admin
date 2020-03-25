package com.ws.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.admin.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查找文章列表
     * @param page
     * @param article
     * @return
     */
    Page<Article> findArticlePage(Page<Article> page, @Param("article") Article article);

    @Update("update t_article set read_count=read_count+1 where id=#{id}")
    void increaseReadCount(Integer articleId);

    @Select("SELECT a.*, u.username,u.avatar FROM t_article a, t_user u WHERE a.state=1 AND a.user_id=u.id AND a.id in \n" +
            "\n" +
            "(SELECT tac.articleId id FROM t_article_collection tac WHERE tac.userId=#{userId}) ")
    Page<Article> findArticlePageByMyCollection(Page page, @Param("userId") Integer userId);

    // 查找相关文章
    List<Article> findRelatedArticle(@Param("articleLabel") List<String> articleLabel, @Param("articleId") Integer articleId);

}
