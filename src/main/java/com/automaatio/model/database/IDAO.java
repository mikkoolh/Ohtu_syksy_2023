package com.automaatio.model.database;

import java.util.List;

public interface IDAO {
    void addObject(Object object);
    Object getObject(int id);
    Object getObject(String s);
    List getAll();
}
