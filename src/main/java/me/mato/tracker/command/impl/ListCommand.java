package me.mato.tracker.command.impl;

import me.mato.tracker.command.Command;
import me.mato.tracker.manager.ExpenseManager;

public class ListCommand extends Command {
	private final ExpenseManager mManager;

	public ListCommand(ExpenseManager manager) {
		super(new String[] { "list", "ls", "l" }, "Lists all recorded expenses.");
		mManager = manager;
	}

	@Override
	public void execute(String[] args) {
		if (mManager.getExpenses().isEmpty()) {
			System.out.println("No expenses recorded.");
			return;
		}

		System.out.println("Recorded Expenses:");
		for (var expense : mManager.getExpenses().values()) {
			System.out.printf(" - ID: %d | Amount: %.2f | Date: %s | Description: %s%n",
					expense.id(),
					expense.amount(),
					expense.date(),
					expense.description());
		}
	}
}
