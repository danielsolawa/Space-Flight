package com.danielsolawa.spaceflight.service;


import java.util.List;


public interface BasicService <T>{

    // Creates a new object.
    void create(T t);

    // Returns an object with the given id.
    T getById(Long id);

    // Returns a list of all objects.
    List<T> getAll();

    //Updates the object.
    void update(T t, Long id);

    //Deletes the object with the given id.
    void delete(Long id);







}
