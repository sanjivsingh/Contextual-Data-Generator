package com.sanjivsingh.datagenerator.core.model;

import java.util.ArrayList;
import java.util.List;

public class IScope {

	public int symbol;
	public IScope parent = null;
	public List<IScope> childs = new ArrayList<IScope>();

}
