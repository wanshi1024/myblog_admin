package com.ws.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_article")
public class Article extends Model<Article> implements Serializable {

    private static final long serialVersionUID = -3262282135754931393L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; //文章id

    @TableField(value = "user_id")
    private Integer userId; //用户id

    @TableField(value = "article_title")
    private String articleTitle; //文章标题

    @TableField(value = "article_content")
    private String articleContent; //文章内容

    @TableField(value = "read_count")
    private Integer readCount; //阅读数

    @TableField(value = "comment_count")
    private Integer commentCount; //评论数

    @TableField(value = "article_label")
    private String articleLabel; //文章标签

    @TableField(value = "publish_date")
    private String publishDate; //发布时间

    @TableField(value = "state")
    private Integer state; //文章状态

    /*****/
    @TableField(exist = false)
    private String username;
    @TableField(exist = false)
    private String avatar;

}
