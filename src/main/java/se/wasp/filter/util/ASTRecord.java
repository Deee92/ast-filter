package se.wasp.filter.util;

public class ASTRecord {
    String elementName;
    String location;
    String parentType;
    String parentLocation;
    String value;
    String filePath;
    String extraInfo;

    public ASTRecord(String elementName, String location, String parentType,
                     String parentLocation, String value, String filePath, String extraInfo) {
        this.elementName = elementName;
        this.location = location;
        this.parentType = parentType;
        this.parentLocation = parentLocation;
        this.value = value;
        this.filePath = filePath;
        this.extraInfo = extraInfo;
    }

    public String getElementName() {
        return elementName;
    }

    public String getLocation() {
        return location;
    }

    public String getParentType() {
        return parentType;
    }

    public String getParentLocation() {
        return parentLocation;
    }

    public String getValue() {
        return value;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

}
