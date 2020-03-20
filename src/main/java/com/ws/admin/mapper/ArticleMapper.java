package com.ws.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.admin.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ArticleMapper extends BaseMapper<Article> {
    @Select("SELECT  a.id,\n" +
            "\t\t\t\ta.article_title,\n" +
            "\t\t\t\ta.article_content,\n" +
            "\t\t\t\ta.read_count,\n" +
            "\t\t\t\ta.comment_count,\n" +
            "\t\t\t\ta.publish_date,\n" +
            "\t\t\t\ta.user_id,\n" +
            "\t\t\t\tu.username,\n" +
            "\t\t\t\tu.avatar\n" +
            "\t\n" +
            "FROM t_article a,t_user u \n" +
            "\n" +
            "WHERE a.state=1 AND a.user_id=u.id ORDER BY a.id DESC")
    Page<Article> findArticlePage(Page page);

    @Update("update t_article set read_count=read_count+1 where id=#{id}")
    void increaseReadCount(Integer articleId);

    @Select("SELECT a.*, u.username,u.avatar FROM t_article a, t_user u WHERE a.state=1 AND a.user_id=u.id AND a.id in \n" +
            "\n" +
            "(SELECT tac.articleId id FROM t_article_collection tac WHERE tac.userId=#{userId}) ")
    Page<Article> findArticlePageByMyCollection(Page page, @Param("userId") Integer userId);
}
