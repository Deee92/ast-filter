package se.wasp.filter.runner;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import se.wasp.filter.util.ConstructEnum;
import se.wasp.filter.util.VisibilityEnum;
import se.wasp.filter.worker.ASTFilter;

import java.util.concurrent.Callable;
import java.util.logging.Logger;

@Command(name = "ast-filter", mixinStandardHelpOptions = true, version = "1.0",
        description = "Filters an AST to find instances of a program construct matching input visibility")
public class Main implements Callable<Integer> {
    @Parameters(index = "0", description = "The path to a CSV file with the parsed AST")
    private String parsedAstFilePath;

    @Option(names = {"-f", "--find"}, defaultValue = "method", description = "Program construct to find: method (default), class")
    private String inputProgramConstruct;
    private ConstructEnum programConstruct;

    @Option(names = {"-v", "--visibility"}, defaultValue = "public", description = "Construct visibility: public (default), private")
    private String inputVisibility;
    private VisibilityEnum visibility;

    private void setProgramConstruct() {
        programConstruct = inputProgramConstruct.equalsIgnoreCase("class") ?
                ConstructEnum.CLASS : ConstructEnum.METHOD;
    }

    private void setVisibility() {
        visibility = inputVisibility.equalsIgnoreCase("private") ?
                VisibilityEnum.PRIVATE : VisibilityEnum.PUBLIC;
    }

    private void setup() {
        setProgramConstruct();
        setVisibility();
    }

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    @Override
    public Integer call() throws Exception {
        if (!parsedAstFilePath.endsWith(".csv")) {
            LOGGER.info("Please provide a CSV file (.csv)\nTry --help for help");
            return 1;
        }
        LOGGER.info(String.format("Working with the parsed AST in %s", parsedAstFilePath));
        setup();
        LOGGER.info(String.format("Finding all %s constructs that are %s",
                programConstruct.toString().toLowerCase(), visibility.toString().toLowerCase()));
        ASTFilter.filterInputCSV(parsedAstFilePath, programConstruct, visibility);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}
