package uk.tw.energy.service.impl;

import org.springframework.stereotype.Service;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.exception.InvalidMeterReadingsException;
import uk.tw.energy.service.IMeterReadingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MeterReadingService
    implements IMeterReadingService {

    private final Map<String, List<ElectricityReading>> meterAssociatedReadings;

    public MeterReadingService(Map<String, List<ElectricityReading>> meterAssociatedReadings) {
        this.meterAssociatedReadings = meterAssociatedReadings;
    }

    @Override
    public Optional<List<ElectricityReading>> getReadings(String smartMeterId) {
        return Optional.ofNullable(meterAssociatedReadings.get(smartMeterId));
    }

    @Override
    public void storeReadings(String smartMeterId, List<ElectricityReading> electricityReadings)
            throws InvalidMeterReadingsException {
        validateReadings(smartMeterId, electricityReadings);

        if (!meterAssociatedReadings.containsKey(smartMeterId)) {
            meterAssociatedReadings.put(smartMeterId, new ArrayList<>());
        }
        meterAssociatedReadings.get(smartMeterId).addAll(electricityReadings);
    }

    private void validateReadings(String smartMeterId, List<ElectricityReading> electricityReadings)
            throws InvalidMeterReadingsException {
        if (smartMeterId == null || smartMeterId.isEmpty()) {
            throw new InvalidMeterReadingsException();
        }

        if (electricityReadings == null || electricityReadings.isEmpty()) {
            throw new InvalidMeterReadingsException();
        }
    }

}
