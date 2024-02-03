package com.min.task.account.validation;

import com.min.task.account.validation.rule.AccountUserIdValidationRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountValidationServiceImpl implements AccountValidationService {

    private final List<AccountUserIdValidationRule> accountUserIdValidationRuleList;

    @Override
    public void validate(String userId) {
        accountUserIdValidationRuleList.forEach(rule -> rule.validate(userId));
    }
}
