package com.jsrom.fenw_items_jrv.services;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.jsrom.fenw_items_jrv.data.ItemEntity;
import com.jsrom.fenw_items_jrv.data.ItemRepository;
import com.jsrom.fenw_items_jrv.services.exceptions.NotFoundException;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public int createItem(ItemEntity itemEntity) {
        return this.itemRepository.save(itemEntity).getId();
    }

    public Stream<ItemEntity> readByName(String name) {
        return this.itemRepository.findByName(name).stream();
    }

    public Stream<ItemEntity> readAll() {
        return this.itemRepository.findAll().stream();
    }

    public ItemEntity readById(Integer id) {
        return this.itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item id " + id));
    }

    public ItemEntity updateItem(Integer id, ItemEntity updateEntity) {
        ItemEntity itemEntity = this.itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item id: " + id));
        itemEntity.setName(updateEntity.getName());
        itemEntity.setDescription(updateEntity.getDescription());
        return this.itemRepository.save(itemEntity);
    }

    public ItemEntity updateDescription(Integer id, String description) {
        ItemEntity itemEntity = this.itemRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Item id: " + id));
        itemEntity.setDescription(description);
        return this.itemRepository.save(itemEntity);
    }

    public void deleteById(Integer id) {
        this.itemRepository.deleteById(id);
    }
}
