package me.mato.tracker.command.impl;

import me.mato.tracker.command.Command;
import me.mato.tracker.expense.Expense;
import me.mato.tracker.manager.ExpenseManager;

import java.time.LocalDate;

public class AddCommand extends Command {
	private final ExpenseManager mManager;

	public AddCommand(ExpenseManager manager) {
		super(new String[] { "add" }, "Adds a new expense.");
		mManager = manager;
	}

	@Override
	public void execute(String[] args) {
		if (args.length < 2) {
			System.out.println("Usage: add <amount> <description>");
			return;
		}

		try {
			double amount = Double.parseDouble(args[0]);
			String description = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));

			mManager.addExpense(new Expense(mManager.getExpenses().size() + 1, description, amount, LocalDate.now()));
			System.out.println("Expense added successfully.");
		} catch (NumberFormatException e) {
			System.out.println("Invalid amount format. Please enter a valid number.");
		}
	}
}
