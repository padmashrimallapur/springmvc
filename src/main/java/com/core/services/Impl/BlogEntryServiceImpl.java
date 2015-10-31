package com.core.services.Impl;

import com.core.models.entities.BlogEntry;
import com.core.respositories.BlogEntryRepo;
import com.core.services.BlogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BlogEntryServiceImpl implements BlogEntryService {

    @Autowired
    private BlogEntryRepo entryRepo;

    @Override
    public BlogEntry findBlogEntry(Long id) {
        return entryRepo.findBlogEntry(id);
    }

    @Override
    public BlogEntry deleteBlogEntry(Long id) {
        return entryRepo.deleteBlogEntry(id);
    }

    @Override
    public BlogEntry updateBlogEntry(Long blogId, BlogEntry blogEntry) {
        return entryRepo.updateBlogEntry(blogId, blogEntry);
    }
}
