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
    private Integer id;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "article_title")
    private String articleTitle;

    @TableField(value = "article_content")
    private String articleContent;

    @TableField(value = "article_label")
    private String articleLabel;

    @TableField(value = "state")
    private Integer state;

}
