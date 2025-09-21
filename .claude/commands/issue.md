---
name: issue
description: Create a new branch and implement tasks from a GitHub issue
argument-hint: [issue-number]
---

Implement the tasks described in GitHub issue #$1.

First, fetch and analyze the issue:
1. Run `gh issue view $1` to read the issue title, description, and comments
2. Create a descriptive branch name based on the issue title (e.g., for "Add user authentication", use `issue-$1-add-user-authentication`)
3. Create and checkout the new branch: `git checkout -b [branch-name]`

Then implement the solution:
1. Break down the issue requirements into specific implementation tasks
2. Implement each task following the codebase conventions in CLAUDE.md
3. Run `./gradlew ktlintCheck` to ensure code formatting
4. Run `./gradlew test` to ensure tests pass
5. Create a commit with a message like "Implement #$1: [brief description of what was done]"

Do NOT create a pull request unless explicitly asked.