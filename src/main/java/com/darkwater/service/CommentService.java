package com.darkwater.service;

import com.darkwater.dao.CommentDao;
import com.darkwater.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Darkwater on 2017/7/26.
 */
@Service
public class CommentService {
    @Autowired
    CommentDao commentDao;
    Logger logger = LoggerFactory.getLogger(CommentService.class);
    public Comment getCommentById(int cid){
        Comment comment = commentDao.selectCommentById(cid);
        if (null == comment){
            logger.error("获取评论失败");
        }
        return comment;
    }
    public int createComment(int entityUid,int entityId,String entityType,String content,int uid){
        Comment comment = new Comment();
        comment.setStatus(0);
        comment.setLiked(0);
        comment.setEntityUid(entityUid);
        comment.setEntityId(entityId);
        comment.setEntityType(entityType);
        comment.setContent(content);
        comment.setUid(uid);
        comment.setDate(new Date());
        return commentDao.createComment(comment);
    }

    public List getCommentByEntity(int entityId,String entityType,int page,int pagesize){
        if(page < 1){
            page = 1;
        }
        if (pagesize < 0){
            return null;
        }
        if (checkEntity(entityId,entityType)){
            return null;
        }
        List<Comment> commentList = commentDao.selectCommentBYEntity(entityId,entityType,page-1,(page-1)*pagesize);
        return commentList;
    }
    public boolean checkEntity(int entityId,String entityType) {
        if (entityId < 0) {
            return false;
        }
        if (entityType != "weblog" && entityType != "comment") {
            return false;
        }
        return true;
    }
}
