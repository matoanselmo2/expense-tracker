package me.mato.tracker.persistance;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public record CSVDatabase(String file, String header) {

	public void initializeCSV() {
		File file = new File(this.file);
		if (!file.exists()) {
			try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
				writer.println(header);
			} catch (IOException e) {
				System.err.println("Error creating CSV file: " + e.getMessage());
			}
		}
	}

	public void saveRecord(String[] record) {
		if (record == null || record.length == 0) {
			throw new IllegalArgumentException("Record cannot be null or empty");
		}

		try (PrintWriter writer = new PrintWriter(new FileWriter(this.file, true))) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < record.length; i++) {
				sb.append(record[i]);
				if (i < record.length - 1) {
					sb.append(",");
				}
			}
			writer.println(sb);
		} catch (IOException e) {
			System.err.println("Error writing to CSV file: " + e.getMessage());
		}
	}

	public boolean removeRecord(int index) {
		List<String[]> allRecords = loadAllRecordsWithHeader();

		if (allRecords.isEmpty() || index + 1 >= allRecords.size()) {
			return false; // Index out of bounds or only header exists
		}

		// +1 because index 0 is header, data starts at index 1
		allRecords.remove(index + 1);
		return writeAllRecords(allRecords);
	}

	private boolean writeAllRecords(List<String[]> records) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(this.file))) {
			for (String[] record : records) {
				writer.println(convertToCSVLine(record));
			}
			return true;
		} catch (IOException e) {
			System.err.println("Error writing records to CSV: " + e.getMessage());
			return false;
		}
	}

	public List<String[]> loadAllRecords() {
		List<String[]> records = new ArrayList<>();
		File file = new File(this.file);

		if (!file.exists()) {
			return records;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			boolean isFirstLine = true;

			while ((line = reader.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue; // Skip header
				}

				String[] record = parseCSVLine(line);
				records.add(record);
			}
		} catch (IOException e) {
			System.err.println("Error loading records from CSV: " + e.getMessage());
		}

		return records;
	}

	// Load all records including header
	public List<String[]> loadAllRecordsWithHeader() {
		List<String[]> records = new ArrayList<>();
		File file = new File(this.file);

		if (!file.exists()) {
			return records;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] record = parseCSVLine(line);
				if (record != null) {
					records.add(record);
				}
			}
		} catch (IOException e) {
			System.err.println("Error loading records from CSV: " + e.getMessage());
		}

		return records;
	}

	// Convert array of strings to a CSV line
	public String convertToCSVLine(String[] fields) {
		if (fields == null || fields.length == 0) {
			return "";
		}

		StringBuilder csvLine = new StringBuilder();
		for (int i = 0; i < fields.length; i++) {
			if (i > 0) {
				csvLine.append(",");
			}
			csvLine.append(escapeCSV(fields[i]));
		}
		return csvLine.toString();
	}

	// Parse CSV line into array of strings
	public String[] parseCSVLine(String csvLine) {
		if (csvLine == null || csvLine.trim().isEmpty()) {
			return new String[0];
		}

		List<String> fields = new ArrayList<>();
		StringBuilder field = new StringBuilder();
		boolean inQuotes = false;

		for (int i = 0; i < csvLine.length(); i++) {
			char c = csvLine.charAt(i);

			if (c == '"') {
				inQuotes = !inQuotes;
			} else if (c == ',' && !inQuotes) {
				fields.add(field.toString());
				field.setLength(0);
			} else {
				field.append(c);
			}
		}

		fields.add(field.toString());
		return fields.toArray(new String[0]);
	}

	public String escapeCSV(String field) {
		if (field == null) {
			return "";
		}
		if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
			return "\"" + field.replace("\"", "\"\"") + "\"";
		}
		return field;
	}

	public String unescapeCSV(String field) {
		if (field == null) {
			return "";
		}
		if (field.startsWith("\"") && field.endsWith("\"")) {
			field = field.substring(1, field.length() - 1);
			field = field.replace("\"\"", "\"");
		}
		return field;
	}

	public String[] getHeader() {
		return parseCSVLine(this.header);
	}

	public void clearAllData() {
		File file = new File(this.file);
		if (file.exists()) {
			file.delete();
		}
		initializeCSV();
	}

	public boolean csvFileExists() {
		return new File(this.file).exists();
	}

	public int getRecordCount() {
		return loadAllRecords().size();
	}
}
