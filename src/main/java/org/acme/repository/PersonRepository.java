package org.acme.repository;

import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.mongodb.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Person;
import org.acme.entity.Status;

import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheMongoRepository<Person> {

    // put your custom logic here as instance methods

    public Person findByName(String name){
        return find("name", name).firstResult();
    }

    public List<Person> findAlive(){
        return list("status", Status.Alive);
    }

    public void deletePerson(String name){
        delete("name", name);
    }
}