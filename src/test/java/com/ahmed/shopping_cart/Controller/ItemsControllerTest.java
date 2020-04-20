package com.ahmed.shopping_cart.Controller;


import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ahmed.shopping_cart.Service.ItemService;

import com.ahmed.shopping_cart.data.Item;
import com.ahmed.shopping_cart.model.ItemRequestModel;
import com.ahmed.shopping_cart.model.ItemResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)   //specify spring extension class
@SpringBootTest    //tell spring to load the application context
@AutoConfigureMockMvc  //create and autoConfig Mock MVC
class ItemsControllerTest {
    @MockBean
    private ItemService service;   //Create a mock version of the item service
    @Autowired
    private MockMvc mockMvc;    //Autowire Mock MVC instance into the test class


    @Test
    @DisplayName("GET /products/1  - Found")
    void testGetProductById() throws Exception{

        //Setup our mocked service
        Item mockItem = new Item((long) 1,"book",100,
                "Great book to read; alot of great information",
                  "https://www.pexels.com/photo/blur-book-girl-hands-373465");
        ItemResponseModel item = new ItemResponseModel("book",100,"Great book to read; alot of great information","https://www.pexels.com/photo/blur-book-girl-hands-373465");

        doReturn(item).when(service).getItemById((long) 1);
       //exec get request
        mockMvc.perform(get("/products/{itemID}",1))

                //validate returned fields
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemTitle",is("book")))
                .andExpect(jsonPath("$.itemPrice",is(100.0)))
                .andExpect(jsonPath("$.itemDescription",is("Great book to read; alot of great information")))
                .andExpect(jsonPath("$.pictureUrl",is("https://www.pexels.com/photo/blur-book-girl-hands-373465")));

    }
    @Test
    @DisplayName("POST  /products  - Success")
    void  testPostProduct() throws Exception{
        //Setup our mocked service
        ItemRequestModel postItem = new ItemRequestModel("book",120,"Great book to read when bored","https://www.pexels.com/photo/blur-book-girl-hands-373465");
        Item responseItem = new Item((long) 1,"book",120,"Great book to read when bored","https://www.pexels.com/photo/blur-book-girl-hands-373465");
       // doReturn(responseItem).when(service).addItem(postItem);
        //exec get request
        mockMvc.perform(post("/products"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.itemTitle",is("book")))
                .andExpect(jsonPath("$.itemPrice",is(120.0)))
                .andExpect(jsonPath("$.itemDescription",is("Great book to read when bored")))
                .andExpect(jsonPath("$.pictureUrl",is("https://www.pexels.com/photo/blur-book-girl-hands-373465")));

    }




}