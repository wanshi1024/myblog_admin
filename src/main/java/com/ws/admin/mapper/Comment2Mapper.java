package com.ws.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ws.admin.entity.Comment2;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Comment2Mapper extends BaseMapper<Comment2> {

    @Select("SELECT\n" +
            "\tc2.*, u1.username fromUserName,\n" +
            "\tu1.avatar fromAvatar,\n" +
            "\tu2.username toUserName\n" +
            "FROM\n" +
            "\tt_comment_2 c2,\n" +
            "\tt_user u1,\n" +
            "\tt_user u2\n" +
            "WHERE\n" +
            "\tc2.fromUserId = u1.id\n" +
            "AND c2.toUserId = u2.id\n" +
            "AND c2.parentCommentId = #{parentCommentId} ORDER BY c2.id DESC")
    List<Comment2> findComment2Page(@Param("parentCommentId") Integer parentCommentId);
}
