package me.mato.tracker.command;

public abstract class Command {

	private final String[] executables;
	private final String description;

	public Command(String[] executables, String description) {
		this.executables = executables;
		this.description = description;
	}

	public abstract void execute(String[] args);

	public String[] getExecutables() {
		return executables;
	}

	public String getDescription() {
		return description;
	}

}
