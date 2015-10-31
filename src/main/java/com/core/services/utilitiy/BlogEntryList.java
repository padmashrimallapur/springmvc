package com.core.services.utilitiy;

import com.core.models.entities.BlogEntry;

import java.util.ArrayList;
import java.util.List;


public class BlogEntryList {

    private List<BlogEntry> blogEntryList = new ArrayList<>();
    private Long blogId;

    public BlogEntryList(Long blogId, List<BlogEntry> blogEntryList) {
        this.blogId = blogId;
        this.blogEntryList = blogEntryList;
    }

    public void setBlogEntryList(List<BlogEntry> blogEntryList) {
        this.blogEntryList = blogEntryList;
    }

    public List<BlogEntry> getBlogEntryList() {
        return blogEntryList;
    }

    public long getBlogId() {
        return blogId;
    }


    public List<BlogEntry> getEntries() {
        return blogEntryList;
    }
}
