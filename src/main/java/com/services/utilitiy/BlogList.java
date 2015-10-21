package com.services.utilitiy;

import com.entities.Blog;

import java.util.ArrayList;
import java.util.List;

public class BlogList {

    private List<Blog> blogs = new ArrayList<>();

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    public BlogList(List<Blog> blogs) {
        this.blogs = blogs;
    }

}
