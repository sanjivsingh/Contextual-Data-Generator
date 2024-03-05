package com.sanjivsingh.datagenerator.core.datatype.persist;

import java.util.Map;

import com.sanjivsingh.datagenerator.core.datatype.persist.impl.CassandraPersistanceConnector;
import com.sanjivsingh.datagenerator.core.datatype.persist.impl.FilePersistanceConnector;
import com.sanjivsingh.datagenerator.core.datatype.persist.impl.GenericPersistanceConnector;
import com.sanjivsingh.datagenerator.core.datatype.persist.impl.KafkaPersistanceConnector;
import com.sanjivsingh.datagenerator.core.datatype.persist.impl.ListPersistanceConnector;
import com.sanjivsingh.datagenerator.core.datatype.persist.impl.RabbitMqPersistanceConnector;

public class PersistanceConnectorFactory implements ConnectorFactory, LifeCycleManager {

	private ConnectorTypes connectorType;
	private Map<String, String> params;

	@Override
	public void initialize() {

	}

	@Override
	public boolean isThreadSafe() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void load(ConnectorTypes connectorTypes, Map<String, String> conProperties) {
		this.connectorType = connectorTypes;
		this.params = conProperties;

	}

	@Override
	public GenericPersistanceConnector getConnectorInstance() {

		// System.out.println("getConnectorInstance  ...");
		if (connectorType.equals(ConnectorTypes.CASSANDRA)) {
			return new CassandraPersistanceConnector(params.get(ConnectorConstants.CASSANDRA_HOST), Integer.parseInt(params.get(ConnectorConstants.CASSANDRA_PORT)),
					params.get(ConnectorConstants.CASSANDRA_KEYSPACE), params.get(ConnectorConstants.CASSANDRA_COLUMNFAMILY));
		} else if (connectorType.equals(ConnectorTypes.COUCHDB)) {

		} else if (connectorType.equals(ConnectorTypes.FILE)) {
			return new FilePersistanceConnector(params.get(ConnectorConstants.FILE_INPUT_DIR), params.get(ConnectorConstants.FILE_OUTPUT_DIR),
					params.get(ConnectorConstants.FILE_SEPERATOR));
		} else if (connectorType.equals(ConnectorTypes.HBASE)) {

		} else if (connectorType.equals(ConnectorTypes.KAFKA)) {
			return new KafkaPersistanceConnector(params.get(ConnectorConstants.KAFKA_BROKER), Integer.parseInt(params.get(ConnectorConstants.KAFKA_PORT)),
					params.get(ConnectorConstants.KAFKA_TOPIC));
		} else if (connectorType.equals(ConnectorTypes.LIST)) {
			return new ListPersistanceConnector();
		} else if (connectorType.equals(ConnectorTypes.MONGODB)) {

		} else if (connectorType.equals(ConnectorTypes.NEO4J)) {

		} else if (connectorType.equals(ConnectorTypes.ORACLE_KVSTORE)) {

		} else if (connectorType.equals(ConnectorTypes.RABBITMQ)) {
			return new RabbitMqPersistanceConnector(params.get(ConnectorConstants.RABBITMQ_HOST), Integer.parseInt(params.get(ConnectorConstants.RABBITMQ_PORT)),
					params.get(ConnectorConstants.RABBITMQ_QUEUE));
		} else if (connectorType.equals(ConnectorTypes.REDIS)) {

		}

		return null;
	}

}
