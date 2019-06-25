package com.danielsolawa.spaceflight.service;


import java.util.List;


public interface BasicService <T>{


    // Returns an object with the given id.
    T getById(Long id);

    // Returns a list of all objects.
    List<T> getAll();

    //Deletes the object with the given id.
    void delete(Long id);







}
