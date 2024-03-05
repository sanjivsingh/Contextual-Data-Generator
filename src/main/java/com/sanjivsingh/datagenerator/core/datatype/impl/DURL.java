package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.DataTypeDefaults;
import com.sanjivsingh.datagenerator.core.DataTypeParameters;
import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.DomainUtil;

public class DURL extends BaseDataType {

	private boolean protocal = DataTypeDefaults.URL_PROTOCAL;
	private boolean host = DataTypeDefaults.URL_HOST;
	private boolean path = DataTypeDefaults.URL_PATH;
	private boolean queryString = DataTypeDefaults.URL_QUERYSTRING;

	@Override
	public String getRandomValue() {
		return DomainUtil.getUrl(this);
	}

	private DURL(int charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {

		DURL dURL = new DURL(charIndex, recordIndex, generatorContext);

		if (dURL.getParameterMap().containsKey(DataTypeParameters.URL_PROTOCAL)) {
			dURL.setProtocal(getBoolean(dURL.getParameterMap(),
					DataTypeParameters.URL_PROTOCAL));
		}
		if (dURL.getParameterMap().containsKey(DataTypeParameters.URL_HOST)) {
			dURL.setHost(getBoolean(dURL.getParameterMap(),
					DataTypeParameters.URL_HOST));
		}
		if (dURL.getParameterMap().containsKey(DataTypeParameters.URL_PATH)) {
			dURL.setPath(getBoolean(dURL.getParameterMap(),
					DataTypeParameters.URL_PATH));
		}
		if (dURL.getParameterMap().containsKey(
				DataTypeParameters.URL_QUERYSTRING)) {
			dURL.setQueryString(getBoolean(dURL.getParameterMap(),
					DataTypeParameters.URL_QUERYSTRING));
		}
		return dURL;
	}

	public boolean isProtocal() {
		return protocal;
	}

	private void setProtocal(boolean protocal) {
		this.protocal = protocal;
	}

	public boolean isHost() {
		return host;
	}

	private void setHost(boolean host) {
		this.host = host;
	}

	public boolean isPath() {
		return path;
	}

	private void setPath(boolean path) {
		this.path = path;
	}

	public boolean isQueryString() {
		return queryString;
	}

	private void setQueryString(boolean queryString) {
		this.queryString = queryString;
	}

}
