package com.darkwater.async.handler;

import com.darkwater.async.EventHandler;
import com.darkwater.async.EventModel;
import com.darkwater.async.EventType;
import com.darkwater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jwc on 2017/7/29.
 */
@Component
public class LikeHandler implements EventHandler {

    @Autowired
    UserService userService;


    @Override
    public void doHandle(EventModel eventModel) {
//        if (eventModel.getEntityType() == WeiboUtil.ENTITY_TYPE_WEIBO) {
//            doWeiboLike(eventModel);
//        }
//        if (eventModel.getEntityType() == WeiboUtil.ENTITY_TYPE_COMMENT) {
//            doCommentLike(eventModel);
//        }
    }

//    public void doWeiboLike(EventModel eventModel) {
//        User fromUser = userService.getUserById(eventModel.getActorId());
//        User toUser = userService.getUserById(eventModel.getEntityOwner());
//        String content = fromUser.getName() + "赞了你的微博1次";
//        int fromId = WeiboUtil.DEFAULT_XITONG_ID;
//        messageService.addMessage(fromId, toUser.getId(), content);
//    }
//
//    public void doCommentLike(EventModel eventModel) {
//        User fromUser = userService.getUserById(eventModel.getActorId());
//        User toUser = userService.getUserById(eventModel.getEntityOwner());
//        String content = fromUser.getName() + "赞了你的评论1次";
//        int fromId = WeiboUtil.DEFAULT_XITONG_ID;
//        messageService.addMessage(fromId, toUser.getId(), content);
//    }

    @Override
    public List<EventType> getSupportEventTypes() {
        List<EventType> list = new ArrayList<>();
        list.add(EventType.LIKE);
        return list;
    }
}
