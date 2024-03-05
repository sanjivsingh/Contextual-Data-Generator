package com.sanjivsingh.datagenerator.core.model;

/**
 * @author Sanjiv.Singh
 * 
 */
public class State {

	/* data attached to state */
	public int c;

	/**
	 * used for question_mark '?' , plus '+' and star '*' only
	 * 
	 */
	public String range;

	public State out;
	public State out1;

	public int visitCount = 0;
	public int randomIteration = 0;

	/* unique for each state */
	public int index;

	// Used for match execution
	public int lastlist;

	/* Used for data generation */
	public StatePath statePath = new StatePath();

	public State(int c) {
		super();
		this.c = c;
	}

	public State() {
		super();
	}

	@Override
	public String toString() {
		String returnString = "{State : index=" + index + ", c = " + c + " , range = "+ range+" , visitCount = "+ visitCount+" }";
		return returnString;
	}

}
