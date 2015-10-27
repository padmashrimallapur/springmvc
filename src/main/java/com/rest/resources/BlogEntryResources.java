package com.rest.resources;

import com.entities.Blog;
import com.entities.BlogEntry;
import org.springframework.hateoas.ResourceSupport;

public class BlogEntryResources extends ResourceSupport {
    private String title;
    private String content;


    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BlogEntry toBlogEntry() {
        return null;
    }

    public Blog toBlog() {
        Blog blog = new Blog();
        blog.setTitle(this.getTitle());
        return blog;
    }

    @Override
    public String toString() {
        return "BlogEntryResources{" +
                "title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        BlogEntryResources resources = (BlogEntryResources) o;

        return !(title != null ? !title.equals(resources.title) : resources.title != null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }


}
