package bootstrap.Controller;

import bootstrap.entity.People;
import bootstrap.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class Provider1Controller {

    @Value("${server.port}")
    private String port;

    @Autowired
    private PeopleRepository peopleRepositoryImpl;

    @GetMapping("/findAll")
    public Collection<People> findAll(){
        return peopleRepositoryImpl.findAll();
    }

    @GetMapping("/findById/{id}")
    public People findById(@PathVariable("id") long id){
        return peopleRepositoryImpl.findById(id);
    }

    @PostMapping("save")
    public void saveOrUpdate(@RequestBody People p){
        peopleRepositoryImpl.saveOrUpdate(p);
    }

    @PutMapping("update")
    public void update(@RequestBody People p){
        peopleRepositoryImpl.saveOrUpdate(p);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") long id){
        peopleRepositoryImpl.deleteById(id);
    }

    @GetMapping("/port")
    public String getPort(){
        return this.port;
    }
}
