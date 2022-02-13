package uk.tw.energy.domain;

import java.math.BigDecimal;
import java.time.Instant;

public class ElectricityReading {

    private final Instant time;
    private final BigDecimal reading; // kW

    public ElectricityReading(Instant time, BigDecimal reading) {
        this.time = time;
        this.reading = reading;
    }

    public BigDecimal getReading() {
        return reading;
    }

    public Instant getTime() {
        return time;
    }
}
