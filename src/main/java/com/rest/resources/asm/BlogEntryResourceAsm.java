package com.rest.resources.asm;

import com.entities.BlogEntry;
import com.mvc.BlogEntryController;
import com.rest.resources.BlogEntryResources;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry, BlogEntryResources> {

    public BlogEntryResourceAsm() {
        super(BlogEntryController.class, BlogEntryResources.class);
    }

    @Override
    public BlogEntryResources toResource(BlogEntry blogEntry) {
        BlogEntryResources resources = new BlogEntryResources();
        resources.setTitle(blogEntry.getTitle());
        Link link = linkTo(methodOn(BlogEntryController.class).getEntryById(blogEntry.getId())).withSelfRel();
        resources.add(link.withSelfRel());
        return resources;
    }
}
