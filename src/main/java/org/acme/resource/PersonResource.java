package org.acme.resource;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Person;
import org.bson.types.ObjectId;

import java.net.URI;
import java.util.List;

@Path("/persons")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    public List<Person> list() {
        return Person.listAll();
    }

    @GET
    @Path("/{id}")
    public Person get(@PathParam("id") String id) {
        return Person.findById(id);
    }

    @POST
    public Response create(Person person) {
        person.persist();
        return Response.created(URI.create("/persons/" + person.id)).build();
    }

    @PUT
    @Path("/{id}")
    public void update(String id, Person person) {
        Person old = Person.findById(id);
        old.birth = person.birth;
        old.name = person.name;
        old.age = person.age;
        old.update();
    }

    @DELETE
    @Path("/{id}")
    public void delete(String id) {
        Person person = Person.findById(id);
        if(person == null) {
            throw new NotFoundException();
        }
        person.delete();
    }

    @GET
    @Path("/search/{name}")
    public Person search(String name) {
        return Person.findByName(name);
    }

    @GET
    @Path("/count")
    public Long count() {
        return Person.count();
    }
}