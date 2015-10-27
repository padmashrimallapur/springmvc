package com.mvc;

import com.entities.Blog;
import com.entities.BlogEntry;
import com.rest.resources.BlogEntryResources;
import com.rest.resources.BlogListResources;
import com.rest.resources.BlogResources;
import com.rest.resources.asm.BlogEntryResourceAsm;
import com.rest.resources.asm.BlogListResourceAsm;
import com.rest.resources.asm.BlogResourcesAsm;
import com.services.BlogServices;
import com.services.utilitiy.BlogList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/rest/blog")
public class BlogController {
    BlogServices blogServices;

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

    @RequestMapping(value = "/{blogId}/blogEntries", method = RequestMethod.GET)
    public ResponseEntity<BlogEntryResources> creatBlogEntry(@PathVariable Long blogId,
                                                             @RequestBody BlogEntryResources sentBlogEntry) {
        BlogEntry createdBlogEntry = null;

        createdBlogEntry = blogServices.createBlogEntry(blogId, sentBlogEntry.toBlogEntry());

        BlogEntryResources blogEntryResources = new BlogEntryResourceAsm().toResource(createdBlogEntry);
        return new ResponseEntity<>(blogEntryResources, HttpStatus.OK);


    }


    public Class<?> findAllBlogEntries(long blogId) {
        return null;
    }
}
