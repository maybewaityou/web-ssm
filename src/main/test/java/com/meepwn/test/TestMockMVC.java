package com.meepwn.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
@WebAppConfiguration
public class TestMockMVC {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        // 初始化一个MockMVC对象的方式有两种：单独设置、web应用上下文设置
        // 建议使用Web应用上下文设置
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void mockMVC() throws Exception {
        System.out.println("======");
//        mock.perform(post("/test.do")).andExpect(MockMvcResultMatchers.status().isOk());
    }

    public RequestBuilder post(String url) {
        return MockMvcRequestBuilders.post(url);
    }

    public RequestBuilder post(String url, MultiValueMap<String, String> params) {
        return MockMvcRequestBuilders.post(url).params(params);
    }

}
