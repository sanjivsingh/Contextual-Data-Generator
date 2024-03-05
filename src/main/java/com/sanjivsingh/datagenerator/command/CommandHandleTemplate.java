package com.sanjivsingh.datagenerator.command;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sanjiv.Singh
 * 
 */
public abstract class CommandHandleTemplate implements CommandHandler {

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

	public abstract void generate(Map<String, String> argsMap);

}
