package uk.tw.energy.service;

import com.sun.tools.javac.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.exception.InvalidMeterReadingsException;
import uk.tw.energy.service.impl.MeterReadingService;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MeterReadingServiceTest {

    private MeterReadingService meterReadingService;

    @BeforeEach
    public void setUp() {
        meterReadingService = new MeterReadingService(new HashMap<>());
    }

    @Test
    public void givenMeterIdThatDoesNotExistShouldReturnNull() {
        assertThat(meterReadingService.getReadings("unknown-id")).isEqualTo(Optional.empty());
    }

    @Test
    public void givenMeterReadingThatExistsShouldReturnMeterReadings() {
        List<ElectricityReading> electricityReadings = List.of(new ElectricityReading(Instant.now(), BigDecimal.ONE));
        meterReadingService.storeReadings("random-id", electricityReadings);
        assertThat(meterReadingService.getReadings("random-id")).isEqualTo(Optional.of(electricityReadings));
    }

    @Test
    public void givenInvalidMeterReadingsWhenStoringShouldThrowException() {
        assertThatThrownBy(() -> meterReadingService.storeReadings("random-id",
                                                                   new ArrayList<>()))
                .isInstanceOf(InvalidMeterReadingsException.class);
    }
}
