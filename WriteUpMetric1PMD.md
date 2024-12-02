
# **Code Review Defect List**

## **Reviewer**
_To be filled during review._

## **GitHub Repository**
_Link to the repository._

---

## **Defect Table**

| ID # | Class                     | Problem Description                                  | Problem                                                                                     | Line Number | Category  | Severity  |
|------|---------------------------|------------------------------------------------------|---------------------------------------------------------------------------------------------|-------------|-----------|-----------|
| 1    | GuessOutOfRangeException  | Unsupported class file version                      | PMD reports "Unsupported class file major version 65", possibly due to incompatible Java version | N/A         | CS        | BR        |
| 2    | GuessingGame              | Field names not following naming conventions         | `Guess_count` should be `guessCount`                                                       | 12          | CG        | LOW       |
| 3    | GuessingGame              | Logical error in game-over condition                | `this.gameOver` is set, but condition allows further execution                              | 35          | FD        | MJ        |
| 4    | Main                      | Code smell: long method                             | The `runGame()` method exceeds 30 lines, violating readability                              | 58          | CS        | MJ        |
| 5    | NumberGuessGameTest       | Missing test coverage                               | No test exists for edge case input (e.g., negative numbers)                                 | N/A         | MD        | MJ        |
| 6    | GuessingGame              | Magic number in scoring logic                       | `this.score -= 5;` - should use a named constant                                            | 47          | CG        | LOW       |
| 7    | BlackBoxTest              | Empty catch block                                   | Catch block is empty, suppressing all exceptions                                           | 21          | CS        | MJ        |
| 8    | GuessingGame              | Excessive cyclomatic complexity                     | The method `validateGuess` has too many decision points                                     | 64          | CS        | MJ        |
| 9    | GuessingGame              | Duplicate code                                      | The methods `resetGame` and `restartGame` contain duplicate logic                           | 88          | CS        | MJ        |
| 10   | Main                      | Excessive class length                              | The `Main` class exceeds 200 lines, making it harder to read and maintain                   | N/A         | CS        | MJ        |

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

1. **Unsupported Class File Version**: This indicates that the PMD tool is incompatible with the Java version used to compile the project. Ensure PMD is configured to work with the project's Java version.

2. **Field Naming Conventions**: Variables like `Guess_count` should use camelCase for better readability and adherence to Java naming conventions.

3. **Logical Error in Game-Over Condition**: The game-over logic fails to prevent subsequent code execution after setting the flag. This needs immediate attention.

4. **Long Method**: Methods exceeding 30 lines (e.g., `runGame()`) are harder to understand. Breaking them into smaller functions improves readability.

5. **Missing Test Coverage**: Test cases for boundary conditions like negative numbers or extremely high guesses should be added to ensure robustness.

6. **Magic Numbers**: Hardcoded values (e.g., `5` in scoring logic) should be replaced with named constants for better maintainability.

7. **Empty Catch Block**: Suppressing exceptions without logging or handling can lead to silent failures. These blocks should include meaningful exception handling or logging.

8. **Excessive Cyclomatic Complexity**: High complexity in methods like `validateGuess` makes them error-prone and hard to test. Refactor to simplify decision points.

9. **Duplicate Code**: Methods `resetGame` and `restartGame` share repetitive logic. Consolidating them into a single reusable method can reduce redundancy.

10. **Excessive Class Length**: Classes exceeding reasonable length (e.g., `Main` with over 200 lines) should be split into smaller, cohesive classes to improve maintainability.

