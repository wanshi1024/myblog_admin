package com.ws.admin.entity.vo;

import com.ws.admin.entity.Article;
import com.ws.admin.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVO {

    private User user;
    private Article article;
}
