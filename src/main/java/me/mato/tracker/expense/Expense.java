package me.mato.tracker.expense;

import java.time.LocalDate;

public record Expense(int id, String description, double amount, LocalDate date) {

	public String[] toRecord() {
		return new String[] {
			String.valueOf(id),
			description,
			String.valueOf(amount),
			date.toString()
		};
	}

	public static Expense fromRecord(String[] record) {
		int id = Integer.parseInt(record[0]);
		String description = record[1];
		double amount = Double.parseDouble(record[2]);
		LocalDate date = LocalDate.parse(record[3]);
		return new Expense(id, description, amount, date);
	}

}
