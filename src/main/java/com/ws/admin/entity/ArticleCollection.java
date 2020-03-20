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
@TableName(value = "t_article_collection")
public class ArticleCollection extends Model<ArticleCollection> implements Serializable {
    private static final long serialVersionUID = -8029790133430649398L;
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "userId")
    private Integer userId;
    @TableField(value = "articleId")
    private Integer articleId;
}
