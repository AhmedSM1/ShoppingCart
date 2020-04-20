package com.ahmed.shopping_cart.Controller;



import javax.validation.Valid;


import com.ahmed.shopping_cart.Service.ItemService;
import com.ahmed.shopping_cart.data.Item;

import com.ahmed.shopping_cart.model.ItemRequestModel;
import com.ahmed.shopping_cart.model.ItemResponseModel;
import com.ahmed.shopping_cart.model.ItemUpdateModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @GetMapping
    public ResponseEntity<List<ItemResponseModel>> getAllItems(){
        return new ResponseEntity<>(service.getAllItems(), HttpStatus.OK);
    }

    @DeleteMapping("/{itemID}")
    public ResponseEntity<String> deleteItems(@PathVariable("itemID") long id){
        String message = service.deleteItem(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(message);

    }

    @PutMapping(value ="/{itemID}" )
    public ResponseEntity<ItemResponseModel> updateItems(@PathVariable("itemID") long id ,@Valid @RequestBody ItemUpdateModel updated)
    {
       ItemResponseModel res = service.editItem(id,updated);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

}