package uk.tw.energy.service;

import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.exception.InvalidMeterReadingsException;

import java.util.List;
import java.util.Optional;

public interface IMeterReadingService {

    Optional<List<ElectricityReading>> getReadings(String smartMeterId);

    void storeReadings(String smartMeterId, List<ElectricityReading> electricityReadings) throws InvalidMeterReadingsException;

}
