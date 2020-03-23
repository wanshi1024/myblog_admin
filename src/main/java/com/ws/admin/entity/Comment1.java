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
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_comment_1")
public class Comment1 extends Model<Comment1> implements Serializable {

    private static final long serialVersionUID = 9105866478951215547L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "userId")
    private Integer userId;

    @TableField(value = "articleId")
    private Integer articleId;

    @TableField(value = "commentContent")
    private String commentContent;

    @TableField(value = "commentDate")
    private String commentDate;

    @TableField(value = "complimentCount")
    private Integer complimentCount; //点赞数



    /*****/
    @TableField(exist = false)
    private String username;

    @TableField(exist = false)
    private String avatar;

    @TableField(exist = false)
    private Integer flag=0; //辅助前端的一个属性

    @TableField(exist = false)
    private Integer flag1=0; //辅助前端的一个属性

    @TableField(exist = false)
    private boolean compliment = false;

    @TableField(exist = false)
    private List<Comment2> comment2List;
}
