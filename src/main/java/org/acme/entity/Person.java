package org.acme.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.time.LocalDate;



@MongoEntity(collection="ThePerson")
public class Person extends PanacheMongoEntityBase {

    @BsonId
    public String id; // used by MongoDB for the _id field
    public String name;
    public LocalDate birth;
    public Status status;
    public int age;


    public static Person findByName(String name) {
        return find("name", name).firstResult();
    }
}
