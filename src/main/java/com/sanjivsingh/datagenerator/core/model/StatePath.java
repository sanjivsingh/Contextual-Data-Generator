package com.sanjivsingh.datagenerator.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Sanjiv.Singh
 * 
 */
public class StatePath {

	public static int limit = 3;
	public State state;
	public List<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
	public Map<Integer, Integer> prevCount = new HashMap<Integer, Integer>();

	public void patch2() {
		ArrayList<Integer> startList = new ArrayList<Integer>();
		startList.add(state.index);
		paths.add(startList);
	}

	public void patch2(State previousstate) {

		StatePath statePathPrev = previousstate.statePath;
		List<ArrayList<Integer>> paths2 = statePathPrev.paths;
		if (paths.size() == 0) {
			ArrayList<Integer> clonePath = (ArrayList<Integer>) paths2.get(0)
					.clone();
			clonePath.add(state.index);
			paths.add(clonePath);
		} else {
			ArrayList<Integer> clonePath = (ArrayList<Integer>) paths2.get(0)
					.clone();
			clonePath.add(state.index);
			paths.add(0, clonePath);
		}
	}

	public void patch() {
		ArrayList<Integer> startList = new ArrayList<Integer>();
		startList.add(state.index);
		paths.add(startList);
	}

	public void patch(State previousstate) {

		StatePath statePathPrev = previousstate.statePath;

		addFromPrevStateCount(previousstate.index);

		List<ArrayList<Integer>> paths2 = statePathPrev.paths;
		for (ArrayList<Integer> patch : paths2) {
			ArrayList<Integer> clonePath = (ArrayList<Integer>) patch.clone();
			clonePath.add(state.index);
			paths.add(clonePath);
		}
	}

	public void printStatePath() {

		for (ArrayList<Integer> path : paths) {
			System.out.println(path);
		}
		System.out.println(state.c + " " + prevCount);

	}

	public boolean isPrevStateCountReachToLimit(State currentState,
			State prevState) {
		int prevStateIndex = prevState.index;
		// System.out.println(prevCount);
		if (prevCount.containsKey(prevStateIndex)
				&& prevCount.get(prevStateIndex) >= limit) {
			return true;
		}
		return false;
	}

	public boolean addFromPrevStateCount(int prevStateIndex) {
		if (prevCount.containsKey(prevStateIndex)) {
			prevCount.put(prevStateIndex, prevCount.get(prevStateIndex) + 1);

			if (prevCount.get(prevStateIndex) >= limit) {
				return true;
			}
		} else {
			prevCount.put(prevStateIndex, 1);
		}
		return false;
	}

}
