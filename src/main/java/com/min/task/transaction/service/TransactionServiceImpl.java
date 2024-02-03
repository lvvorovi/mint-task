package com.min.task.transaction.service;

import com.min.task.exception.TransactionIdDoesNotExistException;
import com.min.task.transaction.dto.TransactionRequest;
import com.min.task.transaction.dto.TransactionResponse;
import com.min.task.transaction.mapper.TransactionMapper;
import com.min.task.transaction.repository.TransactionRepository;
import com.min.task.transaction.util.TransactionUtil;
import com.min.task.transaction.validation.service.TransactionValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;
    private final TransactionValidationService validationService;
    private final TransactionMapper mapper;
    private final TransactionUtil util;

    @Override
    public String save(TransactionRequest transactionRequest) {
        validationService.validate(transactionRequest);
        var destinationAmount = transactionRequest.amount();
        var requestEntity = mapper.toEntity(transactionRequest);
        util.setValuesOnCreation(requestEntity, destinationAmount);
        var savedEntity = repository.save(requestEntity);
        log.info("Transaction with id:{} saved", savedEntity.getId());
        return savedEntity.getId();
    }

    @Override
    public TransactionResponse findById(String id) {
        return repository.findDtoById(id)
                .map(dto -> {
                    log.info("Transaction with id:{} found", id);
                    return dto;
                })
                .orElseThrow(() -> new TransactionIdDoesNotExistException(
                        "Transaction with id:%s does not exist"
                                .formatted(id)));
    }

    @Override
    public Page<TransactionResponse> findPageByAccountId(String accountId, Pageable pageable) {
        var transactionDtoPage = repository.findDtoListByAccountIdPaged(accountId, pageable);
        log.info("{} transactions found for accountId:{}", transactionDtoPage.getContent().size(), accountId);
        return transactionDtoPage;
    }

}
