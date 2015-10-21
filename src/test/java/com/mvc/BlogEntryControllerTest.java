package com.mvc;

import com.entities.BlogEntry;
import com.services.BlogEntryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class BlogEntryControllerTest {

    @InjectMocks
    private BlogEntryController blogEntryController;

    @Mock
    private BlogEntryService services;


    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(blogEntryController).build();
    }

    @Test
    public void blogEntryTest() throws Exception {
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setId(1);
        blogEntry.setTitle("TestTitle");

        when(services.findBlogEntry(1L)).thenReturn(blogEntry);

        mockMvc.perform(get("/rest/blog/1"))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(blogEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/rest/blog/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void blogEntryNotFound() throws Exception {

        mockMvc.perform(get("/rest/blog/123"))
                .andExpect(status().isNotFound());
    }
}