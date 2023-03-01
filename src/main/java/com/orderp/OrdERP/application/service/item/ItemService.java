package com.orderp.OrdERP.application.service.item;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.orderp.OrdERP.application.controller.category.Category;
import com.orderp.OrdERP.application.controller.item.Item;
import com.orderp.OrdERP.application.dao.category.CategoryEntity;
import com.orderp.OrdERP.application.dao.category.CategoryRepository;
import com.orderp.OrdERP.application.dao.item.ItemEntity;
import com.orderp.OrdERP.application.dao.item.ItemMapper;
import com.orderp.OrdERP.application.dao.item.ItemRepository;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemMapper itemMapper;

    public List<Item> findAll() {
        return this.itemMapper.itemEntitiesToItems(itemRepository.findAll());
    }

    public Long create(MultipartFile file, String data) throws IOException {
        String url = imageUploadService.saveImage(file);
        if(url != null && !url.isEmpty()) {
            Item item = new Gson().fromJson(data, Item.class);
            // TODO: Refactor
            JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
            item.setCategory(new Category(jsonObject.get("categoryId").getAsLong(), null));
            item.setImgRoute(url);
            return this.itemRepository.save(itemMapper.itemToItemEntity(item)).getId();
        }
        throw new FileUploadException("There was a problem uploading the file");
    }

    public Item update(Item item, Long id) throws ItemNotFoundException {
        ItemEntity existingItem = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Item not found"));

        // Update fields that are not null in the new item
        if (item.getCurrency() != null) {
            existingItem.setCurrency(item.getCurrency());
        }
        if (item.getPrice() != null) {
            existingItem.setPrice(item.getPrice());
        }
        if (item.getImgRoute() != null) {
            existingItem.setImgRoute(item.getImgRoute());
        }
        if (item.getTitle() != null) {
            existingItem.setTitle(item.getTitle());
        }
        if (item.getDescription() != null) {
            existingItem.setDescription(item.getDescription());
        }
        if (item.getCategory() != null) {
            CategoryEntity category = categoryRepository.findById(item.getCategory().getCategoryId()).orElseThrow(() -> new EntityNotFoundException("Category not found"));
            existingItem.setCategory(category);
        }

        return this.itemMapper.itemEntityToItem(itemRepository.save(existingItem));
    }
}
