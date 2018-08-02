package com.summary.zkhdsummary.service.Impl;

import com.github.pagehelper.PageHelper;

import com.summary.zkhdsummary.bean.Log;
import com.summary.zkhdsummary.config.PageBean;
import com.summary.zkhdsummary.bean.LogBean;
import com.summary.zkhdsummary.bean.User;
import com.summary.zkhdsummary.mapper.LogMapper;
import com.summary.zkhdsummary.mapper.UserMapper;
import com.summary.zkhdsummary.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Autowired
    private UserMapper userMapper;

/**
 * 搜索
 * */
    @Override
    public PageBean<LogBean> searchLog(String username, String userdate, int currement, int pageSize) {
        PageHelper.startPage(currement,pageSize);
        List<LogBean> logBeans = logMapper.searchLog(username, userdate);
        for (LogBean logBean : logBeans) {
            Integer lid = logBean.getLid();
            //获取帖子对应的评论数
            Integer listById = logMapper.findListById(lid);
            logBean.setCommentCount(listById);
        }
       /* Collections.sort(logBeans, new Comparator<LogBean>() {
            @Override
            public int compare(LogBean o1, LogBean o2) {
                return (int) (o2.getLid() - o1.getLid());
            }
        });*/
        //获得总条数
        int searchLogCount=logMapper.getSearchLogCount(username,userdate);
        PageBean<LogBean> pageBean = new PageBean<>(currement,pageSize,searchLogCount);
        pageBean.setItems(logBeans);
        return pageBean;
    }
