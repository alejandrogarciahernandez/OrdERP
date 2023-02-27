package com.orderp.OrdERP.application.controller.category;

import com.orderp.OrdERP.application.controller.item.Item;
import com.orderp.OrdERP.application.service.category.CategoryService;
import com.orderp.OrdERP.application.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    //@CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Category>> getAllItems() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<Long> createCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
    }
}
