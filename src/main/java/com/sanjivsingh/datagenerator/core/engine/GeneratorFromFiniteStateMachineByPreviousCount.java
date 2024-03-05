package com.sanjivsingh.datagenerator.core.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sanjivsingh.datagenerator.core.model.SMap;
import com.sanjivsingh.datagenerator.core.model.State;
import com.sanjivsingh.datagenerator.core.model.Symbals;
import com.sanjivsingh.datagenerator.core.util.DataUtil;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

/**
 * @author Sanjiv.Singh
 * 
 */
public class GeneratorFromFiniteStateMachineByPreviousCount implements
		GenertorFiniteStateMachine {

	private State finalState;

	@Override
	public Set<String> generateData(State start, SMap smap) {

		Set<String> returnList = new HashSet<String>();

		populateStatePathByPreviousState(start, null);

		if (finalState != null) {
			List<ArrayList<Integer>> paths = finalState.statePath.paths;
			// System.out.println("--------------------------------");
			// System.out.println("valid paths: ");
			// System.out.println("--------------------------------");
			for (ArrayList<Integer> path : paths) {
				String s = new String();
				for (int i : path) {
					State state = smap.get(i);
					if (state.c != Symbals.FINAL_STATE_SYMBOL_INDEX
							&& state.c != Symbals.SEPERATOR_SYMBOL_INDEX
							&& state.c != Symbals.STAR_SYMBOL_INDEX
							&& state.c != Symbals.PLUS_SYMBOL_INDEX
							&& state.c != Symbals.QUES_SYMBOL_INDEX) {
						s = s
								+ String.valueOf(Character.toChars(DataUtil
										.formatC(state.c)));
					}
				}
				returnList.add(s);
				// System.out.println(path);
			}
			// System.out.println("--------------------------------");
		} else {
			System.out.println("no final state");
		}
		return returnList;
	}

	private void populateStatePathByPreviousState(State currentState,
			State prevState) {

		if (currentState.c == Symbals.FINAL_STATE_SYMBOL_INDEX) {
			finalState = currentState;
		}
		if (null != prevState) {
			boolean isLimitFromPreviousState = currentState.statePath
					.isPrevStateCountReachToLimit(currentState, prevState);
			if (isLimitFromPreviousState) {
				return;
			}
			currentState.statePath.patch(prevState);
		} else {
			currentState.statePath.patch();
		}

		int randomNum = RandomUtil.nextInt(1, 2);
		if (randomNum == 2) {
			followSecond(currentState);
			followFirst(currentState);
		} else {
			followFirst(currentState);
			followSecond(currentState);
		}
	}

	private void followSecond(State currentState) {
		if (null != currentState.out1) {
			populateStatePathByPreviousState(currentState.out1, currentState);
		}
	}

	private void followFirst(State currentState) {
		if (null != currentState.out) {
			populateStatePathByPreviousState(currentState.out, currentState);
		}
	}

}