/**
 * 添加总结
 * */
    @Override
    public void addSummary(String username, String logTitle, String logContent) {
        //先根据用户名获取id
        List<User> allUser = userMapper.findAllUser();
        for (User user : allUser) {
            String name = user.getName();
            if (name.equals(username)){
                //将id以及总结标题和内容存入log表
                HashMap<String, Object> summaryMap = new HashMap<>();
                summaryMap.put("userId",user.getId());
                summaryMap.put("logTitle",logTitle);
                summaryMap.put("logContent",logContent);
                summaryMap.put("newTime",new Date());
                logMapper.insertSummary(summaryMap);
                break;
            }
        }

    }

    /**
     * 根据热度查询出前8条
     * @return
     */
    @Override
    public List<Log> findAllByHot() {
        return logMapper.findAllByHot();
    }

    /**
     * 查出评论最多的前8条
     * @return
     */
    @Override
    public List<LogBean> findAllByCommentTotal() {

        List<LogBean> logBeans = new ArrayList<>();
        //查出所有日志
        List<Log> logs = logMapper.finAll();
        //根据id查出每条日志的评论
        for(int i = 0; i<logs.size(); i++){
            LogBean logBean = logMapper.findList(logs.get(i).getId());
            //添加进集合
            logBeans.add(logBean);
        }

        //通过评论数排序,并截取前8条
        Collections.sort(logBeans, new Comparator<LogBean>() {
            @Override
            public int compare(LogBean o1, LogBean o2) {
                if(o1.getCommentCount() < o2.getCommentCount()){
                    return 1;
                }
                if(o1.getCommentCount() == o2.getCommentCount()){
                    return 0;
                }
                return -1;
            }
        });

        //截取前8条
        while (true){
            if(logBeans.size() > 8){
                logBeans.remove(8);
            }else {
                break;
            }
        }

        return logBeans;
    }

    /*@Override
    public PageBean<LogBean> findList(int currement, int pageSize) {

        PageHelper.startPage(currement,pageSize);
        List<LogBean> list = logMapper.findAllList();
        PageBean<LogBean> pageBean = new PageBean<>(currement,pageSize,32);
        pageBean.setItems(list);
        return pageBean;
    }*/

    @Override
    public List findTime(List<User> list) {
        return null;
    }

    @Override
    public List<List<Log>> findSummary(List<User> listTime) {
        return null;
    }
    /**
     * 代码之精华
     * 之精华
     * 之精华
     * 之精华
     * 之精华之精华之精华之精华之精华之精华之精华之精华之精华之精华之精华之精华
     * //查询所有的log
     *             List<Log> list = logMapper.findList();
     *             //根据每个log在查出每个user用户
     *             List<User> users= new ArrayList<>();
     *             for (Log log : list) {
     *                 //获取log对应的userid
     *                 Integer userid = log.getUserid();
     *                 //根据userid查出对应的user信息
     *                 User user=userMapper.findByid(userid);
     *                 //添加到集合中
     *                 users.add(user);
     *             }
     *             //去重
     *           //List<User> userList = RemoveDuplicate.removeDuplicate(users);
     *     return users;
     *          @Override
     *     public List findTime(List<User> list) {
     *         //根据userid查询出其发表总结的时间
     *         //将每个用户对应的所有总结时间都存入
     *         for (User user : list) {
     *             Integer id = user.getId();
     *             List<Date>time=logMapper.findTime(id);
     *             //将每个用户的所有总结的时间都存入相应的用户
     *             user.setSummaryTime(time);
     *         }
     *         //获取当前毫秒值
     *         long nowTime = System.currentTimeMillis();
     *         //将每个用户中的总结时间与当前毫秒值比较找出最新的一个总结
     *         //最大毫秒值标记
     *         long maxTime=0L;
     *         for (User user : list) {
     *             List<Date> summaryTime = user.getSummaryTime();
     *             //如果每个用户只有一条总结直接获取总结的毫秒值
     *             if (summaryTime.size()==1){
     *                 maxTime = summaryTime.get(0).getTime();
     *             }else if (summaryTime.size()>1){
     *                 //如果每个用户的总结大于1条就比较出最晚写的总结的毫秒值
     *                 for (int i=0;i<summaryTime.size();i++){
     *                     maxTime=summaryTime.get(i).getTime();
     *                     /*if (summaryTime.get(i-1).getTime() < summaryTime.get(i).getTime()){
     *                         maxTime=summaryTime.get(i).getTime();
     *                     }
     *                 }
     *             }
     *             //毫秒值进化到分钟
     *             long l = nowTime - maxTime;
     *             String s = FormatDuring.formatDuring(l);
     *             user.setNewTime(s);
     *         }
     *         return list;
     *     }
     *
     *     @Override
     *     public List<List<Log>> findSummary(List<User> listTime) {
     *         HashMap<Integer, List<Log>> objectObjectHashMap = new HashMap<>();
     *         for (User user : listTime) {
     *             //根据user id 获取所有总结
     *             List<Log> summaryById1 = logMapper.findSummaryById(user.getId());
     *             //存入map集合去重
     *             objectObjectHashMap.put(user.getId(),summaryById1);
     *         }
     *         //遍历
     *         Set<Map.Entry<Integer, List<Log>>> entries = objectObjectHashMap.entrySet();
     *         List<List<Log>>logs=new ArrayList<>();
     *         for (Map.Entry<Integer, List<Log>> entry : entries) {
     *             List<Log> value = entry.getValue();
     *             for (Log log : value) {
     *                 //根据 log id 获取每个总结对应的评论
     *                 List<Comment> logConut = commentMapper.findLogConut(log.getId());
     *                 //将每条总结对应的所有评论都放入其中
     *                 log.setComment(logConut);
     *             }
     *             logs.add(value);
     *         }
     *         return logs;
     *
     *
     * */

    /**
     * 根据id查log
     * @param id
     * @return
     */
    @Override
    public Log findLogById(int id) {

        Log log = logMapper.findLogById(id);
        return log;
    }

    /***
     * 查找登录用户所有总记录，并进行分页展示
     * @param id
     * @return
     */
    @Override
    public PageBean<Log> findLogById(Integer id,int currement,int pageSize) {
        PageHelper.startPage(currement,pageSize);
        //查询全部内容
        List<Log> logList = logMapper.findAllLogById(id);
        //查询总条数
        int countLog = logMapper.countLog(id);
        PageBean<Log> pageBean = new PageBean<>(currement,pageSize,countLog);
        pageBean.setItems(logList);
        return pageBean;
    }

    @Override
    public List<Integer> findUserByDate(String date) {
        return logMapper.findUserByDate(date);
    }

}
