package com.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class BlogListResources extends ResourceSupport {
    private List<BlogResources> blogs = new ArrayList<>();


    public void setBlogs(List<BlogResources> blogResources) {
        this.blogs = blogResources;
    }

    public List<BlogResources> getBlogs() {
        return blogs;
    }
}
