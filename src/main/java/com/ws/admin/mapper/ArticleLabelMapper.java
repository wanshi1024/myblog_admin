package com.ws.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.admin.entity.ArticleLabel;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArticleLabelMapper extends BaseMapper<ArticleLabel> {


    @Update("update t_article_label set use_count=use_count+1 where label_name=#{labelName}")
    void increaseUseCount(String labelName); //标签使用次数+1

    @Select("SELECT * FROM t_article_label al WHERE al.use_count>0 ORDER BY al.use_count DESC")
    List<ArticleLabel> findHotLabel(); //查找热门标签
}
