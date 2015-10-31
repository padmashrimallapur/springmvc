package com.core.respositories;

import com.core.models.entities.Blog;

import java.util.List;

public interface BlogRepo {
    Blog createBlog(Blog data);

    Blog findBlogByTitle(String title);

    Blog findBlog(Long blogId);

    List<Blog> findAllBlog(Long id);

    List<Blog> findBlogsByAccount(Long AccountId);
}


