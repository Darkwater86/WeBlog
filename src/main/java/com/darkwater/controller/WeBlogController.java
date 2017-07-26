package com.darkwater.controller;

import com.alibaba.fastjson.JSON;
import com.darkwater.dao.NewsDao;
import com.darkwater.model.ErrObject;
import com.darkwater.model.HostHolder;
import com.darkwater.service.ImgService;
import com.darkwater.service.NewsService;
import com.darkwater.service.QiniuService;
import com.darkwater.utils.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Mr.Darkwater on 2017/7/24.
 */
@Controller
@RequestMapping("/weblog")
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

    @PostMapping("/upload/img")
    @ResponseBody
    public String uploadImage(@RequestParam("file")MultipartFile file){
        errObject = new ErrObject();
        if(!CheckUtils.checkImgSize(file)){
            errObject.setErrno(-1);
            errObject.setErrmsg("图片尺寸不符合要求");
            return JSON.toJSONString(errObject);
        }
        if(!CheckUtils.checkImgType(file)){
            errObject.setErrno(-1);
            errObject.setErrmsg("图片类型不符合要求");
            return JSON.toJSONString(errObject);
        }
        String imgurl = qiniuService.upToCloud(file);
        if(null == imgurl){
            errObject.setErrno(-1);
            errObject.setErrmsg("上传图片失败");
            return JSON.toJSONString(errObject);
        }
        errObject.setErrno(0);
        errObject.setErrmsg("http://otkzhho8u.bkt.clouddn.com/"+imgurl);
        return JSON.toJSONString(errObject);
    }

    @PostMapping("/create")
    @ResponseBody
    public String createWeblog(@RequestParam("uid")int uid,
                               @RequestParam(value = "title",required = false,defaultValue = "题目111")String title,
                               @RequestParam("imgurls")String[] imgurls,
                               @RequestParam("content")String content){
        errObject = new ErrObject();
//        hostHolder.getUser().getId();
        String ret = newsService.createNews(uid,imgurls,content,title);
         if(ret.equals("success")){
             errObject.setErrno(0);
             errObject.setErrmsg("success");
             return JSON.toJSONString(errObject);
         }
        errObject.setErrno(-1);
        errObject.setErrmsg(ret);
        return JSON.toJSONString(errObject);
    }
}
