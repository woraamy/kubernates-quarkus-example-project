import json
from typing import Union
from google.protobuf.json_format import MessageToJson, MessageToDict
import grpc
import uvicorn
from fastapi import FastAPI
from fastapi import Response
import people_service_pb2
import people_service_pb2_grpc

app = FastAPI()


@app.get("/")
def read_root():
    return {"Hello": "World"}


@app.get("/items/{item_id}")
def read_item(item_id: str):
    return test(item_id)


def test(id):
    with grpc.insecure_channel("localhost:9000") as channel:  # Connect to the gRPC server
        stub = people_service_pb2_grpc.PeopleServiceStub(channel)
        request = people_service_pb2.PersonIdRequest(id=id)
        response = stub.GetPerson(request)
        # print(response)
        return MessageToDict(response)


if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=5000)