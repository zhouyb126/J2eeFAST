package com.framework;


import com.framework.service.DataSourceTestService;
import com.j2eefast.framework.sys.entity.SysUserEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicDataSourceTest {
    @Autowired
    private DataSourceTestService dataSourceTestService;

    @Test
    public void test(){
        //数据源1
        SysUserEntity user1 = dataSourceTestService.queryUser(1L);
        System.out.println(user1.toString());

        //数据源2
        SysUserEntity user2 = dataSourceTestService.queryUser2(1L);
        System.out.println(user2.toString());

        //数据源1
        SysUserEntity user3 = dataSourceTestService.queryUser(1L);
        System.out.println(user3.toString());
    }

}
