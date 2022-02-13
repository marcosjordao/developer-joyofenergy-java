package uk.tw.energy.service.impl;

import org.springframework.stereotype.Service;
import uk.tw.energy.service.IAccountService;

import java.util.Map;

@Service
public class AccountService
        implements IAccountService {

    private final Map<String, String> smartMeterToPricePlanAccounts;

    public AccountService(Map<String, String> smartMeterToPricePlanAccounts) {
        this.smartMeterToPricePlanAccounts = smartMeterToPricePlanAccounts;
    }

    @Override
    public String getPricePlanIdForSmartMeterId(String smartMeterId) {
        return smartMeterToPricePlanAccounts.get(smartMeterId);
    }
}
