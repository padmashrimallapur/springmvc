package com.core.services.Impl;

import com.core.models.entities.Blog;
import com.core.models.entities.BlogEntry;
import com.core.respositories.BlogEntryRepo;
import com.core.respositories.BlogRepo;
import com.core.services.BlogServices;
import com.core.services.exceptions.BlogNotFoundException;
import com.core.services.utilitiy.BlogEntryList;
import com.core.services.utilitiy.BlogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BlogServiceImpl implements BlogServices {

    @Autowired
    BlogRepo blogRepo;

    @Autowired
    BlogEntryRepo blogEntryRepo;

    @Override
    public BlogEntry createBlogEntry(Long id, BlogEntry data) {
        Blog blog = blogRepo.findBlog(id);
        if (blog == null) {
            throw new BlogNotFoundException();
        }
        BlogEntry entry = blogEntryRepo.createBlogEntry(data);
        entry.setBlog(blog);
        return entry;
    }

    @Override
    public BlogEntryList findAllBlogEntries(Long blogId) {
        Blog blog = blogRepo.findBlog(blogId);
        if (blog == null) {
            throw new BlogNotFoundException();
        }
        return new BlogEntryList(blogId, blogEntryRepo.findByBlogId(blogId));
    }

    @Override
    public BlogList findAllBlogs() {
        return null;
    }

    @Override
    public Blog findBlog(Long id) {
        return null;
    }

}
