import json
from typing import Union
from google.protobuf.json_format import MessageToJson, MessageToDict
import grpc
import uvicorn
from fastapi import FastAPI
from fastapi import Response
import people_service_pb2
import people_service_pb2_grpc
from config import GRPC_HOST, GRPC_PORT, RUNNING_HOST, RUNNING_PORT

app = FastAPI()


@app.get("/")
def read_root():
    return {"Hello": "World"}


@app.get("/person/{person_id}")
def read_item(person_id: str):
    return test(person_id)


def test(id):
    with grpc.insecure_channel(f"{GRPC_HOST}:{GRPC_PORT}") as channel:  # Connect to the gRPC server
        stub = people_service_pb2_grpc.PeopleServiceStub(channel)
        request = people_service_pb2.PersonIdRequest(id=id)
        response = stub.GetPerson(request)
        return MessageToDict(response)


if __name__ == "__main__":
    uvicorn.run(app, host=RUNNING_HOST, port=RUNNING_PORT)