## `ast-filter`

This unit accepts an input containing a list of AST nodes, and filters AST elements based on input parameters.
It currently supports public and private classes or methods.

### Dependencies
- JDK 11+
- [Maven](https://maven.apache.org/)

### Building and running

- `mvn clean install` creates `/target/<ast-filter-version-jar-with-dependencies>.jar`
- Options:
```
  --find: method (default) OR class
  --visibility: public (default) OR private
  ```
- pipe-in input from [AST parser](https://github.com/khaes-kth/Simple-Parser)
  - `parser -s /path/to/source/dir -f csv | filter -f method -v private > output.csv`

#### See also

Documentation generation with [doctor](https://github.com/Deee92/doctor)

#### TODO

- write tests
- add support for tabular output

