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
@TableName(value = "t_article_label")
public class ArticleLabel extends Model<ArticleLabel> implements Serializable {
    private static final long serialVersionUID = -8532722617845014963L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "label_name")
    private String labelName;

    @TableField(value = "label_classify")
    private Integer labelClassify;

    @TableField(value = "use_count")
    private Integer useCount;
}
