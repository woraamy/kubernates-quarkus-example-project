from concurrent import futures
import logging

import grpc
import people_service_pb2
import people_service_pb2_grpc


class PeopleService(people_service_pb2_grpc.PeopleServiceServicer):
    def listPersons(self, request, context):
        return people_service_pb2.PersonList()

    def getPerson(self, request, context):
        return people_service_pb2.PersonResponse()

    def createPerson(self, request, context):
        return people_service_pb2.PersonList()

#     def updatePerson(self, request, context):
#         return people_service_pb2.PersonResponse()

#     def deletePerson(self, request, context):
#         return people_service_pb2.PersonResponse()

    def searchPerson(self, request, context):
        return people_service_pb2.PersonResponse()

    def countPersons(self, request, context):
        return people_service_pb2.CountResponse()




def serve():
    port = "50051"
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    people_service_pb2_grpc.add_PeopleServiceServicer_to_server(PeopleService(), server)
    server.add_insecure_port("[::]:" + port)
    server.start()
    print("Server started, listening on " + port)
    server.wait_for_termination()


if __name__ == "__main__":
    logging.basicConfig()
    serve()