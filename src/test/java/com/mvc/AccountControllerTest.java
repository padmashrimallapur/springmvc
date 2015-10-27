package com.mvc;

import com.entities.Account;
import com.entities.Blog;
import com.services.AccountService;
import com.services.exceptions.AccountDoesNotExistsException;
import com.services.exceptions.BlogExistsException;
import com.services.utilitiy.BlogList;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class AccountControllerTest {


    @Mock
    AccountService service;

    private MockMvc mockMvc;


    @InjectMocks
    private AccountController accountController;

    private ArgumentCaptor<Account> accountCaptor;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(accountController).build();
        accountCaptor = ArgumentCaptor.forClass(Account.class);
    }


    @Test
    public void testAccountController() throws Exception {
        Account foundAccount = new Account();
        foundAccount.setId(2L);
        foundAccount.setName("viji");
        foundAccount.setPassword("padmashri");

        when(service.findAcount(2L)).thenReturn(foundAccount);

        //http://localhost:8080/rest/account/padmashri.mallapur
        //https://www.facebook.com/padmashri.mallapur
        mockMvc.perform(get("/rest/account/2"))
                .andDo(print());
    }


    @Test
    public void createBlogExistingAccount() throws Exception {
        Blog blog = new Blog();
        blog.setId(1L);
        blog.setTitle("Test Title");

        when(service.createBlog(eq(1L), any(Blog.class))).thenReturn(blog);
        mockMvc.perform(post("/rest/accounts/1/blogs")
                .content("{\"title\" : \"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(jsonPath("$.title", is("Test Title")))
                .andExpect(status().isCreated());
    }

    @Test
    public void createBlogExistingBlogName() throws Exception {
        when(service.createBlog(eq(1L), any(Blog.class))).thenThrow(new BlogExistsException());

        mockMvc.perform(post("/rest/accounts/1/blogs")
                .content("{\"title\" : \"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }


    @Test
    public void createBlogNonExistingAccount() throws Exception {
        when(service.createBlog(eq(1L), any(Blog.class))).thenThrow(new AccountDoesNotExistsException());

        mockMvc.perform(post("/rest/accounts/1/blogs")
                .content("{\"title\" : \"Test Title\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

//    @Test
//    public void createBlogExistingAccount() throws Exception {
//        Blog createdBlog = new Blog();
//        createdBlog.setId(1L);
//        createdBlog.setTitle("Test Title");
//
//        when(service.createBlog(eq(1L), any(Blog.class))).thenReturn(createdBlog);
//
//        mockMvc.perform(post("/rest/accounts/1/blogs")
//                .content("{\"title\":\"Test Title\"}")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(jsonPath("$.title", is("Test Title")))
//                .andExpect(jsonPath("$.links[*].href", hasItem(endsWith("/blogs/1"))))
//                .andExpect(header().string("Location", endsWith("/blogs/1")))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void createBlogNonExistingAccount() throws Exception {
//        when(service.createBlog(eq(1L), any(Blog.class))).thenThrow(new AccountDoesNotExistException());
//
//        mockMvc.perform(post("/rest/accounts/1/blogs")
//                .content("{\"title\":\"Test Title\"}")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void createBlogExistingBlogName() throws Exception {
//        when(service.createBlog(eq(1L), any(Blog.class))).thenThrow(new BlogExistsException());
//
//        mockMvc.perform(post("/rest/accounts/1/blogs")
//                .content("{\"title\":\"Test Title\"}")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isConflict());
//    }
//
//    @Test
//    public void createAccountNonExistingUsername() throws Exception {
//        Account createdAccount = new Account();
//        createdAccount.setId(1L);
//        createdAccount.setPassword("test");
//        createdAccount.setName("test");
//
//        when(service.createAccount(any(Account.class))).thenReturn(createdAccount);
//
//        mockMvc.perform(post("/rest/accounts")
//                .content("{\"name\":\"test\",\"password\":\"test\"}")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(header().string("Location", org.hamcrest.Matchers.endsWith("/rest/accounts/1")))
//                .andExpect(jsonPath("$.name", is(createdAccount.getName())))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    public void createAccountExistingUsername() throws Exception {
//        Account createdAccount = new Account();
//        createdAccount.setId(1L);
//        createdAccount.setPassword("test");
//        createdAccount.setName("test");
//
//        when(service.createAccount(any(Account.class))).thenThrow(new AccountExistsException());
//
//        mockMvc.perform(post("/rest/accounts")
//                .content("{\"name\":\"test\",\"password\":\"test\"}")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isConflict());
//    }
//
//    @Test
//    public void getExistingAccount() throws Exception {
//        Account foundAccount = new Account();
//        foundAccount.setId(1L);
//        foundAccount.setPassword("test");
//        foundAccount.setName("test");
//
//        when(service.findAccount(1L)).thenReturn(foundAccount);
//
//        mockMvc.perform(get("/rest/accounts/1"))
//                .andDo(print())
//                .andExpect(jsonPath("$.password", is(nullValue())))
//                .andExpect(jsonPath("$.name", is(foundAccount.getName())))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void getNonExistingAccount() throws Exception {
//        when(service.findAccount(1L)).thenReturn(null);
//
//        mockMvc.perform(get("/rest/accounts/1"))
//                .andExpect(status().isNotFound());
//    }


    @Test
    public void createAccountForNonExistingUser() throws Exception {
        Account createAccount = new Account();
        createAccount.setId(1L);
        createAccount.setName("Test");
        createAccount.setPassword("Test");

        when(service.creatAccount(any(Account.class))).thenReturn(createAccount);

        mockMvc.perform(post("/rest/accounts")
                .content("{\"name\" : \"Test\", \"password\" : \"Test\" }")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", endsWith("/rest/accounts/1")))
                .andExpect(jsonPath("$.name", is(createAccount.getName())))
                .andExpect(status().isCreated());

        verify(service).creatAccount(accountCaptor.capture());

        String password = accountCaptor.getValue().getName();
        assertEquals("Test", password);

    }

    @Test
    public void getExistingAccount() throws Exception {
        Account foundAccount = new Account();
        foundAccount.setId(1L);
        foundAccount.setPassword("Test");
        foundAccount.setName("NameTest");

        when(service.findAcount(1L)).thenReturn(foundAccount);

        mockMvc.perform(get("/rest/accounts/1"))
                .andDo(print())
                .andExpect(jsonPath("$.password", is(foundAccount.getPassword())))
                .andExpect(jsonPath("$.name", is(foundAccount.getName())))
                .andExpect(status().isOk());
    }

    @Test
    public void getNonExistingAccount() throws Exception {
        when(service.findAcount(1L)).thenReturn(null);
        mockMvc.perform(get("/rest/accounts/1")).andExpect(status().isNotFound());
    }


    @Test
    public void findAllBlogsForAccount() throws Exception {
        List<Blog> list = new ArrayList<>();

        Blog blogA = new Blog();
        blogA.setId(1L);
        blogA.setTitle("Title A");
        list.add(blogA);

        Blog blogB = new Blog();
        blogB.setId(2L);
        blogB.setTitle("Title B");

        list.add(blogB);

        BlogList blogList = new BlogList(list);

        when(service.findBlogsByAccount(1L)).thenReturn(blogList);

        //noinspection unchecked
        mockMvc.perform(get("/rest/accounts/1/blogs"))
                .andExpect(jsonPath("$.blogs[*].title",
                        hasItems(Matchers.endsWith("Title A"), Matchers.endsWith("Title B"))))
                .andExpect(status().isOk());

    }

}