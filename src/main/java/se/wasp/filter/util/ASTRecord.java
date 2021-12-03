package se.wasp.filter.util;

public class ASTRecord {
    String name;
    String lineStart;
    String lineEnd;
    String columnStart;
    String columnEnd;
    String absPath;
    String value;
    String parentLineStart;
    String parentLineEnd;
    String parentColumnStart;
    String parentColumnEnd;
    String parentName;
    String visibility;

    public ASTRecord(String name, String lineStart, String lineEnd, String columnStart,
                     String columnEnd, String absPath, String value, String parentLineStart,
                     String parentLineEnd, String parentColumnStart, String parentColumnEnd,
                     String parentName, String visibility) {
        this.name = name;
        this.lineStart = lineStart;
        this.lineEnd = lineEnd;
        this.columnStart = columnStart;
        this.columnEnd = columnEnd;
        this.absPath = absPath;
        this.value = value;
        this.parentLineStart = parentLineStart;
        this.parentLineEnd = parentLineEnd;
        this.parentColumnStart = parentColumnStart;
        this.parentColumnEnd = parentColumnEnd;
        this.parentName = parentName;
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    public String getLineStart() {
        return lineStart;
    }

    public String getLineEnd() {
        return lineEnd;
    }

    public String getColumnStart() {
        return columnStart;
    }

    public String getColumnEnd() {
        return columnEnd;
    }

    public String getAbsPath() {
        return absPath;
    }

    public String getValue() {
        return value;
    }

    public String getParentLineStart() {
        return parentLineStart;
    }

    public String getParentLineEnd() {
        return parentLineEnd;
    }

    public String getParentColumnStart() {
        return parentColumnStart;
    }

    public String getParentColumnEnd() {
        return parentColumnEnd;
    }

    public String getParentName() {
        return parentName;
    }

    public String getVisibility() {
        return visibility;
    }
}
