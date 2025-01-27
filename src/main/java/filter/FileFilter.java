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

    public void filter() throws IOException {
        for (String file : sourceFiles) {
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(new File(file)));
            } catch (FileNotFoundException e) {
                System.out.println("File: " + file + " not found: " + e.getMessage());
                continue;
            }

            String sourceLine;
            while ((sourceLine = bufferedReader.readLine()) != null) {
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
            bufferedReader.close();
        }
    }

    public ArrayList<Long> getLongArrayList() {
        return longArrayList;
    }

    public ArrayList<Double> getDoubleArrayList() {
        return doubleArrayList;
    }

    public ArrayList<String> getStringArrayList() {
        return stringArrayList;
    }
}
