package com.sanjivsingh.datagenerator.core.datatype.persist.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sanjivsingh.datagenerator.core.codegenerator.EntityGenerator;
import com.sanjivsingh.datagenerator.core.datatype.persist.ConnectorTypes;
import com.sanjivsingh.datagenerator.core.datatype.persist.ParserModel;
import com.sanjivsingh.datagenerator.core.datatype.persist.RecordParser;

public class CassandraPersistanceConnector extends GenericPersistanceConnector {

	private String host;
	private int port = 3099;
	private String keyspace;
	private String columnFamily;

	private RecordParser recordParser;
	private EntityManagerFactory emf;
	private EntityManager em;

	public CassandraPersistanceConnector(String host, int port,
			String keyspace, String columnFamily) {
		super(ConnectorTypes.CASSANDRA);
		this.host = host;
		this.port = port;
		this.keyspace = keyspace;
		this.columnFamily = columnFamily;
	}

	@Override
	public void initialize() {
		Map<String, String> arg1 = new HashMap<>();
		arg1.put("kundera.nodes", host);
		arg1.put("kundera.port", port + "");
		arg1.put("kundera.keyspace", keyspace);
		emf = Persistence.createEntityManagerFactory("cassandra", arg1);
		em = emf.createEntityManager();

	}

	@Override
	public void generateAndSave(List<String> records) {

		
		EntityGenerator entityGenerator = null;
		
		for (String record : records) {
			ParserModel parserModel = recordParser.parser(record);
			Object entity = null;
			try {
				if(null == entityGenerator){
					entityGenerator = new EntityGenerator(columnFamily, parserModel);
				}
			    entity = entityGenerator.getEntity(parserModel);
				
			} catch (ClassNotFoundException | IllegalAccessException
					| InstantiationException | NoSuchFieldException
					| URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			em.persist(entity);
		}
	}

	@Override
	public void destroy() {
		em.close();
		emf.close();
	}

	public RecordParser getRecordParser() {
		return recordParser;
	}

	public void setRecordParser(RecordParser recordParser) {
		this.recordParser = recordParser;
	}

}
