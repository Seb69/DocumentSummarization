package com.kyushu.autosum;

import com.kyushu.autosum.configurationlayer.IntegrationTestContextLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by ANDRE on 18/04/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
@ContextConfiguration(classes = Application.class, loader=IntegrationTestContextLoader.class)
@WebAppConfiguration
public class ApplicationTest {

    @Test
    public void contextLoads() {

    }

}
