package com.example.androidprojectbtl.Presenter;

public interface ICrud<T, TypeId> {
    void store(T entity);

    void edit(T entity, TypeId id);

    void delete(TypeId id);

    void getAll();

    void getById(TypeId id);
}