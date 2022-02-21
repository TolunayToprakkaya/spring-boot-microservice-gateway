package com.sha.springbootmicroservicegateway.service;

import com.google.gson.JsonElement;

import java.util.List;

public interface ITransactionService {

    JsonElement saveProduct(JsonElement requestBody);

    List<JsonElement> findAllTransactionsOfUser(Long userId);

    void deleteTransaction(Long transactionId);
}
