package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;

public class DIndex extends BaseDataType {

	private int start = DataTypeDefaults.INDEX_START;
	private int increament = DataTypeDefaults.INDEX_INCREMENT;

	private DIndex(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
		// TODO Auto-generated constructor stub
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DIndex dIndex = new DIndex(charIndex, recordIndex, generatorContext);
		if (dIndex.getParameterMap()
				.containsKey(DataTypeParameters.INDEX_START)) {
			dIndex.setStart(getInt(dIndex.getParameterMap(),
					DataTypeParameters.INDEX_START));
		}
		if (dIndex.getParameterMap().containsKey(
				DataTypeParameters.INDEX_INCREMENT)) {
			dIndex.setIncreament(getInt(dIndex.getParameterMap(),
					DataTypeParameters.INDEX_INCREMENT));
		}
		return dIndex;
	}

	@Override
	public String getRandomValue() {
		return ((increament * (getRecordIndex() - 1)) + start) + "";
	}

	private void setStart(int start) {
		this.start = start;
	}

	private void setIncreament(int increament) {
		this.increament = increament;
	}

}
