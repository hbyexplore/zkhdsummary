package com.summary.zkhdsummary.controller;

import com.summary.zkhdsummary.bean.NoSubmiat;
import com.summary.zkhdsummary.service.LogService;
import com.summary.zkhdsummary.service.SubmitService;
import com.summary.zkhdsummary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ScheduTask {
    public List<String> users = new ArrayList<>();
    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;

    @Autowired
    private SubmitService submitService;
    //周一到周五每天下午5点31分执行
    @Scheduled(cron = "01 04 19 ? * MON-FRI")
    public void findUser(){
        //在查询之前清空昨天的数据
        submitService.delete();
        //获取当天时间并格式化
        SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
        String date = simp.format(new Date());
        //更具当天时间查询出所有用户的编号
        List<Integer> UserCardByDateList = logService.findUserByDate(date);
        //查询出全部用户的编号
        List<Integer> allCradList = userService.findAllCrad();

        List<Integer> list = allCradList;
        //求出两个集合的差集，也就是当天没有提交总结的员工的crad
        for(int i = 0; i < allCradList.size() ; i++){
            for(int j = 0 ; j < UserCardByDateList.size();j++){
                if(allCradList.get(i) == UserCardByDateList.get(j)){
                    list.remove(i);
                }
            }
        }

        if(list != null && list.size() > 0){
            //如果有没提交总结的员工编号，遍历编号，查询出员工的名称
            for(int k = 0; k < list.size(); k++){
                String name = userService.findUserByCrad(list.get(k));
              if(!("").equals(name)){
                  users.add(name);
                  //将信息存入数据库
                  submitService.insert(name);
               }
               System.out.println(name);
            }
        }

    }

    /**
     * 定时清空集合内的数据
     */
    //"0 0 0 * * MON-FRI"
//    @Scheduled(cron ="0 0 0 * * MON-FRI")
//    public void clear(){
//
//        submitService.delete();
//    }
}
