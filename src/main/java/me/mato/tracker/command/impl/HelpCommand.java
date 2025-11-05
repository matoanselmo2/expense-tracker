package me.mato.tracker.command.impl;

import me.mato.tracker.command.Command;
import me.mato.tracker.command.CommandManager;

public class HelpCommand extends Command {
	private final CommandManager mManager;

	public HelpCommand(CommandManager manager) {
		super(new String[]{ "help", "h", "?" }, "Displays help information for available commands.");
		mManager = manager;
	}

	@Override
	public void execute(String[] args) {
		System.out.println("Available commands:");
		for (Command command : mManager.getAllCommands()) {
			String executables = String.join(", ", command.getExecutables());
			System.out.printf(" - %s: %s%n", executables, command.getDescription());
		}
	}
}
