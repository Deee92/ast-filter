package se.wasp.filter.worker;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import se.wasp.filter.util.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import static se.wasp.filter.util.CSVHeadersEnum.*;

public class ASTFilter {
    private static final CSVFormat csvFormat = CSVFormat.Builder
            .create(CSVFormat.DEFAULT)
            .setHeader(CSVHeadersEnum.class)
            .build();

    public static void filterCLIInput(String content,
                                      ConstructEnum programConstruct,
                                      VisibilityEnum visibility) throws IOException {
        Reader in = new InputStreamReader(new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8)));
        List<ASTRecord> filteredRecords = new LinkedList<>();
        Iterable<CSVRecord> records = csvFormat.parse(in);
        for (CSVRecord record : records) {
            if (record.get(ELEMENT_TYPE).toLowerCase().contains(programConstruct.name().toLowerCase()) &
                    record.get(EXTRA_INFO).toLowerCase().contains("visibility=" + visibility.name().toLowerCase())) {
                filteredRecords.add(new ASTRecord(
                        record.get(ELEMENT_TYPE),
                        record.get(LOCATION),
                        record.get(PARENT_TYPE),
                        record.get(PARENT_LOCATION),
                        record.get(VALUE),
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
                    record.getValue(),
                    record.getFilePath(),
                    record.getExtraInfo());
        }
    }
}
