package com.rest.resources.asm;

import com.core.models.entities.Blog;
import com.rest.mvc.AccountController;
import com.rest.mvc.BlogController;
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
        resource.add(linkTo(BlogController.class).slash(blog.getId()).slash("blogEntries").withRel("entries"));
        if (blog.getOwner() != null)
            resource.add(linkTo(AccountController.class).slash(blog.getOwner().getId()).withRel("owner"));
        return resource;
    }
}
