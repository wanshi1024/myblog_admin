package com.ws.admin.controller;

import com.ws.admin.entity.ArticleLabel;
import com.ws.admin.mapper.ArticleLabelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/articleLabel")
public class ArticleLabelController {
    @Resource
    private ArticleLabelMapper articleLabelMapper;
    Map<String, Object> map = new HashMap<>();

    //    1-开发语言 2-平台框架 3-服务器 4-数据库 5开发工具 6-其他
    @RequestMapping("/getAllLabel")
    public Object getAllLabel() {
        List<ArticleLabel> labelList = articleLabelMapper.selectList(null);
        List<ArticleLabel> list1 = new ArrayList<>();
        List<ArticleLabel> list2 = new ArrayList<>();
        List<ArticleLabel> list3 = new ArrayList<>();
        List<ArticleLabel> list4 = new ArrayList<>();
        List<ArticleLabel> list5 = new ArrayList<>();
        List<ArticleLabel> list6 = new ArrayList<>();
        for (ArticleLabel label : labelList) {
            switch (label.getLabelClassify()) {
                case 1:
                    list1.add(label);
                    break;
                case 2:
                    list2.add(label);
                    break;
                case 3:
                    list3.add(label);
                    break;
                case 4:
                    list4.add(label);
                    break;
                case 5:
                    list5.add(label);
                    break;
                case 6:
                    list6.add(label);
                    break;
                default:
                    break;
            }
        }
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();
        Map<String, Object> map4 = new HashMap<>();
        Map<String, Object> map5 = new HashMap<>();
        Map<String, Object> map6 = new HashMap<>();
        map1.put("classifyName", "开发工具");
        map1.put("labelList", list1);
        map2.put("classifyName", "平台框架");
        map2.put("labelList", list2);
        map3.put("classifyName", "服务器");
        map3.put("labelList", list3);
        map4.put("classifyName", "数据库");
        map4.put("labelList", list4);
        map5.put("classifyName", "开发工具");
        map5.put("labelList", list5);
        map6.put("classifyName", "其他");
        map6.put("labelList", list6);
        List<Map<String, Object>> r = new ArrayList<>();
        r.add(map1);
        r.add(map2);
        r.add(map3);
        r.add(map4);
        r.add(map5);
        r.add(map6);
        return r;
    }

}
