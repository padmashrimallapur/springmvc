package com.rest.resources;

import com.core.models.entities.Blog;
import org.springframework.hateoas.ResourceSupport;

public class BlogResources extends ResourceSupport {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Blog toBlog() {
        Blog blog = new Blog();
        blog.setTitle(title);
        return blog;
    }


}
