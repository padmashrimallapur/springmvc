package com.core.respositories.jpa;

import com.core.models.entities.Blog;
import com.core.respositories.BlogRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaBlogRepo implements BlogRepo {

    @PersistenceContext
    EntityManager em;


    @Override
    public Blog createBlog(Blog data) {
        em.persist(data);
        return data;
    }

    @Override
    public Blog findBlogByTitle(String title) {
        Query query = em.createQuery("SELECT b from Blog b where b.title=?1");
        query.setParameter(1, title);
        List<Blog> blogs = query.getResultList();
        if (blogs.isEmpty()) {
            return null;
        } else {
            return blogs.get(0);
        }

    }

    @Override
    public Blog findBlog(Long blogId) {
        return em.find(Blog.class, blogId);

    }

    @Override
    public List<Blog> findAllBlog(Long id) {
        Query query = em.createQuery("SELECT b from Blog b");
        return query.getResultList();
    }

    @Override
    public List<Blog> findBlogsByAccount(Long accountId) {
        Query query = em.createQuery("SELECT b from Blog b where b.owner.id=?1");
        query.setParameter(1, accountId);
        return query.getResultList();

    }
}
