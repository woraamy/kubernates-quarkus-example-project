FROM python:3.9

WORKDIR /app

COPY . /app

RUN pip install fastapi uvicorn grpcio protobuf

EXPOSE 5000

ENV MODULE_NAME=main

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "5000"]
