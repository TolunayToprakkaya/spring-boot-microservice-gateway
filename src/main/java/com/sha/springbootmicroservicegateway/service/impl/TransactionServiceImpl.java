package com.sha.springbootmicroservicegateway.service.impl;

import com.google.gson.JsonElement;
import com.sha.springbootmicroservicegateway.request.ITransactionServiceRequest;
import com.sha.springbootmicroservicegateway.service.ITransactionService;
import com.sha.springbootmicroservicegateway.util.RetrofitUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private ITransactionServiceRequest transactionServiceRequest;

    @Override
    public JsonElement saveProduct(JsonElement requestBody) {
        return RetrofitUtils.executeInBlock(transactionServiceRequest.saveTransaction(requestBody));
    }

    @Override
    public List<JsonElement> findAllTransactionsOfUser(Long userId) {
        return RetrofitUtils.executeInBlock(transactionServiceRequest.findAllTransactionsOfUser(userId));
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        RetrofitUtils.executeInBlock(transactionServiceRequest.deleteTransaction(transactionId));
    }
}
