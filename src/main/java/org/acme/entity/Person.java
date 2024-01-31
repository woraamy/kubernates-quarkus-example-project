package org.acme.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

//import static io.quarkus.credentials.runtime.CredentialsProviderFinder.find;

//import static jakarta.el.FactoryFinder.find;


@MongoEntity(collection="ThePerson")
public class Person extends PanacheMongoEntity {
    public ObjectId id; // used by MongoDB for the _id field
    public String name;
    public LocalDate birth;
    public Status status;
    public int age;


    public static Person findByName(String name) {
        return find("name", name).firstResult();
    }
}
