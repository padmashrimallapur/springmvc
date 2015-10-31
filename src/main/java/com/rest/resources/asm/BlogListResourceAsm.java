package com.rest.resources.asm;

import com.core.services.utilitiy.BlogList;
import com.rest.mvc.BlogController;
import com.rest.resources.BlogListResources;
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
