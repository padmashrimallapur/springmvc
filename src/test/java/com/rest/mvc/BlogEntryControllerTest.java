package com.rest.mvc;

import com.core.models.entities.Blog;
import com.core.models.entities.BlogEntry;
import com.core.services.BlogEntryService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

        mockMvc.perform(get("/rest/blogEntries/1"))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(blogEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/rest/blogEntries/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void blogEntryNotFound() throws Exception {

        mockMvc.perform(get("/rest/blog/123"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void getExistingBlogEntry() throws Exception {
        BlogEntry blogEntry = new BlogEntry();
        blogEntry.setTitle("BlogEntry A");
        blogEntry.setId(1L);

        Blog blog = new Blog();
        blog.setId(1L);

        blogEntry.setBlog(blog);

        when(services.findBlogEntry(1L)).thenReturn(blogEntry);

        mockMvc.perform(get("/rest/blogEntries/1"))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(blogEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href",
                        hasItems(Matchers.endsWith("/blog/1"), Matchers.endsWith("/blogEntries/1"))))
                .andExpect(jsonPath("$.links[*].rel", hasItems(is("self"), is("blog"))))
                .andExpect(status().isOk());
    }


    @Test
    public void getNonExistingBlogEntry() throws Exception {
        when(services.findBlogEntry(1L)).thenReturn(null);

        mockMvc.perform(get("/rest/blogEntries/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteExistingBlogEntry() throws Exception {
        BlogEntry deletedBlogEntry = new BlogEntry();
        deletedBlogEntry.setId(1L);
        deletedBlogEntry.setTitle("Title title");

        when(services.deleteBlogEntry(1L)).thenReturn(deletedBlogEntry);

        mockMvc.perform(delete("/rest/blogEntries/1"))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(deletedBlogEntry.getTitle())))
                .andExpect(jsonPath("$.links[*].href", hasItem(Matchers.endsWith("/blogEntries/1"))))
                .andExpect(status().isOk());
    }

    @Test
    public void updateExistingBlogEntry() throws Exception {
        BlogEntry updatedBlogEntry = new BlogEntry();
        updatedBlogEntry.setId(1L);
        updatedBlogEntry.setTitle("Title title");
        updatedBlogEntry.setContent("new content");

        when(services.updateBlogEntry(eq(1L), any(BlogEntry.class)))
                .thenReturn(updatedBlogEntry);

        mockMvc.perform(put("/rest/blogEntries/1")
                .content("{\"Title \" : \"Title title\" ,\"content\" : \"new content\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(updatedBlogEntry.getTitle())))
                .andExpect(jsonPath("$.content", is("new content")))
                .andExpect(jsonPath("$.links[*].href", hasItem(Matchers.endsWith("/blogEntries/1"))))
                .andExpect(status().isOk());

    }

    @Test
    public void updateNonExistingBlogEntry() throws Exception {
        when(services.updateBlogEntry(eq(1L), any(BlogEntry.class)))
                .thenReturn(null);

        mockMvc.perform(put("/rest/blogEntries/1")
                .content("{\"title\" : \"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }








}