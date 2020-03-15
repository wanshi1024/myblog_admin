package com.ws.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.admin.entity.Article;
import com.ws.admin.entity.vo.ArticleVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {
    @Select("SELECT a.*,u.* FROM t_article a,t_user u WHERE a.state=1;")
    List<ArticleVO> findArticlePage();
}
