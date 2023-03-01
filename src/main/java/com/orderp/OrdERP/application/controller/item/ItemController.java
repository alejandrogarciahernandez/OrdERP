package com.orderp.OrdERP.application.controller.item;

import com.google.gson.Gson;
import com.orderp.OrdERP.application.service.item.ItemNotFoundException;
import com.orderp.OrdERP.application.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;
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
    public ResponseEntity<Long> createItem(@RequestParam("file") MultipartFile file, @RequestParam("data") String data) throws IOException {
        return new ResponseEntity<>(itemService.create(file, data), HttpStatus.CREATED);
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<Item> updateItem(@RequestBody Item item,@PathVariable Long id) throws ItemNotFoundException {
        return new ResponseEntity<>(itemService.update(item, id), HttpStatus.OK);
    }
}
