package com.darkwater.async.handler;


import com.darkwater.async.EventHandler;
import com.darkwater.async.EventModel;
import com.darkwater.async.EventType;
import com.darkwater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jwc on 2017/7/28.
 */
@Component
public class LoginHandler implements EventHandler {

    @Autowired
    UserService userService;

//    @Autowired
//    MailUtil mailUtil;

    @Override
    public void doHandle(EventModel eventModel) {
//        try {
//            User user = userService.getUserById(eventModel.getActorId());
//            String subject = "欢迎登陆";
//            Map<String, Object> map = new HashMap<>();
//            map.put("user", user);
//            map.put("subject", subject);
//            mailUtil.sendWithHTMLTemplate(map);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LOGIN);
    }
}
