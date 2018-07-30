package com.summary.zkhdsummary;

import com.summary.zkhdsummary.bean.power;
import com.summary.zkhdsummary.mapper.powerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkhdsummaryApplicationTests {
    @Autowired
    private powerMapper powerMapper;
    @Test
    public void contextLoads() {

        power power = powerMapper.selectByPrimaryKey(1);

        System.out.println(power.getPower());

    }

}
