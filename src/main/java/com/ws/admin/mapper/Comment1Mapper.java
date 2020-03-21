package com.ws.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ws.admin.entity.Comment1;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface Comment1Mapper extends BaseMapper<Comment1> {


    @Select("SELECT c1.*,u.username,u.avatar \n" +
            "FROM t_comment_1 c1,t_user u \n" +
            "WHERE c1.userId=u.id AND c1.articleId=#{articleId}  ORDER BY c1.id DESC")
    Page<Comment1> findComment1Page(Page page, @Param("articleId") Integer articleId);
}
