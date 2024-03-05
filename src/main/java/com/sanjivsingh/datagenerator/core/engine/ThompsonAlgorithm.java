package com.sanjivsingh.datagenerator.core.engine;

import java.util.ArrayList;
import java.util.Stack;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.model.Frag;
import com.sanjivsingh.datagenerator.core.model.GeneratorCache;
import com.sanjivsingh.datagenerator.core.model.Ptrlist;
import com.sanjivsingh.datagenerator.core.model.SMap;
import com.sanjivsingh.datagenerator.core.model.State;
import com.sanjivsingh.datagenerator.core.model.Symbals;
import com.sanjivsingh.datagenerator.core.model.TList;

/**
 * @author Sanjiv.Singh
 * 
 */
public class ThompsonAlgorithm {

	class B {
		int nalt;
		int natom;
	}

	int nstate;
	/* matching state */
	private String uniqueID;

	SMap smap = new SMap();
	State matchstate = state(Symbals.FINAL_STATE_SYMBOL_INDEX, null, null, null);
	TList l1 = new TList();
	TList l2 = new TList();
	static int listid;

	public ThompsonAlgorithm(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String regexToPostfix(String regex) {
		int nalt, natom;
		StringBuilder buf = new StringBuilder();

		B paren[] = new B[100];
		int p;

		p = 0;
		nalt = 0;
		natom = 0;

		for (int index = 0; index < regex.length(); index++) {
			char charAt = regex.charAt(index);
			if (charAt == Symbals.L_PRAN) {
				if (natom > 1) {
					--natom;
					buf.append(Symbals.SEPERATOR);
				}
				if (p >= paren.length)
					return null;

				ThompsonAlgorithm a = new ThompsonAlgorithm(uniqueID);
				paren[p] = a.new B();
				paren[p].nalt = nalt;
				paren[p].natom = natom;
				p++;
				nalt = 0;
				natom = 0;
			} else if (charAt == Symbals.ALTERNATE) {
				if (natom == 0)
					return null;
				while (--natom > 0)
					buf.append(Symbals.SEPERATOR);
				nalt++;
			} else if (charAt == Symbals.R_PRAN) {
				if (p == paren.length)
					return null;
				if (natom == 0)
					return null;
				while (--natom > 0)
					buf.append(Symbals.SEPERATOR);
				for (; nalt > 0; nalt--)
					buf.append(Symbals.ALTERNATE);
				--p;
				nalt = paren[p].nalt;
				natom = paren[p].natom;
				natom++;
			} else if (charAt == Symbals.STAR || charAt == Symbals.PLUS
					|| charAt == Symbals.QUESTION_MARK) {
				if (natom == 0)
					return null;
				buf.append(regex.charAt(index));
			} else {
				if (natom > 1) {
					--natom;
					buf.append(Symbals.SEPERATOR);
				}
				buf.append(regex.charAt(index));
				natom++;
			}
		}
		if (p != 0)
			return null;
		while (--natom > 0)
			buf.append(Symbals.SEPERATOR);
		for (; nalt > 0; nalt--)
			buf.append(Symbals.ALTERNATE);
		return String.valueOf(buf);

	}

	/*
	 * Convert postfix regular expression to NFA. Return start state.
	 */
	public State postfixToNFA(String postfix) {

		Stack<Frag> stack = new Stack<Frag>();
		Frag e1, e2, e;
		State s;

		int quesIndex = 0;
		int plusIndex = 0;
		int starIndex = 0;

		if (postfix == null)
			return null;

		for (int index = 0; index < postfix.length(); index++) {
			char charAt = postfix.charAt(index);
			if (charAt == Symbals.SEPERATOR) {
				/* catenate */
				e2 = stack.pop();
				e1 = stack.pop();
				patch(e1.out, e2.start);
				stack.push(frag(e1.start, e2.out));
			} else if (charAt == Symbals.ALTERNATE) {
				/* alternate */
				e2 = stack.pop();
				e1 = stack.pop();
				s = state(Symbals.SEPERATOR_SYMBOL_INDEX, e1.start, e2.start,
						null);
				stack.push(frag(s, append(e1.out, e2.out)));
			} else if (charAt == Symbals.QUESTION_MARK) {
				String range = getRangeForIndexedOperator(charAt, quesIndex++);
				/* zero or one */
				e = stack.pop();
				s = state(Symbals.QUES_SYMBOL_INDEX, e.start, null, range);
				stack.push(frag(s, append(e.out, list1(s, 1))));
			} else if (charAt == Symbals.STAR) {
				String range = getRangeForIndexedOperator(charAt, starIndex++);
				/* zero or more */
				e = stack.pop();
				s = state(Symbals.STAR_SYMBOL_INDEX, e.start, null, range);
				patch(e.out, s);
				stack.push(frag(s, list1(s, 1)));
			} else if (charAt == Symbals.PLUS) {
				String range = getRangeForIndexedOperator(charAt, plusIndex++);
				/* one or more */
				e = stack.pop();
				s = state(Symbals.PLUS_SYMBOL_INDEX, e.start, null, range);
				patch(e.out, s);
				stack.push(frag(e.start, list1(s, 1)));
			} else {
				s = state(postfix.charAt(index), null, null, null);
				stack.push(frag(s, list1(s, 0)));
			}

		}

		e = stack.pop();
		if (!stack.empty())
			return null;

		patch(e.out, matchstate);
		return e.start;
	}

	private String getRangeForIndexedOperator(Character charAt,
			int operationsIndex) {
		int charIndex = (int) charAt.charValue();
		ArrayList<String> ranges = (ArrayList<String>) GeneratorCache
				.getInstance().getMap().get(uniqueID).get(charIndex).getInput()
				.get("ranges");
		String range = ranges.get(operationsIndex);
		if (null == range) {
			if (charIndex == Symbals.STAR) {
				range = DataTypeDefaults.START_RANGE;
			} else if (charIndex == Symbals.PLUS) {
				range = DataTypeDefaults.PLUS_RANGE;
			} else if (charIndex == Symbals.QUESTION_MARK) {
				range = DataTypeDefaults.QUES_RANGE;
			}
		}
		return range;
	}

	/* Initialize Frag struct. */
	static Frag frag(State start, Ptrlist out) {
		Frag frag = new Frag();
		frag.start = start;
		frag.out = out;
		return frag;
	}

	/* Join the two lists l1 and l2, returning the combination. */
	static Ptrlist append(Ptrlist l1, Ptrlist l2) {
		Ptrlist oldl1 = l1;

		while (null != l1.next) {
			l1 = l1.next;
		}

		l1.next = l2;
		return oldl1;
	}

	/* Patch the list of states at out to point to start. */
	static void patch(Ptrlist l, State s) {
		Ptrlist next;

		for (; null != l; l = next) {
			next = l.next;
			l.s2 = s;

			if (l.option == 0) {
				l.s1.out = s;
			} else {
				l.s1.out1 = s;
			}
		}
	}

	/* Create singleton list containing just outp. */
	static Ptrlist list1(State s, int option) {

		Ptrlist l = new Ptrlist();

		if (option == 0) {
			l.s2 = s.out;
		} else {
			l.s2 = s.out1;
		}
		l.option = option;
		l.s1 = s;
		l.next = null;
		return l;
	}

	/* Allocate and initialize State */
	private State state(int c, State out, State out1, String range) {
		State s = new State();
		s.c = c;
		s.range = range;

		s.lastlist = 0;
		s.out = out;
		s.out1 = out1;

		s.index = nstate++;
		s.statePath.state = s;

		smap.put(s.index, s);
		// System.out.println("State added : " + s);
		return s;
	}

	/* Run NFA to determine whether it matches s. */
	public boolean match(State start, String s) {
		int c;
		TList clist, nlist, t;

		clist = startlist(start, l1);
		nlist = l2;
		for (int j = 0; j < s.length(); j++) {
			c = s.charAt(j) & 0xFF;
			step(clist, c, nlist);
			t = clist;
			clist = nlist;
			nlist = t; /* swap clist, nlist */
		}
		return ismatch(clist);
	}

	/*
	 * Step the NFA from the states in clist past the character c, to create
	 * next NFA state set nlist.
	 */
	void step(TList clist, int c, TList nlist) {
		int i;
		State s;

		listid++;
		nlist.n = 0;
		// System.out.println("clist.n : " + clist.n);
		for (i = 0; i < clist.n; i++) {

			s = clist.s.get(i);
			if (s.c == c) {
				// System.out.println(c);
				addstate(nlist, s.out);
			}

		}
	}

	/* Check whether state list contains a match. */
	boolean ismatch(TList l) {

		for (int i = 0; i < l.n; i++)
			if (l.s.get(i) == matchstate)
				return true;
		return false;
	}

	/* Compute initial state list */
	TList startlist(State start, TList l) {
		l.n = 0;
		listid++;
		addstate(l, start);
		return l;
	}

	/* Add s to l, following unlabeled arrows. */
	void addstate(TList l, State s) {
		if (s == null || s.lastlist == listid)
			return;
		s.lastlist = listid;
		if (s.c == Symbals.SEPERATOR_SYMBOL_INDEX) {
			/* follow unlabeled arrows */
			addstate(l, s.out);
			addstate(l, s.out1);
			return;
		}
		l.s.add(l.n++, s);
	}

	public SMap getSmap() {
		return smap;
	}

}
