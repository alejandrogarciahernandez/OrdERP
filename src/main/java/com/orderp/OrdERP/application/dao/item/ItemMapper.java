package com.orderp.OrdERP.application.dao.item;

import com.orderp.OrdERP.application.controller.item.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {

    ItemEntity itemToItemEntity(Item item);
    Item itemEntityToItem(ItemEntity item);
    List<ItemEntity> itemsToItemEntities(List<Item> items);
    List<Item> itemEntitiesToItems(List<ItemEntity> itemEntities);
}
