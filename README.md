## `ast-filter`

This unit takes in a file with a list of AST nodes, and filters AST elements based on input parameters.
It currently supports public and private classes or methods.

### Dependencies
- JDK 11+
- [Maven](https://maven.apache.org/)

### Building and running

- `mvn clean install`
- ```
  java -jar target/<ast-filter-version-jar-with-dependencies>.jar /path/to/a/file.csv
  --find <method (default) OR class>
  --visibility <public (default) OR private>
  ```

#### TODO

- add support for tabular output
- write tests
- pipe-in input from [AST parser](https://github.com/khaes-kth/Simple-Parser)
  - `parser -s /path/to/source/dir -f csv | filter -f method -v private > output.csv`
