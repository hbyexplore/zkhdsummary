package com.summary.zkhdsummary;

import com.summary.zkhdsummary.bean.Power;

import com.summary.zkhdsummary.mapper.PowerMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkhdsummaryApplicationTests {

    @Autowired
    private PowerMapper powerMapper;
    @Test
    public void contextLoads() {

        Power power = powerMapper.selectByPrimaryKey(1);

        System.out.println(power.getPower());

    }

}
