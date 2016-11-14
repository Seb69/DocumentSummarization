package com.kyushu.autosum.configurationlayer;

import com.kyushu.autosum.Application;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Abstract configurationlayer : Integration test
 *
 * Set Junit4 Runner
 * Set Spring context
 * Reload context every test
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = ApplicationTest.class)
@ContextConfiguration(classes = Application.class, loader=IntegrationTestContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@WebAppConfiguration
public class AbstractIntegrationTest {
}
