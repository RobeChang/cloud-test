package com.cc.cloud.web.controller;

import com.cc.cloud.monitor.AlarmMessage;
import com.cc.cloud.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changcheng
 * @since 2020-03-09 9:24
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String index() {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setId("1");
        user1.setName("changcheng");
        user1.setAddress("北京");
        list.add(user1);
        //创建一个模型视图对象
        ModelAndView modelAndView = new ModelAndView();
        //将数据放置到ModelAndView对象中
        modelAndView.addObject("userList", list);
        // 指定content.jsp视图接受model
        modelAndView.setViewName("index");
        //返回ModelAndView对象mav
        return "index";
    }


    List<AlarmMessage> lastList;

    /**
     * 接收skywalking的webhook接口
     */
    @RequestMapping("webhook")
    public void webhook(@RequestBody List<AlarmMessage> alarmMessageList) {
        for(AlarmMessage m:alarmMessageList) {
            System.out.println(m.toString());
        }
        lastList=alarmMessageList;

    }

    /**
     * 显示skywalking的警告信息
     */
    @ResponseBody
    @RequestMapping("skywalkingShowWarnings")
    public List<AlarmMessage> skywalkingShowWarnings() {
        return lastList;
    }

    /**
     * 显示skywalking的警告信息
     */
    @ResponseBody
    @RequestMapping("timeout")
    public String timeout() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "timeout";
    }
}
