package bootstrap.repository.impl;

import bootstrap.entity.People;
import bootstrap.repository.PeopleRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PeopleRepositoryImpl implements PeopleRepository {

    private static Map<Long,People> map = null;

    static{
        map = new HashMap<>();
        map.put(1L,new People(1L,"老王",18));
        map.put(2L,new People(2L,"小叶",16));
    }
    @Override
    public Collection<People> findAll() {
        return map.values();
    }

    @Override
    public People findById(long id) {
        return map.get(id);
    }


    @Override
    public void saveOrUpdate(People p) {

    }

    @Override
    public void deleteById(long id) {

    }
}
