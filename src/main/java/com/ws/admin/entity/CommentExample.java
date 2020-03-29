package com.ws.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_comment_example")
public class CommentExample extends Model<CommentExample> implements Serializable {

    private static final long serialVersionUID = -8019483530141155881L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "fromCommentUserName")
    private String fromCommentUserName; //评论者用户名

    @TableField(value = "toUserId")
    private Integer toUserId; //被评论用户的id

    @TableField(value = "articleId")
    private Integer articleId; //评论所在的文章id

    @TableField(value = "articleTitle")
    private String articleTitle;

    @TableField(value = "commentContent")
    private String commentContent;

    @TableField(value = "commentDate")
    private String commentDate; //评论日期

    @TableField(value = "readState")
    private Integer readState; //阅读状态

}
