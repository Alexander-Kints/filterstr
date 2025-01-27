package filter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.*;

// -o путь для результатов
// -p префикс для имен файлов с результатами
// -a режим добавления (по умолчания результат перезаписывается)
// -s краткая статистика (кол-во элеметов в каждом рез. файле)
// -f полная статистика (дополнительно к краткой)
// числа: min, max, sum, avg
// строки: длины самой короткой и самой длинной

public class Main {
    private static final String FILEPATH_COMMAND_NAME = "o";
    private static final String PREFIX_COMMAND_NAME = "p";
    private static final String ADD_COMMAND_NAME = "a";
    private static final String SHORT_STAT_COMMAND_NAME = "s";
    private static final String FULL_STAT_COMMAND_NAME = "f";

    public static void main(String[] args) {
        CommandLine commandLine = getCommandLine(args);
        FileFilter fileFilter = new FileFilter(commandLine.getArgs());
        try {
            fileFilter.filter();
        } catch (IOException e) {
            System.out.println("Fatal error: " + e.getMessage());
            System.exit(2);
        }

        System.out.println("Int");
        for (Long el : fileFilter.getLongArrayList()) {
            System.out.println(el);
        }

        System.out.println("Double");
        for (Double el : fileFilter.getDoubleArrayList()) {
            System.out.println(el);
        }

        System.out.println("Str");
        for (String el : fileFilter.getStringArrayList()) {
            System.out.println(el);
        }

    }

    private static CommandLine getCommandLine(String[] args) {
        Options options = new Options();
        options.addOption(FILEPATH_COMMAND_NAME, true, "result filepath");
        options.addOption(PREFIX_COMMAND_NAME, true, "result files prefix");
        options.addOption(ADD_COMMAND_NAME, false, "adding mode");
        options.addOption(SHORT_STAT_COMMAND_NAME, false, "short statistics");
        options.addOption(FULL_STAT_COMMAND_NAME, false, "full statistics");

        CommandLine commandLine = null;
        try {
            commandLine = new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            System.out.println("Fatal error! Invalid arguments: " + e.getMessage());
            System.exit(1);
        }

        //TODO непустой список файлов обработать

        return commandLine;
    }
}
