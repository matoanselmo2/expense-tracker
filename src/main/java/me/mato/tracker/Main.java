package me.mato.tracker;

import me.mato.tracker.command.CommandManager;
import me.mato.tracker.command.impl.AddCommand;
import me.mato.tracker.command.impl.HelpCommand;
import me.mato.tracker.command.impl.ListCommand;
import me.mato.tracker.command.impl.RemoveCommand;
import me.mato.tracker.manager.ExpenseManager;
import me.mato.tracker.persistance.CSVDatabase;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
	private final ExpenseManager mExpenseManager = null;

    public static void main(String[] args) {
	    CSVDatabase database = new CSVDatabase("expenses.csv", "id,description,amount,date");
	    database.initializeCSV();

		ExpenseManager expenseManager = new ExpenseManager(database);
	    CommandManager commandManager = new CommandManager();

		commandManager.registerCommand(new HelpCommand(commandManager));
		commandManager.registerCommand(new ListCommand(expenseManager));
		commandManager.registerCommand(new AddCommand(expenseManager));
		commandManager.registerCommand(new RemoveCommand(expenseManager));

	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Digite um comando (ou 'exit' para sair):");

	    while (true) {
		    System.out.print("> ");
		    String line;

			try {
			    line = scanner.nextLine();
		    } catch (NoSuchElementException e) {
			    break;
		    }

			if (line == null) break;

			line = line.trim();

			if (line.isEmpty()) continue;
		    if (line.equalsIgnoreCase("exit") || line.equalsIgnoreCase("quit")) break;

			String[] parts = line.split("\\s+");
			String commandName = parts[0];
		    String[] commandArgs = new String[parts.length - 1];
		    System.arraycopy(parts, 1, commandArgs, 0, commandArgs.length);

		    commandManager.executeCommand(commandName, commandArgs);
		}
    }
}