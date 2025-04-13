package com.jsrom.fenw_items_jrv.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity,Integer>  {
    public List<ItemEntity> findByName(String name);
    
}
