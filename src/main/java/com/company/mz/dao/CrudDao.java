package com.company.mz.dao;

public interface CrudDao<Entity, Key, UpdatedValue> {

    void create(Entity entity);
    Entity read(Key key);
    void update(Entity entity, UpdatedValue updatedValue );
    void delete(Entity entity);
}
