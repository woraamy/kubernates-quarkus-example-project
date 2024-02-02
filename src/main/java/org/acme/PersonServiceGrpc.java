//package org.acme;
//
//import io.grpc.stub.StreamObserver;
//import io.quarkus.example.GreeterGrpc;
//import io.quarkus.example.HelloReply;
//import io.quarkus.example.HelloRequest;
//import io.quarkus.grpc.GrpcService;
//
//@GrpcService
//public class PersonService extends PersonService.PersonServiceImplBase {
//
//    @Override
//    public void listPersons(Empty request, StreamObserver<PersonList> responseObserver) {
//        List<Person> persons = Person.listAll();
//        List<PersonResponse> responses = persons.stream().map(p -> PersonResponse.newBuilder()
//                .setId(p.id.toString())
//                .setName(p.name)
//                .setBirth(p.birth.format(DateTimeFormatter.ISO_DATE))
//                .setStatus(p.status.toString())
//                .setAge(p.age)
//                .build()).collect(Collectors.toList());
//        PersonList response = PersonList.newBuilder().addAllPersons(responses).build();
//        responseObserver.onNext(response);
//        responseObserver.onCompleted();
//    }
//}