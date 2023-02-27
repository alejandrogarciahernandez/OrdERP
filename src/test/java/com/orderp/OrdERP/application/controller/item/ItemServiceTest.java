package com.orderp.OrdERP.application.controller.item;

import com.orderp.OrdERP.application.controller.category.Category;
import com.orderp.OrdERP.application.dao.category.CategoryEntity;
import com.orderp.OrdERP.application.dao.category.CategoryMapper;
import com.orderp.OrdERP.application.dao.category.CategoryRepository;
import com.orderp.OrdERP.application.dao.item.ItemEntity;
import com.orderp.OrdERP.application.dao.item.ItemMapper;
import com.orderp.OrdERP.application.dao.item.ItemMapperImpl;
import com.orderp.OrdERP.application.dao.item.ItemRepository;
import com.orderp.OrdERP.application.service.item.ItemNotFoundException;
import com.orderp.OrdERP.application.service.item.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ItemMapperImpl.class, ItemService.class})
public class ItemServiceTest {

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemMapper itemMapper;



    @Test
    void getItems(){
        Item itemSample = new Item(1L, "$", 19.99d, "img/imgRoute/img.jpg", "title", "description", new Category(1l, "category1"));
        List<Item> itemList = new ArrayList<>();
        itemList.add(itemSample);
        when(this.itemRepository.findAll()).thenReturn(itemMapper.itemsToItemEntities(itemList));
        List<Item> itemListResult = itemService.findAll();

        assertEquals(itemSample.getCurrency(), itemListResult.get(0).getCurrency());
        assertEquals(itemSample.getTitle(), itemListResult.get(0).getTitle());
        assertEquals(itemSample.getDescription(), itemListResult.get(0).getDescription());
    }

    @Test
    void insertItem(){
        Item item = new Item(null, "$", 19.99d, "imgRoute", "title", "description", new Category(1l, "category1"));
        Item itemResult = new Item();
        itemResult.setId(1l);
        when(itemRepository.save(Mockito.any())).thenReturn(itemMapper.itemToItemEntity(itemResult));

        Long result = itemService.create(item);

        assertEquals(1l, result);
    }

    @Test
    void updateItem() throws ItemNotFoundException {
        ArgumentCaptor<ItemEntity> argument = ArgumentCaptor.forClass(ItemEntity.class);

        ItemEntity item = new ItemEntity(1L, "$", 19.99d, "imgRoute", "title", "description", new CategoryEntity());
        Item itemResult = new Item(1L, "$", 19.99d, "imgRoute", "title", "description", new Category(1l, "category1"));
        CategoryEntity categoryEntity = new CategoryEntity(1l, "TEST");
        when(itemRepository.findById(Mockito.any())).thenReturn(Optional.of(item));
        when(itemRepository.save(Mockito.any())).thenReturn(itemMapper.itemToItemEntity(itemResult));
        when(categoryRepository.findById(Mockito.any())).thenReturn(Optional.of(categoryEntity));
        Item result = itemService.update(itemResult, 1l);

        Mockito.verify(itemRepository).save(argument.capture());

        assertEquals(item.getId(), argument.getValue().getId());
        assertEquals(item.getCurrency(), argument.getValue().getCurrency());
        assertEquals(item.getDescription(), argument.getValue().getDescription());
        assertEquals(itemResult, result);
    }
}
