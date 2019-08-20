package com.yhj.multidb;

import com.yhj.multidb.service.DynamicTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDbApplicationTests {

    @Autowired
    private DynamicTestService dynamicTestService;

    @Test
    public void contextLoads() {
        Assert.isTrue(dynamicTestService != null, "【 dynamicTestService 】不能为 null");
        List<Map<String, Object>> cellDbTest = dynamicTestService.queryCellDbTest();
        cellDbTest.forEach(System.out::println);

        List<Map<String, Object>> mimiDbTest = dynamicTestService.queryMiniDbTest();
        mimiDbTest.forEach(System.out::println);
    }

}
