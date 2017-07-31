package com.darkwater.controller;

import com.alibaba.fastjson.JSON;
import com.darkwater.dao.NewsDao;
import com.darkwater.model.ErrObject;
import com.darkwater.model.HostHolder;
import com.darkwater.model.News;
import com.darkwater.service.ImgService;
import com.darkwater.service.NewsService;
import com.darkwater.service.QiniuService;
import com.darkwater.utils.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Mr.Darkwater on 2017/7/24.
 */
@Controller
//@RequestMapping("/weblog")
public class WeBlogController {
    @Autowired
    QiniuService qiniuService;
    @Autowired
    NewsService newsService;
    @Autowired
    ImgService imgService;
    @Autowired
    NewsDao newsDao;
    @Autowired
    HostHolder hostHolder;

    ErrObject errObject;

    @PostMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        errObject = new ErrObject();
        if (!CheckUtils.checkImgSize(file)) {
            errObject.setErrno(-1);
            errObject.setErrmsg("图片尺寸不符合要求");
            return JSON.toJSONString(errObject);
        }
//        if(!CheckUtils.checkImgType(file)){
//            errObject.setErrno(-1);
//            errObject.setErrmsg("图片类型不符合要求");
//            return JSON.toJSONString(errObject);
//        }
        String imgurl = qiniuService.upToCloud(file);
        if (null == imgurl) {
            errObject.setErrno(-1);
            errObject.setErrmsg("上传图片失败");
            return JSON.toJSONString(errObject);
        }
        errObject.setErrno(0);
        errObject.setErrmsg("http://otkzhho8u.bkt.clouddn.com/" + imgurl);
        return JSON.toJSONString(errObject);
    }

    @PostMapping("/addWeibo")
    @ResponseBody
    public String createWeblog(@RequestParam(value = "title", required = false, defaultValue = "题目111") String title,
                               @RequestParam("images") String imgurls,
                               @RequestParam("content") String content) {
        errObject = new ErrObject();
//        hostHolder.getUser().getId();
        if (hostHolder.getUser() == null) {
            errObject.setErrno(-1);
            errObject.setErrmsg("未找到该用户");
            return JSON.toJSONString(errObject);
        }
        String ret = newsService.createNews(hostHolder.getUser().getId(), imgurls, content, title);
        if (ret.equals("success")) {
            errObject.setErrno(0);
            errObject.setErrmsg("success");
            return JSON.toJSONString(errObject);
        }
        errObject.setErrno(-1);
        errObject.setErrmsg(ret);
        return JSON.toJSONString(errObject);
    }

    @GetMapping("/getNews")
    @ResponseBody
    public String getWeBlog(@RequestParam("page") int page,
                            @RequestParam("pagesize") int pagesize,
                            @RequestParam("uid") int uid) {
        errObject = new ErrObject();
        List<News> newsList ;
        if (0 == uid) {
            newsList = newsService.selectNews(page, pagesize);
        } else {
            newsList = newsService.selectNewsByUid(page, pagesize, uid);
        }
        if (0 == newsList.size()){
            errObject.setErrno(-1);
            errObject.setErrmsg("没有更多信息");
            System.out.println(JSON.toJSONString(newsList));
        }else{
            errObject.setErrno(0);
            errObject.setErrmsg(JSON.toJSONString(newsList));
        }
        return JSON.toJSONString(errObject);
    }
    @PostMapping("/deleteNews")
    @ResponseBody
    public String deleteWeBlog(@RequestParam("wid")int wid){
        errObject = new ErrObject();
        switch (newsService.checkSelfBlog(wid,hostHolder.getUser().getId())){
            case -1 :
                errObject.setErrno(-1);
                errObject.setErrmsg("未找到该文章");
                return JSON.toJSONString(errObject);
            case 0 :
                break;
            case 1:
                errObject.setErrno(1);
                errObject.setErrmsg("无该操作权限");
                return JSON.toJSONString(errObject);
            default:
                errObject.setErrmsg("fuck");
                errObject.setErrno(111111111);
                return JSON.toJSONString(errObject);
        }
        if (newsService.deleteNewsById(wid)){
            errObject.setErrno(0);
            errObject.setErrmsg("success");
            return JSON.toJSONString(errObject);
        }
        errObject.setErrno(-1);
        errObject.setErrmsg("删除失败");
        return JSON.toJSONString(errObject);
    }
}
