package com.company.mz.dao.interfaces;

public interface CrudDao<Entity, Key> {

    void create(Entity entity);
    Entity read(Key key);
    void update(Entity entity);
    void delete(Entity entity);
}
