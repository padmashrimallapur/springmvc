package com.rest.resources.asm;

import com.mvc.BlogController;
import com.rest.resources.BlogListResources;
import com.services.utilitiy.BlogList;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class BlogListResourceAsm extends ResourceAssemblerSupport<BlogList, BlogListResources> {


    public BlogListResourceAsm() {
        super(BlogController.class, BlogListResources.class);
    }

    @Override
    public BlogListResources toResource(BlogList blogList) {
        BlogListResources res = new BlogListResources();
        res.setBlogs(new BlogResourceAsm().toResources(blogList.getBlogs()));
        return res;
    }


}
