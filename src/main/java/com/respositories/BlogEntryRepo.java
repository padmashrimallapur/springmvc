package com.respositories;

import com.entities.BlogEntry;

import java.util.List;

public interface BlogEntryRepo {

    BlogEntry findBlogEntry(Long id); // Returns the BlogEntry or null if it can't be found

    BlogEntry deleteBlogEntry(Long id); // Deletes the found BlogEntry or returns null if it can't be found

    BlogEntry updateBlogEntry(Long id, BlogEntry data);

    BlogEntry createBlogEntry(BlogEntry data);

    List<BlogEntry> findByBlogId(Long blogId);
}
