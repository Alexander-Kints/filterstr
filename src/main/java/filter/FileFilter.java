package filter;

import java.io.*;
import java.util.ArrayList;

public class FileFilter {
    private final String[] sourceFiles;
    private final ArrayList<Long> longArrayList;
    private final ArrayList<Double> doubleArrayList;
    private final ArrayList<String> stringArrayList;

    public FileFilter(String[] sourceFiles) {
        this.sourceFiles = sourceFiles;
        longArrayList = new ArrayList<>();
        doubleArrayList = new ArrayList<>();
        stringArrayList = new ArrayList<>();
    }

    public void filter() {
        for (String file : sourceFiles) {
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(new File(file)));
            } catch (FileNotFoundException e) {
                System.out.println("File " + file + " not found: " + e.getMessage());
                continue;
            }

            String sourceLine;
            while (true) {
                try {
                    sourceLine = bufferedReader.readLine();
                } catch (IOException e) {
                    System.out.println("Warn: " + e.getMessage());
                    break;
                }

                if (sourceLine == null) break;

                try {
                    Long intNum = Long.parseLong(sourceLine);
                    longArrayList.add(intNum);
                    continue;
                } catch (NumberFormatException ignored) {}

                try {
                    Double doubleNum = Double.parseDouble(sourceLine);
                    doubleArrayList.add(doubleNum);
                    continue;
                } catch (NumberFormatException ignored) {}

                stringArrayList.add(sourceLine);
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                System.out.println("Warn: " + e.getMessage());
            }
        }
    }

    public void outputLongArrayList(String filepath, boolean aMode) {
        outputToFile(filepath, longArrayList, aMode);
    }

    public void outputDoubleArrayList(String filepath, boolean aMode) {
        outputToFile(filepath, doubleArrayList, aMode);
    }

    public void outputStringArrayList(String filepath, boolean aMode) {
        outputToFile(filepath, stringArrayList, aMode);
    }

    public void printShortStatistics() {
        System.out.println("Integers: " + longArrayList.size());
        System.out.println("Floats: " + doubleArrayList.size());
        System.out.println("Strings: " + stringArrayList.size());
    }

    public void printFullStatistics() {
        printShortStatistics();

        System.out.println("-Integers-");
        System.out.println("Max: " + StatisticsCalculator.getMaxInt(longArrayList));
        System.out.println("Min: " + StatisticsCalculator.getMinInt(longArrayList));
        System.out.println("Sum: " + StatisticsCalculator.getSumInt(longArrayList));
        System.out.println("Avg: " + StatisticsCalculator.getAverageInt(longArrayList));

        System.out.println("-Floats-");
        System.out.println("Max: " + StatisticsCalculator.getMaxDouble(doubleArrayList));
        System.out.println("Min: " + StatisticsCalculator.getMinDouble(doubleArrayList));
        System.out.println("Sum: " + StatisticsCalculator.getSumDouble(doubleArrayList));
        System.out.println("Avg: " + StatisticsCalculator.getAverageDouble(doubleArrayList));

        System.out.println("-Strings-");
        System.out.println("Max: " + StatisticsCalculator.getMaxStringLength(stringArrayList));
        System.out.println("Min: " + StatisticsCalculator.getMinStringLength(stringArrayList));
    }

    private void outputToFile(String filepath, ArrayList<?> list, boolean aMode) {
        if (list.isEmpty()) return;

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(new File(filepath), aMode);
        } catch (IOException e) {
            System.out.println("Warn: " + e.getMessage());
            return;
        }

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (Object obj : list) {
            try{
                bufferedWriter.write(String.valueOf(obj));
                bufferedWriter.newLine();
            } catch (Exception e) {
                System.out.println("Warn: " + e.getMessage());
            }
        }

        try {
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Warn: " + e.getMessage());
        }
    }
}
