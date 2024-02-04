package com.min.task.transaction.validation.service;

import com.min.task.transaction.validation.rule.TransactionRequestPostValidationRule;
import com.min.task.transaction.validation.rule.TransactionRequestPreValidationRule;
import com.min.task.transaction.validation.rule.impl.DestinationAccountExistsTransactionPreValidationRule;
import com.min.task.transaction.validation.rule.impl.DestinationCurrencyMatchesAccountCurrencyTransactionPreValidationRule;
import com.min.task.transaction.validation.rule.impl.DestinationCurrencySupportedTransactionPreValidationRule;
import com.min.task.transaction.validation.rule.impl.PositiveBalanceSourceAccountTransactionPreValidationRule;
import com.min.task.transaction.validation.rule.impl.SourceAccountExistsTransactionPreValidationRule;
import com.min.task.transaction.validation.rule.impl.SourceAndDestinationAccountsAreDifferentTransactionPreValidationRule;
import com.min.task.transaction.validation.rule.impl.SourceCurrencyMatchesAccountCurrencyTransactionPreValidationRule;
import com.min.task.transaction.validation.rule.impl.SourceCurrencySupportedTransactionPreValidationRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.min.task.test.util.TestUtil.testTransactionRequest;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomTransactionValidationServiceTest {

    @Mock
    DestinationAccountExistsTransactionPreValidationRule destinationAccountExistsTransactionPreValidationRule;
    @Mock
    DestinationCurrencyMatchesAccountCurrencyTransactionPreValidationRule destinationCurrencyMatchesAccountCurrencyTransactionPreValidationRule;
    @Mock
    DestinationCurrencySupportedTransactionPreValidationRule destinationCurrencySupportedTransactionPreValidationRule;
    @Mock
    PositiveBalanceSourceAccountTransactionPreValidationRule positiveBalanceSourceAccountTransactionPreValidationRule;
    @Mock
    SourceAccountExistsTransactionPreValidationRule sourceAccountExistsTransactionPreValidationRule;
    @Mock
    SourceAndDestinationAccountsAreDifferentTransactionPreValidationRule sourceAndDestinationAccountsAreDifferentTransactionPreValidationRule;
    @Mock
    SourceCurrencyMatchesAccountCurrencyTransactionPreValidationRule sourceCurrencyMatchesAccountCurrencyTransactionPreValidationRule;
    @Mock
    SourceCurrencySupportedTransactionPreValidationRule sourceCurrencySupportedTransactionPreValidationRule;

    List<TransactionRequestPreValidationRule> preValidationRuleList;
    List<TransactionRequestPostValidationRule> postValidationRuleList;

    CustomTransactionValidationService victim;

    @BeforeEach
    void setUp() {
        preValidationRuleList = new ArrayList<>();
        preValidationRuleList.add(destinationAccountExistsTransactionPreValidationRule);
        preValidationRuleList.add(destinationCurrencyMatchesAccountCurrencyTransactionPreValidationRule);
        preValidationRuleList.add(destinationCurrencySupportedTransactionPreValidationRule);
        preValidationRuleList.add(sourceAccountExistsTransactionPreValidationRule);
        preValidationRuleList.add(sourceAndDestinationAccountsAreDifferentTransactionPreValidationRule);
        preValidationRuleList.add(sourceCurrencyMatchesAccountCurrencyTransactionPreValidationRule);
        preValidationRuleList.add(sourceCurrencySupportedTransactionPreValidationRule);

        postValidationRuleList = new ArrayList<>();
        postValidationRuleList.add(positiveBalanceSourceAccountTransactionPreValidationRule);

        victim = new CustomTransactionValidationService(preValidationRuleList, postValidationRuleList);
    }

    @Test
    void preValidate_whenRequest_thenDelegateToEachRule() {
        var transactionRequest = testTransactionRequest();
        preValidationRuleList.forEach(rule ->
                doNothing().when(rule).validate(transactionRequest));

        victim.preValidate(transactionRequest);

        preValidationRuleList.forEach(rule ->
                verify(rule, times(1)).validate(transactionRequest));
    }

    @Test
    void postValidate_whenRequest_thenDelegateToEachRule() {
        var transactionRequest = testTransactionRequest();
        postValidationRuleList.forEach(rule ->
                doNothing().when(rule).validate(transactionRequest, BigDecimal.TEN));

        victim.postValidated(transactionRequest, BigDecimal.TEN);

        postValidationRuleList.forEach(rule ->
                verify(rule, times(1)).validate(transactionRequest, BigDecimal.TEN));
    }
}
