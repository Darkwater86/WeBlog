package com.darkwater.controller;

import com.alibaba.fastjson.JSON;
import com.darkwater.model.Comment;
import com.darkwater.model.ErrHolder;
import com.darkwater.model.HostHolder;
import com.darkwater.model.ViewObject;
import com.darkwater.service.CommentService;
import com.darkwater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Darkwater on 2017/7/26.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    ErrHolder errHolder;

    @Autowired
    HostHolder hostHolder;

    @PostMapping("/get")
    @ResponseBody
    public String getCommentByEntity(@RequestParam(value = "entityId" ,required = false,defaultValue = "-1") int entityId,
                                     @RequestParam(value = "entityType",required = false) String entityType,
                                     @RequestParam(value = "page",required = false,defaultValue = "-1") int page,
                                     @RequestParam(value = "pagesize",required = false,defaultValue = "-1") int pagesize){
        List<ViewObject> vos = new ArrayList<>();
        List<Comment> commentlist = commentService.getCommentByEntity(entityId,entityType,page,pagesize);
        if(null != commentlist){
            for (Comment comment : commentlist){
                ViewObject vo = new ViewObject();
                vo.set("user",userService.selectUserById(comment.getUid()));
                vo.set("comment",comment);
                vos.add(vo);
            }
            return JSON.toJSONString(vos);
        }
        errHolder.getErrobj().setErrno(-1);
        errHolder.getErrobj().setErrmsg("获取评论异常");
        return JSON.toJSONString(errHolder);
    }

    @PostMapping("/create")
    @ResponseBody
    public String createComment(@RequestParam("entityId")int entityId,
                                @RequestParam("entityType")String entityType,
                                @RequestParam("content")String content,
                                @RequestParam("entityUid")int entityUid){

        int cid = commentService.createComment(entityUid,entityId,entityType,content,hostHolder.getUser().getId());
        Comment comment = commentService.getCommentById(cid);
        if (null == comment){
            return "failed";
        }
        return cid+" : "+JSON.toJSONString(comment);
    }
}
