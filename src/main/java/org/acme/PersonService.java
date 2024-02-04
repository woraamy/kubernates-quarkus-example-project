package org.acme;

import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import org.acme.entity.Person;
import org.acme.entity.grpc.PeopleServiceGrpc;
import org.acme.entity.grpc.PersonList;
import org.acme.entity.grpc.PersonResponse;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@GrpcService
public class PersonService extends PeopleServiceGrpc.PeopleServiceImplBase {

    @Override
    public void listPersons(Empty request, StreamObserver<PersonList> responseObserver) {
        List<Person> persons = Person.listAll();
        List<PersonResponse> responses = persons.stream().map(p -> PersonResponse.newBuilder()
                .setId(p.id.toString())
                .setName(p.name)
                .setBirth(p.birth.format(DateTimeFormatter.ISO_DATE))
                .setStatus(p.status.toString())
                .setAge(p.age)
                .build()).collect(Collectors.toList());
        PersonList response = PersonList.newBuilder().addAllPersons(responses).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}