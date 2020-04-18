package com.ahmed.shopping_cart.Controller;



import javax.validation.Valid;


import com.ahmed.shopping_cart.Service.ItemService;
import com.ahmed.shopping_cart.data.Item;

import com.ahmed.shopping_cart.model.ItemRequestModel;
import com.ahmed.shopping_cart.model.ItemResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ItemsController {

    @Autowired
    private ItemService service;
    //add items
    @PostMapping
    public ResponseEntity<ItemResponseModel> addItem(@RequestBody ItemRequestModel entry){
        this.service.addItem(entry);
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ItemResponseModel dto = modelMapper.map(entry,ItemResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping(value = ("/{itemID}"))
    public ResponseEntity<ItemResponseModel> getItemById(@PathVariable("itemID") Long id){
        ItemResponseModel res = this.service.getItemById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }



    @GetMapping
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