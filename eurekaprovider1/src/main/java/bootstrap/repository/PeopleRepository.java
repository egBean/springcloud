package bootstrap.repository;


import bootstrap.entity.People;

import java.util.Collection;

public interface PeopleRepository {
     Collection<People> findAll();
     People findById(long id);
     void saveOrUpdate(People p);
     void deleteById(long id);
}
