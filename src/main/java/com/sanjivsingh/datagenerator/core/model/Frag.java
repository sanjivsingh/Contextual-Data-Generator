package com.sanjivsingh.datagenerator.core.model;

/*Each NFA fragment is defined by its 
 * start state and its outgoing arrows
 */

/**
 * @author Sanjiv.Singh
 * 
 */
public class Frag {
	public State start;
	public Ptrlist out;
}
