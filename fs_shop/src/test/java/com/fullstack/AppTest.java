package com.fullstack;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fullstack.App;


@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(classes = App.class,initializers = ConfigFileApplicationContextInitializer.class)
public class AppTest {
   
}
