package com.rest.mvc;

import com.core.models.entities.BlogEntry;
import com.core.services.BlogServices;
import com.core.services.exceptions.BlogNotFoundException;
import com.core.services.utilitiy.BlogEntryList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BlogControllerTest {

    @InjectMocks
    private BlogController controller;

    @Mock
    private BlogServices services;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void listBlogEntriesForNonExistingBlog() throws Exception {
        when(services.findAllBlogEntries(1L)).thenThrow(new BlogNotFoundException());
        mockMvc.perform(get("/rest/blog/1/blogEntries"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void listBlogEntriesForExistingBlogs() throws Exception {
        BlogEntry blogEntryA = new BlogEntry();
        blogEntryA.setId(1L);
        blogEntryA.setTitle("Test Title");

        BlogEntry blogEntryB = new BlogEntry();
        blogEntryB.setId(2L);
        blogEntryB.setTitle("Test Title");

        List<BlogEntry> blogListing = new ArrayList<>();
        blogListing.add(blogEntryA);
        blogListing.add(blogEntryB);

        BlogEntryList list = new BlogEntryList(1l, blogListing);

        when(services.findAllBlogEntries(1L)).thenReturn(list);

        mockMvc.perform(get("/rest/blog/1/blogEntries"))
                .andDo(print())
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blog/1/blogEntries"))))
                .andExpect(jsonPath("$.entries[*].title", hasItem(is("Test Title"))))
                .andExpect(status().isOk());
    }
}
