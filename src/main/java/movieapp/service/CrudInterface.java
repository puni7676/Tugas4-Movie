package movieapp.service;

import java.util.List;

// Interface CRUD untuk memenuhi konsep OOP Interface.
public interface CrudInterface<T> {
    void create(T data) throws Exception;
    List<T> read() throws Exception;
    void update(T data) throws Exception;
    void delete(int id) throws Exception;
}
