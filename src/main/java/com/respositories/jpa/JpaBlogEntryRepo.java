package com.respositories.jpa;

import com.entities.BlogEntry;
import com.respositories.BlogEntryRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaBlogEntryRepo implements BlogEntryRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public BlogEntry findBlogEntry(Long id) {
        return null;
    }

    @Override
    public BlogEntry deleteBlogEntry(Long id) {
        return null;
    }

    @Override
    public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
        BlogEntry entry = em.find(BlogEntry.class, id);
        entry.setTitle(data.getTitle());
        entry.setContent(data.getContent());
        return entry;
    }

    @Override
    public BlogEntry createBlogEntry(BlogEntry data) {
        return null;
    }

    @Override
    public List<BlogEntry> findByBlogId(Long blogId) {
        return null;
    }


}
