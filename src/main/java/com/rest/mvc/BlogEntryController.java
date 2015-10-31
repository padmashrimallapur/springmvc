package com.rest.mvc;

import com.core.models.entities.BlogEntry;
import com.core.services.BlogEntryService;
import com.core.services.exceptions.BlogNotFoundException;
import com.rest.exceptions.NotFoundException;
import com.rest.resources.BlogEntryResources;
import com.rest.resources.asm.BlogEntryResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;


@Controller
@RequestMapping("/rest/blogEntries")
public class BlogEntryController {

    private BlogEntryService blogEntryServices;

    @Autowired
    public BlogEntryController(BlogEntryService blogEntryServices) {
        this.blogEntryServices = blogEntryServices;
    }


    @RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponseEntity<BlogEntryResources> getBlogEntry(@PathVariable Long blogId) {

        try {
            BlogEntry blogEntry = blogEntryServices.findBlogEntry(blogId);
            if (blogEntry != null) {
                BlogEntryResources resources = new BlogEntryResourceAsm().toResource(blogEntry);
                return new ResponseEntity<>(resources, OK);
            } else {
                return new ResponseEntity<>(NOT_FOUND);
            }

        } catch (BlogNotFoundException exception) {
            throw new NotFoundException(exception);

        }

    }


    @RequestMapping(value = "/{blogEntryId}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<BlogEntryResources> deleteBlogEntry(@PathVariable Long blogEntryId) {
        BlogEntry blogEntry = blogEntryServices.deleteBlogEntry(blogEntryId);

        if (blogEntry != null) {
            BlogEntryResources blogEntryResources = new BlogEntryResourceAsm().toResource(blogEntry);
            return new ResponseEntity<>(blogEntryResources, OK);
        } else {

            return new ResponseEntity<>(NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{blogId}", method = RequestMethod.PUT)
    public
    @ResponseBody
    ResponseEntity<BlogEntryResources> updateBlogEntry(@PathVariable Long blogId, @RequestBody BlogEntryResources sentBlogEntry) {
        BlogEntry updateBlogEntry = blogEntryServices.updateBlogEntry(blogId, sentBlogEntry.toBlogEntry());

        if (updateBlogEntry != null) {

            BlogEntryResources blogEntryResources = new BlogEntryResourceAsm().toResource(updateBlogEntry);
            return new ResponseEntity<>(blogEntryResources, OK);
        } else {

            return new ResponseEntity<>(NOT_FOUND);
        }


    }



}