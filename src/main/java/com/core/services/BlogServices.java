package com.core.services;

import com.core.models.entities.Blog;
import com.core.models.entities.BlogEntry;
import com.core.services.utilitiy.BlogEntryList;
import com.core.services.utilitiy.BlogList;

public interface BlogServices {
    BlogEntry createBlogEntry(Long id, BlogEntry data);

    BlogEntryList findAllBlogEntries(Long blogId);

    BlogList findAllBlogs();

    Blog findBlog(Long id);

}
