package com.mvc;

import com.core.services.BlogEntryServices;
import com.entities.BlogEntry;
import com.entities.BlogEntryList;
import com.rest.resources.BlogEntryResources;
import com.rest.resources.asm.BlogEntryResourceAsm;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class BlogEntryController {

    private BlogEntryServices blogEntryServices;

    public BlogEntryController(BlogEntryServices blogEntryServices) {
        this.blogEntryServices = blogEntryServices;
    }

    @RequestMapping(value = "/home", method = POST)
    public
    @ResponseBody
    BlogEntry getBlogEntry(@RequestBody BlogEntryList blogEntry) {
        List<BlogEntry> blogEntryList = blogEntry.getBlogEntryList();
        blogEntryList.get(0).setTitle(blogEntryList.get(1).getTitle().toUpperCase());
        return blogEntryList.get(0);
    }

    @RequestMapping(value = "/getBlogEntryId/{id}", method = GET)
    public
    @ResponseBody
    ResponseEntity<BlogEntryResources> getEntryById(@PathVariable long id) {
        BlogEntry entryById = blogEntryServices.getEntryById(id);
        if (entryById != null) {
            BlogEntryResources resources = new BlogEntryResourceAsm().toResource(entryById);
            return new ResponseEntity<>(resources, OK);
        } else {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }
}