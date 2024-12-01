
# **Metrics 2 Code Review Defect List**

## **Reviewer**  
_To be filled during review._

## **GitHub Repository**  
_Link to the repository._

---

## **Defect Table**

| ID # | Class                     | Problem Description                                  | Problem                                                                                     | Line Number | Category  | Severity  |
|------|---------------------------|------------------------------------------------------|---------------------------------------------------------------------------------------------|-------------|-----------|-----------|
| 1    | GuessOutOfRangeException  | Missing serialVersionUID                             | Serializable class is missing a `serialVersionUID`, which may lead to deserialization issues | 4-8         | CS        | MJ        |
| 2    | GuessingGame              | Avoid literals in conditional statements             | Literal values are used in `if` condition, reducing maintainability                          | 40          | CG        | LOW       |
| 3    | GuessingGame              | Dataflow anomaly: 'DU' anomaly for `guessNum`        | The variable `guessNum` is defined but not used correctly, leading to possible misuse       | 47-84       | FD        | MJ        |
| 4    | GuessingGame              | Dataflow anomaly: 'DD' anomaly for `validGuessCount` | The variable `validGuessCount` is defined multiple times without being used or reset        | 94-100      | FD        | MJ        |
| 5    | GuessingGame              | Duplicate 'DD' anomaly for `validGuessCount`         | The same variable (`validGuessCount`) has multiple duplicate definitions                    | 100         | FD        | MJ        |

---

## **Category Definitions**

- **CS** – Code Smell defect: Areas of the code that reduce readability or maintainability, even if functional correctness is not immediately impacted.
- **CG** – Coding Guideline violation: The code does not follow the agreed-upon style or conventions.
- **FD** – Functional Defect: A defect that directly affects the correctness of the program’s functionality.
- **MD** – Miscellaneous Defect: Issues that do not fit into other categories but still need addressing.

---

## **Severity Levels**

- **BR** – Blocker: Must be fixed immediately as it prevents the program from running.
- **MJ** – Major: High-priority issues but do not block the program from functioning.
- **LOW** – Minor issues that do not significantly affect functionality but should be addressed.

---

## **Brief Reviews of Errors**

### **1. Missing SerialVersionUID**
- **Class**: GuessOutOfRangeException  
- **Description**: The `GuessOutOfRangeException` class implements `Serializable` but is missing a `serialVersionUID`.  
- **Impact**: This can cause issues during deserialization if the class structure changes between versions.  
- **Recommendation**: Add a `private static final long serialVersionUID` to the class with a generated or manually assigned value.

### **2. Avoid Literals in Conditional Statements**
- **Class**: GuessingGame  
- **Description**: Literal values are used in an `if` condition within the `makeGuess` method.  
- **Impact**: Reduces maintainability and readability, as modifying conditions later requires changing hardcoded values.  
- **Recommendation**: Replace the literal with a named constant, e.g., `MAX_GUESS_LIMIT`.

### **3. Dataflow Anomaly: 'DU' Anomaly for `guessNum`**
- **Class**: GuessingGame  
- **Description**: The variable `guessNum` is defined but not used correctly in the `makeGuess` method, which can lead to misuse or bugs.  
- **Impact**: This anomaly suggests potential logic issues and makes the code less predictable.  
- **Recommendation**: Review and refactor the method to ensure proper usage of `guessNum`.

### **4. Dataflow Anomaly: 'DD' Anomaly for `validGuessCount`**
- **Class**: GuessingGame  
- **Description**: The variable `validGuessCount` is defined multiple times without being used or reset appropriately in the `processValidGuesses` method.  
- **Impact**: Repeated definitions clutter the code and may result in unpredictable behavior.  
- **Recommendation**: Consolidate the variable's usage and reset it before redefining.

### **5. Duplicate 'DD' Anomaly for `validGuessCount`**
- **Class**: GuessingGame  
- **Description**: Duplicate definitions for the `validGuessCount` variable are present on the same line.  
- **Impact**: Creates redundancy and confusion, which can hinder code maintainability.  
- **Recommendation**: Eliminate duplicate declarations and streamline variable usage.

---

## **Summary and Recommendations**

1. **Improve Serialization**:
   - Add `serialVersionUID` to all Serializable classes to avoid deserialization issues.

2. **Refactor Conditionals**:
   - Replace hardcoded literals in conditions with named constants for better maintainability.

3. **Fix Data Flow Anomalies**:
   - Refactor variable definitions and usages (e.g., `guessNum`, `validGuessCount`) to simplify and avoid redundancy.

4. **Enhance Maintainability**:
   - Address all code smells and ensure that variables and methods are concise and well-documented.

5. **Test Thoroughly**:
   - Ensure all fixes are covered with appropriate unit tests to prevent regressions.

---
