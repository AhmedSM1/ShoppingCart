package com.ahmed.shopping_cart.Controller;



import javax.validation.Valid;


import com.ahmed.shopping_cart.Service.ItemService;
import com.ahmed.shopping_cart.model.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ItemsController {

    @Autowired
    private ItemService service;
    //add items
    @PostMapping( )
    public ResponseEntity<Item> addItem(@RequestBody Item entry){
        Item item = service.addItem(entry);
        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @GetMapping()
    public ResponseEntity<Iterable<Item>> getAll(){
        Iterable<Item> items = service.getAllItemss();

        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @DeleteMapping("/{itemID}")
    public ResponseEntity<String> deleteItems(@PathVariable("itemID") long id){

        
        String message = service.deleteItem(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);

    }

    @PutMapping()
    public ResponseEntity<Item> updateItems(@Valid @RequestBody Item updated)
    throws ResourceNotFoundException
    
    {
       

          Item item = service.editItem(updated);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

}