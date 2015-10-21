package com.services;

import com.entities.Blog;
import com.entities.BlogEntry;
import com.entities.BlogEntryList;

public interface BlogEntryService {
    BlogEntry findBlogEntry(Long id);

    BlogEntry deleteBlogEntry(Long id);

    BlogEntry updateBlogEntryData(Long id, BlogEntry Data);

    Blog findBlog(Long blogId);


    BlogEntry createBlog(Long blogId, BlogEntry blogEntry);

    BlogEntryList findAllBlogEntries(Long blogId);

    BlogEntry updateBlogEntry(Long blogId, BlogEntry blogEntry);

}
