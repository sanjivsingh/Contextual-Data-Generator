package com.sanjivsingh.datagenerator.core.engine;

import java.util.Set;

import com.sanjivsingh.datagenerator.core.model.SMap;
import com.sanjivsingh.datagenerator.core.model.State;

public interface GenertorFiniteStateMachine {

	public Set<String> generateData(State start, SMap smap);

}
