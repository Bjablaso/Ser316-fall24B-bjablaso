
# Code Review Defect List

**Reviewer:** _____________________________  
**GitHub Repo:** ________________________________

---

| ID # | Class             | Problem Description                                | Problem                                                                | Line Number | Category  | Severity  |
|------|-------------------|----------------------------------------------------|------------------------------------------------------------------------|-------------|-----------|-----------|
| 1    | GuessingGame      | Field names not following naming conventions       | `Guess_count` should be `guessCount`                                   | 10          | CG        | LOW       |
| 2    | GuessingGame      | Code smell: unused variable                        | `unusedVar` is declared but never used                                 | 56          | CS        | LOW       |
| 3    | GuessingGame      | Logical error in method                            | Resetting `previousGuesses` to `null` instead of clearing set          | 41          | FD        | MJ        |
| 4    | NumberGuessGame1  | Unreachable code after gameOver flag check         | `this.gameOver = true;` is set, but condition allows further execution | 17          | CS        | MJ        |
| 5    | GuessingGame      | Incorrect range checking in `processValidGuesses`  | Checks `guessNum > 1` instead of `>= 1`                                | 37          | FD        | MJ        |
| 6    | NumberGuessGame2  | Coding guideline violation: Magic numbers          | `this.score -= 2.0;` - should use a named constant                     | 25          | CG        | LOW       |
| 7    | NumberGuessGame3  | Code smell: Multiple magic strings for Easter Eggs | Various magic strings for Easter Eggs should be constants              | 30          | CS        | MJ        |

---

### Category
- **CS** – Code Smell defect.
- **CG** – Violation of a coding guideline. Provide the guideline number.
- **FD** – Functional defect. Code will not produce the expected result.
- **MD** – Miscellaneous defect, for all other defects.

### Severity
- **BR** - Blocker, must be fixed ASAP.
- **MJ** – Major, of high importance but not a blocker.
- **LOW** – Low. 
