package org.acme;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import org.acme.entity.Person;
import org.acme.entity.grpc.*;
import org.acme.entity.grpc.Empty;


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
                        .build()
        );
    }

    @Override
    public Uni<PersonIdResponse> createPerson(PersonRequest request) {
        return Uni.createFrom().item(() ->
                PersonIdResponse.newBuilder().build()
        );
    }

    @Override
    public Uni<Empty> updatePerson(UpdatePersonRequest request) {
        return Uni.createFrom().item(() ->
                Empty.newBuilder().build()
        );
    }

    @Override
    public Uni<Empty> deletePerson(PersonIdRequest request) {
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