package com.rest.mvc;

import com.core.models.entities.Blog;
import com.core.models.entities.BlogEntry;
import com.core.services.BlogServices;
import com.core.services.exceptions.BlogNotFoundException;
import com.core.services.utilitiy.BlogEntryList;
import com.core.services.utilitiy.BlogList;
import com.rest.exceptions.NotFoundException;
import com.rest.resources.BlogEntryListResources;
import com.rest.resources.BlogEntryResources;
import com.rest.resources.BlogListResources;
import com.rest.resources.BlogResources;
import com.rest.resources.asm.BlogEntryListResourceAsm;
import com.rest.resources.asm.BlogEntryResourceAsm;
import com.rest.resources.asm.BlogListResourceAsm;
import com.rest.resources.asm.BlogResourcesAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

@Controller
@RequestMapping(value = "/rest/blog")
public class BlogController {
    BlogServices blogServices;

    @Autowired
    public BlogController(BlogServices blogServices) {
        this.blogServices = blogServices;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<BlogListResources> findAllBlogs() {
        BlogList blogList = blogServices.findAllBlogs();
        BlogListResources blogListResources = new BlogListResourceAsm().toResource(blogList);
        return new ResponseEntity<>(blogListResources, HttpStatus.OK);
    }

    @RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
    public ResponseEntity<BlogResources> getBlog(@PathVariable Long blogId) {
        Blog blog = blogServices.findBlog(blogId);
        BlogResources resources = new BlogResourcesAsm().toResource(blog);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/{blogId}/blogEntries", method = RequestMethod.POST)
    public ResponseEntity<BlogEntryResources> creatBlogEntry(@PathVariable Long blogId,
                                                             @RequestBody BlogEntryResources sentBlogEntry) {
        BlogEntry createdBlogEntry;
        try {
            createdBlogEntry = blogServices.createBlogEntry(blogId, sentBlogEntry.toBlogEntry());
            BlogEntryResources blogEntryResources = new BlogEntryResourceAsm().toResource(createdBlogEntry);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(blogEntryResources.getLink("self").getHref()));
            return new ResponseEntity<>(blogEntryResources, headers, HttpStatus.CREATED);
        } catch (BlogNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @RequestMapping(value = "/{blogId}/blogEntries")
    public ResponseEntity<BlogEntryListResources> findAllBlogEntries(@PathVariable long blogId) {
        try {
            BlogEntryList list = blogServices.findAllBlogEntries(blogId);
            BlogEntryListResources res = new BlogEntryListResourceAsm().toResource(list);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (BlogNotFoundException exception) {
            throw new NotFoundException(exception);
        }
    }
}
