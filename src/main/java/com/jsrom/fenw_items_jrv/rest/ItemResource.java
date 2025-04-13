package com.jsrom.fenw_items_jrv.rest;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsrom.fenw_items_jrv.data.ItemEntity;
import com.jsrom.fenw_items_jrv.rest.dtos.ItemDto;
import com.jsrom.fenw_items_jrv.services.ItemService;
import com.jsrom.fenw_items_jrv.services.exceptions.BadRequestException;

@RestController
@RequestMapping(ItemResource.ITEMS)
public class ItemResource {
    public static final String ITEMS = "items";
    public static final String ID = "/{id}";
    public static final String SEARCH = "/search";
    private ItemService itemService;

    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Integer createItem(@RequestBody ItemDto itemDto) {
        ItemEntity itemEntity = new ItemEntity(
                itemDto.getName(),
                itemDto.getDescription());
        return itemService.createItem(itemEntity);
    }

    @GetMapping(SEARCH)
    public Stream<ItemDto> find(@RequestParam String q) {
        String name = new LexicalAnalyzer().extractWithAssure(q, "name");
        return itemService.readByName(name).map(ItemDto::new);
    }

    @GetMapping
    public Stream<ItemDto> findAll() {
        return itemService.readAll().map(ItemDto::new);
    }

    @GetMapping(ID)
    public ItemDto readById(@PathVariable Integer id) {
        return new ItemDto(itemService.readById(id));
    }

    @PutMapping(ID)
    public ItemDto putItem(@PathVariable Integer id, @RequestBody ItemDto itemDto) {
        ItemEntity itemEntity = new ItemEntity(
                itemDto.getName(),
                itemDto.getDescription());
        return new ItemDto(itemService.updateItem(id, itemEntity));
    }

    @PatchMapping(ID)
    public ItemDto updateDescription(@PathVariable int id, @RequestBody ItemDto itemDto) {
        return new ItemDto(itemService.updateDescription(id, itemDto.getDescription()));
    }

    @DeleteMapping(ID)
    public void deleteItem(@PathVariable int id) {
        itemService.deleteById(id);
    }

}
