package com.min.task.transaction.controller;

import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.dto.TransactionResponse;
import com.min.task.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/transactions")
public class TransactionController {

    private final TransactionService service;

    @PostMapping
    public ResponseEntity<TransactionResponse> save(@RequestBody @Valid TransactionRequest transactionRequest,
                                                    @PathVariable String userId,
                                                    UriComponentsBuilder builder) {
        var savedTransactionId = service.save(transactionRequest);

        return ResponseEntity
                .created(builder
                        .path("/api/v1/users/{userId}/transactions/{id}")
                        .buildAndExpand(userId, savedTransactionId)
                        .toUri())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable String id) {
        var transactionResponse = service.findById(id);

        return ResponseEntity
                .ok(transactionResponse);
    }


    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<Page<TransactionResponse>> findPageByAccountId(@PathVariable String accountId,
                                                                         @SortDefault(sort = "created", direction = Sort.Direction.DESC)
                                                                         @PageableDefault(size = 5)
                                                                         Pageable pageable,
                                                                         @RequestParam(required = false) Integer limit,
                                                                         @RequestParam(required = false) Integer offset) {
        // --This is a quick fix to support limit and offset. requires proper refactoring.
        // missing logic to handle requests when (offset % limit) != 0
        if (Objects.nonNull(limit) && Objects.nonNull(offset)) {
            int page = offset / limit;
            pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "created"));
        }
        // --

        var transactionDtoPage = service
                .findPageByAccountId(accountId, pageable);

        if (transactionDtoPage.getContent().isEmpty()) {
            return ResponseEntity
                    .noContent()
                    .build();
        } else {
            return ResponseEntity
                    .ok(transactionDtoPage);
        }
    }
}
