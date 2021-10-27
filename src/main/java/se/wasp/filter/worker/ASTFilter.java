package se.wasp.filter.worker;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import se.wasp.filter.util.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import static se.wasp.filter.util.CSVHeadersEnum.*;

public class ASTFilter {
    private static final CSVFormat csvFormat = CSVFormat.Builder
            .create(CSVFormat.DEFAULT)
            .setHeader(CSVHeadersEnum.class)
            .build();

    public static void filterInputCSV(String filePath,
                                      ConstructEnum programConstruct,
                                      VisibilityEnum visibility) throws IOException {
        Reader in = new FileReader(filePath);
        List<ASTRecord> filteredRecords = new LinkedList<>();
        Iterable<CSVRecord> records = csvFormat.parse(in);
        for (CSVRecord record : records) {
            if (record.get(ELEMENT_TYPE).toLowerCase().contains(programConstruct.name().toLowerCase()) &
                    record.get(EXTRA_INFO).toLowerCase().contains(visibility.name().toLowerCase())) {
                filteredRecords.add(new ASTRecord(
                        record.get(ELEMENT_TYPE),
                        record.get(LOCATION),
                        record.get(PARENT_TYPE),
                        record.get(PARENT_LOCATION),
                        record.get(FILEPATH),
                        record.get(EXTRA_INFO)
                ));
            }
        }
        prepareOutput(filteredRecords);
    }

    private static void prepareOutput(List<ASTRecord> records) throws IOException {
        PrintStream printStream = System.out;
        CSVPrinter csvPrinter = new CSVPrinter(printStream, csvFormat);
        for (ASTRecord record : records) {
            csvPrinter.printRecord(
                    record.getElementName(),
                    record.getLocation(),
                    record.getParentType(),
                    record.getParentLocation(),
                    record.getFilePath(),
                    record.getExtraInfo());
        }
    }
}