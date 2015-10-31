package com.core.services;

import com.core.models.entities.BlogEntry;

public interface BlogEntryService {
    BlogEntry findBlogEntry(Long id);

    BlogEntry deleteBlogEntry(Long id);

    BlogEntry updateBlogEntry(Long blogId, BlogEntry blogEntry);

}
