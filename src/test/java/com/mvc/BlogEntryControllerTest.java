package com.mvc;

import com.core.services.BlogEntryServices;
import com.entities.BlogEntry;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class BlogEntryControllerTest {

    @InjectMocks
    private BlogEntryController blogEntryController;

    @Mock
    private BlogEntryServices services;


    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(blogEntryController).build();
    }

    @Test
    public void testHomePost() throws Exception {
        mockMvc.perform(post("/home")
                .content("{\"blogEntryList\":[{\"title\":\"vijay and padmashri \"}, {\"title\": \"happy\"}]}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title", is("HAPPY")))
                .andDo(print());
    }

    @Test
    public void blogEntryTest() throws Exception {
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setId(1);
        blogEntry.setTitle("TestTitle");

        when(services.getEntryById(1)).thenReturn(blogEntry);

        mockMvc.perform(get("/getBlogEntryId/1"))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(blogEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("getBlogEntryId/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void blogEntryNotFound() throws Exception {

        mockMvc.perform(get("/getBlogEntryId/123"))
                .andExpect(status().isNotFound());
    }
}