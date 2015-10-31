package com.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class BlogEntryListResources extends ResourceSupport {

    private List<BlogEntryResources> entries;

    public List<BlogEntryResources> getEntries() {
        return entries;
    }

    public void setEntries(List<BlogEntryResources> entries) {
        this.entries = entries;
    }


}
