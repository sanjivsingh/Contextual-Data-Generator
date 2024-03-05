package com.sanjivsingh.datagenerator.command;

import java.util.Date;

import com.sanjivsingh.datagenerator.command.handler.FileCommandHandler;
import com.sanjivsingh.datagenerator.command.handler.RabbitMQCommandHandler;
import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorTypes;
import com.sanjivsingh.datagenerator.core.util.DataUtil;

/**
 * The Class ContextualDataGenerator.
 *
 * @author Sanjiv.Singh
 */
public class ContextualDataGenerator {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		System.out.println("------------Handling request : ");
		Date start = new Date();
		System.out.println("Started at : " + DataUtil.getDateString(start));

		System.out.println("Type       : " + args[0]);
		if (args[0].equalsIgnoreCase(ConnectorTypes.FILE.getLabel())) {
			FileCommandHandler fch = new FileCommandHandler();
			fch.handleCommand(args);
		} else if (args[0].equalsIgnoreCase(ConnectorTypes.RABBITMQ.getLabel())) {
			RabbitMQCommandHandler rch = new RabbitMQCommandHandler();
			rch.handleCommand(args);
		}
		Date end = new Date();
		System.out.println("\nFinished at : " + DataUtil.getDateString(end));
		System.out.println("Time Taken  : " + DataUtil.getDateDiff(end, start));

	}

}
