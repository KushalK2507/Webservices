package com.kushal.restwsasync.client;

import com.kushal.restwsasync.model.ChecksList;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class RestAsyncClient {

  private static final String CHECK_SERVICE_URL =
      "http://localhost:8080/checkrestwsasync/services/checkProcessorService";

  public static void main(String[] args) {
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target(CHECK_SERVICE_URL).path("/checks");

    // invoker in this we use async for Async Application
    AsyncInvoker invoker = target.request().async();

    // The below return type is future which is from Concurrent package API and represents that
    // response will
    // come in future and this will poll server to response to come.
    Future<Boolean> response =
        invoker.post(Entity.entity(new ChecksList(), MediaType.APPLICATION_XML), Boolean.class);

    try {
      System.out.println(response.get());
    }
    // Interrupt Exception is handling the exception when there is any interruption in this request
    // Execution Exception will handle all the server side Exception
    catch (InterruptedException | ExecutionException e) {
      if (e.getCause() instanceof BadRequestException) {
        BadRequestException bre = (BadRequestException) e.getCause();
        System.out.println("PLease send the valid list");
      }
    }
  }
}
