package filter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import java.nio.file.Paths;

public class Main {
    private static final String FILEPATH_COMMAND_NAME = "o";
    private static final String PREFIX_COMMAND_NAME = "p";
    private static final String APPEND_COMMAND_NAME = "a";
    private static final String SHORT_STAT_COMMAND_NAME = "s";
    private static final String FULL_STAT_COMMAND_NAME = "f";

    private static final String DEFAULT_INTEGERS_FILENAME = "integers.txt";
    private static final String DEFAULT_FLOATS_FILENAME = "floats.txt";
    private static final String DEFAULT_STRINGS_FILENAME = "strings.txt";

    public static void main(String[] args) {
        CommandLine commandLine = getCommandLine(args);
        FileFilter fileFilter = new FileFilter(commandLine.getArgs());
        fileFilter.filter();

        String prefixOptionValue = commandLine.getOptionValue(PREFIX_COMMAND_NAME);
        String pathOptionValue = commandLine.getOptionValue(FILEPATH_COMMAND_NAME);
        boolean aMode = commandLine.hasOption(APPEND_COMMAND_NAME);

        String integersFilePath = formatFilePath(DEFAULT_INTEGERS_FILENAME,
                prefixOptionValue, pathOptionValue);
        String floatsFilePath = formatFilePath(DEFAULT_FLOATS_FILENAME,
                prefixOptionValue, pathOptionValue);
        String stringsFilePath = formatFilePath(DEFAULT_STRINGS_FILENAME,
                prefixOptionValue, pathOptionValue);

        fileFilter.outputLongArrayList(integersFilePath, aMode);
        fileFilter.outputDoubleArrayList(floatsFilePath, aMode);
        fileFilter.outputStringArrayList(stringsFilePath, aMode);

        if (commandLine.hasOption(SHORT_STAT_COMMAND_NAME) && !commandLine.hasOption(FULL_STAT_COMMAND_NAME)) {
            fileFilter.printShortStatistics();
        }

        if (commandLine.hasOption(FULL_STAT_COMMAND_NAME)) {
            fileFilter.printFullStatistics();
        }
    }

    private static CommandLine getCommandLine(String[] args) {
        Options options = new Options();
        options.addOption(FILEPATH_COMMAND_NAME, true, "result filepath");
        options.addOption(PREFIX_COMMAND_NAME, true, "result files prefix");
        options.addOption(APPEND_COMMAND_NAME, false, "append mode");
        options.addOption(SHORT_STAT_COMMAND_NAME, false, "short statistics");
        options.addOption(FULL_STAT_COMMAND_NAME, false, "full statistics");

        CommandLine commandLine = null;
        try {
            commandLine = new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            System.out.println("Fatal error! Invalid arguments: " + e.getMessage());
            System.exit(1);
        }

        if (commandLine.getArgs().length == 0) {
            System.out.println("Fatal error! file paths are not set");
            System.exit(2);
        }

        return commandLine;
    }

    private static String formatFilePath(String filename, String prefix, String path) {
        if (prefix != null) filename = prefix + filename;
        if (path != null) filename = Paths.get(path, filename).toString();
        return filename;
    }
}
