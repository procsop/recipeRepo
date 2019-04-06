package com.recipe.controller;

import com.recipe.entity.User;
import com.recipe.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Random;

/**
 * Testclass for HomeController.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    /**
     * Test redirecting to index.
     * TODO: FAILED.
     * @throws Exception
     */
    @Test
    public void rootShouldRedirectToIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    /**
     * Test authentication. Mandatory to be fulfilled if user exist.
     */
    @Test
    public void authenticationShouldSucceed() throws Exception {
        User user = new User();
        user.setFullName("Doors");
        user.setEmail("neduabi@pm.me");
        user.setPassword("123456");
        user.setId(new Random().nextLong());
        user.addRoles("ADMIN");
        
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(user,null);
        SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);

        mockMvc.perform(get("/login")
                .principal(testingAuthenticationToken))
                .andExpect(status().isOk());
                //.andExpect(model().attribute("user", is(user.getEmail())));
    }

    /**
     * Test if non existing page was called. Should be passed.
     * @throws Exception
     */
    @Test
    public void nonExistingPageShouldReturnClientError() throws Exception {
        mockMvc.perform(get("/hulyeseg"))
                .andExpect(status().is4xxClientError());
    }

}