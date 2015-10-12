package com.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class BlogEntryControllerTest {

    @InjectMocks
    private BlogEntryController blogEntryController;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(blogEntryController).build();
    }

    @Test
    public void testHomePost() throws Exception {
        mockMvc.perform(post("/home")
                .content("{\"blogEntryList\":[{\"title\":\"vijay and padmashri kundi\"}, {\"title\": \"happy\"}]}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("HAPPY")))
                .andDo(print());
    }
}