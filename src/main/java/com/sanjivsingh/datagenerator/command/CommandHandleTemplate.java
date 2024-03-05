package com.sanjivsingh.datagenerator.command;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class CommandHandleTemplate.
 *
 * @author Sanjiv.Singh
 */
public abstract class CommandHandleTemplate implements CommandHandler {

	/**
	 * Handle command.
	 *
	 * @param args the args
	 */
	@Override
	public void handleCommand(String[] args) {

		// pupulate argument map
		System.out.println("Parsing input params...");
		Map<String, String> argsMap = new HashMap<>();
		for (int i = 1; i < args.length; i = i + 2) {
			argsMap.put(args[i], args[i + 1]);
			System.out.println("    " + args[i] + " = " + args[i + 1]);
		}

		// pass it to manager
		generate(argsMap);

	}

	/**
	 * Generate.
	 *
	 * @param argsMap the args map
	 */
	public abstract void generate(Map<String, String> argsMap);

}
