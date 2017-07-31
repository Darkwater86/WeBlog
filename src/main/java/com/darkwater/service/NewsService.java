package com.darkwater.service;

import com.darkwater.dao.NewsDao;
import com.darkwater.dao.UserDao;
import com.darkwater.model.ErrObject;
import com.darkwater.model.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Darkwater on 2017/7/25.
 */
@Service
public class NewsService {
    static String WEBLOG_TYPE = "weblog";
    @Autowired
    NewsDao newsDao;
    @Autowired
    QiniuService qiniuService;
    @Autowired
    UserDao userDao;
    ErrObject errObject;
    Logger logger = LoggerFactory.getLogger(NewsService.class);

    public String createNews(int user_id, String images, String content, String title) {
        errObject = new ErrObject();
        if (!checkWeblogContent(content)) {
            logger.error("内容格式错误");
            return "内容格式错误";
        }
        //TODO:入参为url，此处需对于传入url的原图格式进行校验
        News news = new News();
        news.setContent(content);
        news.setDate(new Date());
        news.setStatus(0);
        news.setType(WEBLOG_TYPE);
        news.setImg(images);
        news.setUid(user_id);
        news.setLike(0);
        news.setTitle(title);
        try {
            newsDao.createBlog(news);
        } catch (Exception e) {
            e.printStackTrace();
            return "数据库添加微博失败";
        }
        return "success";
    }
    public boolean checkWeblogContent(String content) {
        if (content.length() > 0 && content.length() < 500) {
            return true;
        }
        return false;
    }

    public List<News> selectNews(int page,int pagesize){
        if(page < 1){
            page = 1;
        }
        if(0 <pagesize && pagesize<20){}else{
            pagesize = 10;
        }
        int limit = pagesize;
        int offset = (page-1)*pagesize;

        return newsDao.selectNews(offset,limit);
    }

    public List<News> selectNewsByUid(int page,int pagesize,int uid){
        if(page < 1){
            page = 1;
        }
        if(0 <pagesize && pagesize<20){}else{
            pagesize = 10;
        }
        int limit = pagesize;
        int offset = (page-1)*pagesize;
        return newsDao.selectNewsByUserId(offset,limit,uid);
    }

    public int checkSelfBlog(int wid,int uid){
        News news = newsDao.selectNewsById(wid);
        if(null == news){
            return -1;
        }
        if(0 != news.getStatus()){

        }
        if(news.getUid() != uid){
            return 1;
        }
        return 0;
    }
    public boolean deleteNewsById(int id){
        return true;
        //TODO
    }
}
