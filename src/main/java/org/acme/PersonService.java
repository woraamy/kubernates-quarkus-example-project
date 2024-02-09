package org.acme;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import org.acme.entity.Person;
import org.acme.entity.Status;
import org.acme.entity.grpc.*;
import org.acme.entity.grpc.Empty;

import java.time.LocalDate;


@GrpcService
public class PersonService implements PeopleService {


    @Override
    public Uni<PersonList> listPersons(Empty request) {
        return Uni.createFrom().item(() ->
                PersonList.newBuilder().build()
        );
    }

    @Override
    public Uni<PersonResponse> getPerson(PersonIdRequest request) {
        Person person =  Person.findById(request.getId());
        return Uni.createFrom().item(() ->
                PersonResponse.newBuilder()
                        .setId(person.id)
                        .setAge(person.age)
                        .setBirth(person.birth.toString())
                        .setStatus(String.valueOf(person.status))
                        .build()
        );
    }

    @Override
    public Uni<PersonIdResponse> createPerson(PersonRequest request) {
        Person person = new Person();
        person.age = request.getAge();
        person.name = request.getName();
        person.birth = LocalDate.parse(request.getBirth());
        person.status = Status.valueOf(request.getStatus());
        person.persist();
        return Uni.createFrom().item(() ->
                PersonIdResponse.newBuilder().build()
        );
    }

    @Override
    public Uni<Empty> updatePerson(UpdatePersonRequest request) {
        Person person =  Person.findById(request.getId());
        person.age = request.getPerson().getAge();
        person.name = request.getPerson().getName();
        person.birth = LocalDate.parse(request.getPerson().getBirth());
        person.status = Status.valueOf(request.getPerson().getStatus());
        person.persist();
        return Uni.createFrom().item(() ->
                Empty.newBuilder().build()
        );
    }

    @Override
    public Uni<Empty> deletePerson(PersonIdRequest request) {
        Person person =  Person.findById(request.getId());
        person.delete();
        return Uni.createFrom().item(() ->
                Empty.newBuilder().build()
        );
    }

    @Override
    public Uni<PersonResponse> searchPerson(SearchRequest request) {
        return Uni.createFrom().item(() ->
                PersonResponse.newBuilder().build()
        );
    }

    @Override
    public Uni<CountResponse> countPersons(Empty request) {
        return Uni.createFrom().item(() ->
                CountResponse.newBuilder().build()
        );
    }
}