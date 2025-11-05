package me.mato.tracker.manager;

import me.mato.tracker.expense.Expense;
import me.mato.tracker.persistance.CSVDatabase;

import java.util.HashMap;
import java.util.Map;

public class ExpenseManager {
	private final Map<Integer, Expense> expenses = new HashMap<>();
	private final CSVDatabase database;

	public ExpenseManager(CSVDatabase database) {
		this.database = database;

		loadExpenses(database);
	}

	public void addExpense(Expense expense) {
		expenses.put(expense.id(), expense);
		database.saveRecord(expense.toRecord());
	}

	public boolean removeExpense(int expenseId) {
		expenses.remove(expenseId);
		return database.removeRecord(expenseId);
	}

	private void loadExpenses(CSVDatabase database) {
		database.loadAllRecords().forEach(row -> {
			Expense expense = Expense.fromRecord(row);
			expenses.put(expense.id(), expense);
		});
	}

	public Map<Integer, Expense> getExpenses() {
		return expenses;
	}
}
