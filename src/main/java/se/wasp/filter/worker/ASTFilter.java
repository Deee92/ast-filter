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
            if (record.get(NAME).toLowerCase().contains(programConstruct.name().toLowerCase()) &
                    record.get(VISIBILITY).toLowerCase().contains(visibility.name().toLowerCase())) {
                filteredRecords.add(new ASTRecord(
                        record.get(NAME),
                        record.get(LINE_START),
                        record.get(LINE_END),
                        record.get(COLUMN_START),
                        record.get(COLUMN_END),
                        record.get(ABS_PATH),
                        record.get(VALUE),
                        record.get(PARENT_LINE_START),
                        record.get(PARENT_LINE_END),
                        record.get(PARENT_COLUMN_START),
                        record.get(PARENT_COLUMN_END),
                        record.get(PARENT_NAME),
                        record.get(VISIBILITY)
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
                    record.getName(),
                    record.getLineStart(),
                    record.getLineEnd(),
                    record.getColumnStart(),
                    record.getColumnEnd(),
                    record.getAbsPath(),
                    record.getValue(),
                    record.getParentLineStart(),
                    record.getParentLineEnd(),
                    record.getParentColumnStart(),
                    record.getParentColumnEnd(),
                    record.getParentName(),
                    record.getVisibility());
        }
    }
}
