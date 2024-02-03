package com.min.task.transaction.service;

import com.min.task.currency.conversion.service.ConversionService;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionValidationService validationService;
    private final TransactionMapper mapper;
    private final TransactionUtil util;
    private final ConversionService conversionService;

    public String save(TransactionRequest transactionRequest) {
        validationService.preValidate(transactionRequest);
        var sourceAmount = conversionService.convertToSourceAmount(transactionRequest);
        validationService.postValidated(transactionRequest, sourceAmount);
        var requestEntity = mapper.toEntity(transactionRequest);
        util.setValuesOnCreation(requestEntity, sourceAmount);
        var savedEntity = repository.save(requestEntity);
        log.info("Transaction with id:{} saved", savedEntity.getId());
        return savedEntity.getId();
    }

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

    public Page<TransactionResponse> findPageByAccountId(String accountId, Pageable pageable) {
        var transactionDtoPage = repository.findDtoListByAccountIdPaged(accountId, pageable);
        log.info("{} transactions found for accountId:{}", transactionDtoPage.getContent().size(), accountId);
        return transactionDtoPage;
    }

}
