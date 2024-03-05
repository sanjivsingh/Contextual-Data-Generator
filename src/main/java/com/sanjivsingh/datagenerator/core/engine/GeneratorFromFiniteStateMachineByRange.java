package com.sanjivsingh.datagenerator.core.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sanjivsingh.datagenerator.common.PropertiesManager;
import com.sanjivsingh.datagenerator.core.model.SMap;
import com.sanjivsingh.datagenerator.core.model.State;
import com.sanjivsingh.datagenerator.core.model.Symbals;
import com.sanjivsingh.datagenerator.core.util.DataUtil;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

/**
 * @author Sanjiv.Singh
 * 
 */
public class GeneratorFromFiniteStateMachineByRange implements
		GenertorFiniteStateMachine {

	private State finalState;
	private static int versions = Integer.parseInt(PropertiesManager
			.getConfigurations().getValue("record.versions"));

	@Override
	public Set<String> generateData(State start, SMap smap) {

		Set<String> returnList = new HashSet<String>();
		for (int j = 1; j <= versions; j++) {
			// refresh path data
			refreshPathData(smap);

			// populate path
			populateStatePathByRange(start, null);

			if (finalState != null) {
				List<ArrayList<Integer>> paths = finalState.statePath.paths;

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
				}

			} else {
				System.out.println("no final state");
			}
		}
		return returnList;
	}

	private void refreshPathData(SMap smap) {

		for (int index : smap.smap.keySet()) {
			State state = smap.get(index);
			state.visitCount = 0;
			state.statePath.paths = new ArrayList<ArrayList<Integer>>();

			String range = state.range;
			if (null != range) {
				int minRange = Integer.parseInt(range.split("-")[0]);
				int maxRange = Integer.parseInt(range.split("-")[1]);
				state.randomIteration = RandomUtil.nextInt(minRange, maxRange);
			}
		}
	}

	private void populateStatePathByRange(State currentState, State prevState) {

		// update state visitCount....
		currentState.visitCount++;

		if (currentState.c == Symbals.FINAL_STATE_SYMBOL_INDEX) {
			finalState = currentState;
		}

		int nextMove = 2;
		if (null != prevState) {
			nextMove = isCurrentStateVisitToLimit(currentState, prevState);
			currentState.statePath.patch2(prevState);
		} else {
			currentState.statePath.patch2();
		}

		if (nextMove == 2) {
			int randomNum = RandomUtil.nextInt(1, 2);
			if (randomNum == 2) {
				followSecond(currentState);
				followFirst(currentState);
			} else {
				followFirst(currentState);
				followSecond(currentState);
			}
		} else if (nextMove == 1) {
			followSecond(currentState);
		} else if (nextMove == 0) {
			followFirst(currentState);
		} else {
			System.out.println("Do nothing !!!!");
		}

	}

	private int isCurrentStateVisitToLimit(State currentState, State prevState) {
		if (currentState.c == Symbals.SEPERATOR_SYMBOL_INDEX
				|| currentState.c == Symbals.STAR_SYMBOL_INDEX
				|| currentState.c == Symbals.PLUS_SYMBOL_INDEX
				|| currentState.c == Symbals.QUES_SYMBOL_INDEX) {

			String range = currentState.range;
			if (null == range) {
				return 2;
			}

			if (currentState.c == Symbals.QUES_SYMBOL_INDEX) {
				if (currentState.visitCount == currentState.randomIteration) {
					return 1;
				} else {
					return 0;
				}
			}

			if (currentState.c == Symbals.PLUS_SYMBOL_INDEX) {
				if (currentState.visitCount == currentState.randomIteration) {
					return 1;
				} else {
					return 0;
				}
			}
			if (currentState.c == Symbals.STAR_SYMBOL_INDEX) {
				if (currentState.visitCount == currentState.randomIteration + 1) {
					return 1;
				} else {
					return 0;
				}
			}
		}
		return 2;
	}

	private void followSecond(State currentState) {
		if (null != currentState.out1) {
			populateStatePathByRange(currentState.out1, currentState);
		}
	}

	private void followFirst(State currentState) {
		if (null != currentState.out) {
			populateStatePathByRange(currentState.out, currentState);
		}
	}

}
