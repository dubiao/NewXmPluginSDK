package com.xiaomi.smarthome.device.api.spec.definitions.data.value;

import com.xiaomi.smarthome.device.api.spec.definitions.data.DataValue;

import java.util.Objects;

public class Vuint32 extends DataValue {

    private long value;

    public Vuint32() {
        value = 0;
    }

    public Vuint32(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public boolean lessEquals(DataValue maxValue) {
        if (maxValue.getClass() != this.getClass()) {
            return false;
        }

        Vuint32 v = (Vuint32) maxValue;

        return this.value <= v.value;
    }

    @Override
    public boolean validate(DataValue minValue, DataValue maxValue) {
        if (minValue.getClass() != this.getClass() || maxValue.getClass() != this.getClass()) {
            return false;
        }

        Vuint32 min = (Vuint32) minValue;
        Vuint32 max = (Vuint32) maxValue;

        if (this.value < min.value || this.value > max.value) {
            return false;
        }

        return true;
    }
    @Override
    public boolean validate(DataValue min, DataValue max, DataValue step) {
        if (min.getClass() != this.getClass() || max.getClass() != this.getClass() || step.getClass() != this.getClass()) {
            return false;
        }

        long minValue = ((Vuint32) min).value;
        long maxValue = ((Vuint32) max).value;
        long stepValue = ((Vuint32) step).value;

        if (this.value < minValue || this.value > maxValue || stepValue <= 0) {
            return false;
        }

        return (this.value - minValue) % stepValue == 0;
    }

    @Override
    public Object getObjectValue() {
        return value;
    }

    public static Vuint32 valueOf(Object value) {
        if (value instanceof Long) {
            Long longV = (Long) value;

            if (longV < 0 || longV > 0xFFFFFFFFL) {
                return null;
            }

            return new Vuint32(longV);
        } else if (value instanceof Integer) {
            Integer intV = (Integer) value;

            if (intV < 0) {
                return null;
            }

            return new Vuint32(intV);
        }

        if (value instanceof String) {
            try {
                Long longV = Long.valueOf((String) value);
                if (longV < 0 || longV > 0xFFFFFFFFL) {
                    return null;
                }
                return new Vuint32(longV);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vuint32 v = (Vuint32) o;
        return value == v.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
