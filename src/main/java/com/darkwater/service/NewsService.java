package com.darkwater.service;

import com.darkwater.dao.NewsDao;
import com.darkwater.dao.UserDao;
import com.darkwater.model.ErrObject;
import com.darkwater.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Date;

/**
 * Created by Mr.Darkwater on 2017/7/25.
 */
@Service
public class NewsService {
    @Autowired
    NewsDao newsDao;
    @Autowired
    QiniuService qiniuService;
    @Autowired
    UserDao userDao;

    ErrObject errObject;

    Logger logger = LoggerFactory.getLogger(NewsService.class);

    public String createNews(int user_id,String[] filenames,String content,String title){

        errObject = new ErrObject();
        StringBuilder img_url = new StringBuilder();

        for (String filename : filenames){
            img_url.append("|");
            img_url.append(filename);
        }
        if(!checkWeblogContent(content)){
            logger.error("内容格式错误");
            return "内容格式错误";
        }
        //TODO:入参为url，此处需对于传入url的原图格式进行校验
        News news = new News();
        news.setContent(content);
        news.setDate(new Date());
        news.setStatus(0);
        news.setType("w");
        news.setImg(StringUtils.substring(img_url.toString(),1));
        news.setUid(user_id);
        news.setLike(0);
        news.setTitle(title);
        try {
            System.out.println(news.getContent()+news.getTitle()+news.getLike());
            newsDao.createBlog(news);
        }catch (Exception e){
            e.printStackTrace();
            return "数据库添加微博失败";
        }

        return "success";
    }

    public boolean checkWeblogContent(String content){
        if(content.length()>0&&content.length()<500){
            return true;
        }
        return false;
    }
}
