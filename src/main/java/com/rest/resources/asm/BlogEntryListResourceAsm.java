package com.rest.resources.asm;

import com.core.services.utilitiy.BlogEntryList;
import com.rest.mvc.BlogController;
import com.rest.resources.BlogEntryListResources;
import com.rest.resources.BlogEntryResources;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class BlogEntryListResourceAsm extends ResourceAssemblerSupport<BlogEntryList, BlogEntryListResources> {

    public BlogEntryListResourceAsm() {
        super(BlogController.class, BlogEntryListResources.class);
    }

    @Override
    public BlogEntryListResources toResource(BlogEntryList list) {
        List<BlogEntryResources> resources = new BlogEntryResourceAsm().toResources(list.getEntries());
        BlogEntryListResources listResource = new BlogEntryListResources();
        listResource.setEntries(resources);
        listResource.add(linkTo(methodOn(BlogController.class).findAllBlogEntries(list.getBlogId())).withSelfRel());
        return listResource;
    }
}

