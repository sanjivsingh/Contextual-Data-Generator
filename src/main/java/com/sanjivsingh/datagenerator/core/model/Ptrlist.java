package com.sanjivsingh.datagenerator.core.model;

/**
 * @author Sanjiv.Singh
 * 
 */
public class Ptrlist {
	public Ptrlist next;
	public State s2;

	// State to with it belongs
	public State s1;
	// option 0 for s1.out and 1 for s1.out1
	public int option = 0;

}
