package com.sanjivsingh.datagenerator.core.datatype.persist;

import java.util.List;

public interface PersistanceConnector<K> {

	public void generateAndSave(List<K> records);

}
