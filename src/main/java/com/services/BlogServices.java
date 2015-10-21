package com.services;

import com.entities.Blog;
import com.entities.BlogEntry;
import com.entities.BlogEntryList;
import com.services.utilitiy.BlogList;

public interface BlogServices {
    BlogEntry createBlogEntry(Long id, BlogEntry data);

    BlogEntryList findAllBlogEntries(Long blogId);

    BlogList findAllBlogs();

    Blog findBlog(Long id);

    BlogEntry deleteBlogEntry(Long blogEntryId);

    BlogEntry updateBlogEntry(Long blogId, BlogEntry blogEntry);
}
