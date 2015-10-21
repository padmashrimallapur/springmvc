package com.rest.resources.asm;

import com.entities.Blog;
import com.mvc.AccountController;
import com.mvc.BlogController;
import com.rest.resources.BlogResources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class BlogResourceAsm extends ResourceAssemblerSupport<Blog, BlogResources> {


    public BlogResourceAsm() {
        super(BlogController.class, BlogResources.class);
    }

    @Override
    public BlogResources toResource(Blog blog) {
        BlogResources resource = new BlogResources();

        resource.setTitle(blog.getTitle());
        resource.add(linkTo(BlogController.class).slash(blog.getId()).withSelfRel());
        resource.add(linkTo(BlogController.class).slash(blog.getId()).slash("entries").withRel("entries"));
        if (blog.getOwner() != null)
            resource.add(linkTo(AccountController.class).slash(blog.getOwner().getId()).withRel("owner"));
        return resource;


    }
}
