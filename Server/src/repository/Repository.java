/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;

/**
 *
 * @author Nikolina
 */
public interface Repository<T> {
    
    List<T> get(T t) throws Exception;
    T create (T t) throws Exception;
    T update (T t) throws Exception;
    boolean delete (T t) throws Exception;
    T getById (T t) throws Exception;

   
}
