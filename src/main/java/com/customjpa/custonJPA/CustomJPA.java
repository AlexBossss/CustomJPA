package com.customjpa.custonJPA;

import java.util.List;

@JPARepo
public interface CustomJPA <T, ID> {
   List<T> findAll();
   T findById(ID Id);
}
