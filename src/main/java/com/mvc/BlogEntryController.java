package com.mvc;

import com.entities.BlogEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;


class BlogEntryList {

    private List<BlogEntry> blogEntryList;

    public void setBlogEntryList(List<BlogEntry> blogEntryList) {
        this.blogEntryList = blogEntryList;
    }

    public List<BlogEntry> getBlogEntryList() {
        return blogEntryList;
    }

}

@Controller
public class BlogEntryController {

    @RequestMapping(value = "/home", method = POST)
    public
    @ResponseBody
    BlogEntry getBlogEntry(@RequestBody BlogEntryList blogEntry) {
        List<BlogEntry> blogEntryList = blogEntry.getBlogEntryList();
        blogEntryList.get(0).setTitle(blogEntryList.get(1).getTitle().toUpperCase());
        return blogEntryList.get(0);
    }
}
