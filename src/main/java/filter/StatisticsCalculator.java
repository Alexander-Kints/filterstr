package filter;

import java.util.OptionalDouble;
import java.util.List;

public class StatisticsCalculator {
    public static long getMinInt(List<Long> list) {
        if (list == null || list.isEmpty()) {
            return 0L;
        }
        return list.stream().mapToLong(Long::longValue).min().getAsLong();
    }

    public static long getMaxInt(List<Long> list) {
        if (list == null || list.isEmpty()) {
            return 0L;
        }
        return list.stream().mapToLong(Long::longValue).max().getAsLong();
    }

    public static double getAverageInt(List<Long> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return list.stream().mapToLong(Long::longValue).average().orElse(0);
    }

    public static long getSumInt(List<Long> list) {
        if (list == null || list.isEmpty()) {
            return 0L;
        }
        return list.stream().mapToLong(Long::longValue).sum();
    }

    public static double getMinDouble(List<Double> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return list.stream().mapToDouble(Double::doubleValue).min().getAsDouble();
    }

    public static double getMaxDouble(List<Double> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return list.stream().mapToDouble(Double::doubleValue).max().getAsDouble();
    }

    public static double getAverageDouble(List<Double> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return list.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public static double getSumDouble(List<Double> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return OptionalDouble.of(list.stream().mapToDouble(Double::doubleValue).sum()).getAsDouble();
    }

    public static int getMinStringLength(List<String> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return list.stream()
                .mapToInt(String::length)
                .min().getAsInt();
    }

    public static int getMaxStringLength(List<String> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return list.stream()
                .mapToInt(String::length)
                .max().getAsInt();
    }
}
