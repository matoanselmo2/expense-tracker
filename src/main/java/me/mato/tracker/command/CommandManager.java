package me.mato.tracker.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
	private final Map<Class<? extends Command>, Command> mCommandMap = new HashMap<>();

	public void registerCommand(Command command) {
		mCommandMap.put(command.getClass(), command);
	}

	public Command getCommand(String name) {
		for (Command command : mCommandMap.values()) {
			for (String executable : command.getExecutables()) {
				if (executable.equalsIgnoreCase(name)) {
					return command;
				}
			}
		}

		return null;
	}

	public void executeCommand(String name, String[] args) {
		Command command = getCommand(name);
		if (command != null) {
			command.execute(args);
		} else {
			System.out.println("Command not found: " + name);
		}
	}

	public ArrayList<Command> getAllCommands() {
		return new ArrayList<>(mCommandMap.values());
	}
}
