package org.acme;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import org.acme.entity.Person;
import org.acme.entity.grpc.*;
import org.acme.entity.grpc.Empty;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        return Uni.createFrom().item(() ->
                PersonResponse.newBuilder().build()
        );
    }

    @Override
    public Uni<PersonIdResponse> createPerson(PersonRequest request) {
        return null;
    }

    @Override
    public Uni<org.acme.entity.grpc.Empty> updatePerson(UpdatePersonRequest request) {
        return null;
    }

    @Override
    public Uni<org.acme.entity.grpc.Empty> deletePerson(PersonIdRequest request) {
        return null;
    }

    @Override
    public Uni<PersonResponse> searchPerson(SearchRequest request) {
        return null;
    }

    @Override
    public Uni<CountResponse> countPersons(org.acme.entity.grpc.Empty request) {
        return Uni.createFrom().item(() ->
                CountResponse.newBuilder().build()
        );
    }
}