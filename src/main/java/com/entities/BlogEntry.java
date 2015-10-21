package com.entities;

public class BlogEntry {
    private String title;
    private long id;
    private Blog blog;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    @Override
    public String toString() {
        return "BlogEntry{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", blog=" + blog +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntry blogEntry = (BlogEntry) o;

        if (id != blogEntry.id) return false;
        //noinspection SimplifiableIfStatement
        if (title != null ? !title.equals(blogEntry.title) : blogEntry.title != null) return false;
        return !(blog != null ? !blog.equals(blogEntry.blog) : blogEntry.blog != null);

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (blog != null ? blog.hashCode() : 0);
        return result;
    }
}
