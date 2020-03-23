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
@TableName(value = "t_comment_2")
public class Comment2 extends Model<Comment2> implements Serializable {

    private static final long serialVersionUID = -6766142490763796795L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "fromUserId")
    private Integer fromUserId; // 评论人的id

    @TableField(value = "toUserId")
    private Integer toUserId; //被评论人的id

    @TableField(value = "parentCommentId")
    private Integer parentCommentId; //父级评论的id

    @TableField(value = "commentContent")
    private String commentContent;

    @TableField(value = "commentDate")
    private String commentDate;

    @TableField(value = "complimentCount")
    private Integer complimentCount; //点赞数


    /********/
    @TableField(exist = false)
    private String fromUserName;

    @TableField(exist = false)
    private String fromAvatar;

    @TableField(exist = false)
    private String toUserName;

    @TableField(exist = false)
    private boolean compliment = false;

}
