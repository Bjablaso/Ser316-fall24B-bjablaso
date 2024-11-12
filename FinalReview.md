# Code Review Defect List

**Reviewer:** _____________________________  
**GitHub Repo:** ________________________________

This project uses the \`GuessingGame\` class to construct a basic number guessing game. 
The game gives the user feedback on whether their predictions are accurate or too high or low. 
The amount of guesses made and the player's score are also tracked by the game.

### Checkstyle and SpotBugs Results

| Branch       | Checkstyle (main) | Checkstyle (test) | SpotBugs (main) | SpotBugs (test) |
|--------------|-------------------|-------------------|------------------|------------------|
| Dev-Branch   | 0 (After fix)    | 0 (After fix)    | 0 (After fix)   | 0 (After fix)   |

### Code Review Defect List

| ID # | Class        | Problem Description                            | Problem                                    | Line Number | Category | Severity |
|------|--------------|------------------------------------------------|--------------------------------------------|-------------|----------|----------|
| 1    | GuessingGame | Field name not following naming conventions    | \`Guess_count\` should be \`guessCount\`   | 10          | CG       | LOW      |
| 2    | GuessingGame | Unused variable detected                       | \`unusedVar\` is declared but never used   | 56          | CS       | LOW      |
| 3    | GuessingGame | Logical error when resetting game state        | Resetting \`previousGuesses\` to \`null\`  | 41          | FD       | MJ       |
| 4    | GuessingGame | Incorrect range check in \`processValidGuesses\` | \`guessNum > 1\` instead of \`>= 1\`       | 37          | FD       | MJ       |
| 5    | GuessingGame | Magic number used in score deduction           | \`score -= 2.0;\` should use a constant    | 25          | CG       | LOW      |


## Conclusion

All identified defects have been successfully resolved, except for the class name, 
which was deemed arbitrary and does not impact the functionality or violate critical naming guidelines. 
The code now adheres to coding standards, as verified by comprehensive analysis using Checkstyle and SpotBugs, 
resulting in zero reported issues.  This improvement ensures that the code is cleaner, more maintainable, 
and aligned with best practices,  enhancing both code quality and developer productivity.