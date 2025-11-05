# Expense Tracker

A simple command-line expense tracking application built with Java that allows you to manage your personal expenses efficiently.

## Features

- Add expenses with description and amount
- List all tracked expenses
- Remove expenses by ID
- Persistent storage using CSV files
- Interactive command-line interface

## Prerequisites

- Java 17 or higher
- Gradle 8.x (wrapper included)

## Installation

1. Clone the repository:
```bash
git clone https://github.com/matoanselmo2/expense-tracker.git
cd expense-tracker
```

2. Build the project:
```bash
./gradlew build
```

## Usage

1. First, build the project:
```bash
./gradlew build
```

2. Run the application:
```bash
java -cp build/classes/java/main me.mato.tracker.Main
```

### Available Commands

Once the application is running, you can use the following commands:

- **add `<amount>` `<description>`** - Add a new expense
  ```
  > add 25.50 Grocery shopping
  Expense added successfully.
  ```

- **list** (aliases: `ls`, `l`) - Display all expenses
  ```
  > list
  Recorded Expenses:
   - ID: 1 | Amount: 25.50 | Date: 2025-11-05 | Description: Grocery shopping
  ```

- **remove `<id>`** (aliases: `del`, `rem`) - Remove an expense by its ID
  ```
  > remove 1
  Expense removed successfully.
  ```

- **help** (aliases: `h`, `?`) - Display help information for available commands
  ```
  > help
  Available commands:
   - list, ls, l: Lists all recorded expenses.
   - remove, del, rem: Removes an expense.
   - help, h, ?: Displays help information for available commands.
   - add: Adds a new expense.
  ```

- **exit** (or `quit`) - Exit the application

## Data Storage

Expenses are stored in a CSV file (`expenses.csv`) in the project directory. The file is created automatically when you add your first expense and persists between sessions.

## Project Structure

```
expense-tracker/
├── src/
│   └── main/
│       └── java/
│           └── me/mato/tracker/
│               ├── Main.java                    # Application entry point
│               ├── command/                      # Command pattern implementation
│               │   ├── Command.java
│               │   ├── CommandManager.java
│               │   └── impl/                     # Command implementations
│               │       ├── AddCommand.java
│               │       ├── ListCommand.java
│               │       ├── RemoveCommand.java
│               │       └── HelpCommand.java
│               ├── expense/
│               │   └── Expense.java              # Expense data model
│               ├── manager/
│               │   └── ExpenseManager.java       # Expense management logic
│               └── persistance/
│                   └── CSVDatabase.java          # CSV file handling
├── build.gradle.kts                              # Gradle build configuration
└── README.md                                     # This file
```

## Building and Testing

- **Build the project:**
  ```bash
  ./gradlew build
  ```

- **Run tests:**
  ```bash
  ./gradlew test
  ```

- **Clean build artifacts:**
  ```bash
  ./gradlew clean
  ```

## License

This project is open source and available for personal and educational use.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.
