package com.rest.resources.asm;

import com.core.models.entities.BlogEntry;
import com.rest.mvc.BlogController;
import com.rest.mvc.BlogEntryController;
import com.rest.resources.BlogEntryResources;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry, BlogEntryResources> {

    public BlogEntryResourceAsm() {
        super(BlogEntryController.class, BlogEntryResources.class);
    }


    @Override
    public BlogEntryResources toResource(BlogEntry blogEntry) {
        BlogEntryResources res = new BlogEntryResources();
        res.setTitle(blogEntry.getTitle());
        res.setContent(blogEntry.getContent());
        Link self = linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel();
        res.add(self);
        if (blogEntry.getBlog() != null) {
            res.add((linkTo(BlogController.class).slash(blogEntry.getBlog().getId()).withRel("blog")));
        }
        return res;
    }
}