package me.mato.tracker.command.impl;

import me.mato.tracker.command.Command;
import me.mato.tracker.expense.Expense;
import me.mato.tracker.manager.ExpenseManager;

import java.time.LocalDate;

public class RemoveCommand extends Command {
	private final ExpenseManager mManager;

	public RemoveCommand(ExpenseManager manager) {
		super(new String[] { "remove", "del", "rem" }, "Adds a new expense.");
		mManager = manager;
	}

	@Override
	public void execute(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: remove <id>");
			return;
		}

		try {
			int id = Integer.parseInt(args[0]) + 1;

			boolean removed = mManager.removeExpense(id);
			if (removed) {
				System.out.println("Expense removed successfully.");
			} else {
				System.out.println("Expense with ID " + id + " not found.");
			}
		} catch (NumberFormatException e) {
			System.out.println("Invalid ID format. Please enter a valid number.");
		}
	}
}
