package com.sha.springbootmicroservicegateway.controller;

import com.google.gson.JsonElement;
import com.sha.springbootmicroservicegateway.security.UserPrincipal;
import com.sha.springbootmicroservicegateway.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway/transaction")
public class ITransactionController {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody JsonElement requestBody) {
        return ResponseEntity.ok(transactionService.saveProduct(requestBody));
    }

    @GetMapping
    public ResponseEntity<?> findAllTransactionsOfUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok(transactionService.findAllTransactionsOfUser(userPrincipal.getId()));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
