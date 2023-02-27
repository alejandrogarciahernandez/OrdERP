package com.orderp.OrdERP.application.controller.item;

import com.orderp.OrdERP.application.service.item.ItemNotFoundException;
import com.orderp.OrdERP.application.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/item")
    public ResponseEntity<Long> createItem(@RequestBody Item item) {
        return new ResponseEntity<>(itemService.create(item), HttpStatus.CREATED);
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<Item> updateItem(@RequestBody Item item,@PathVariable Long id) throws ItemNotFoundException {
        return new ResponseEntity<>(itemService.update(item, id), HttpStatus.OK);
    }
}
