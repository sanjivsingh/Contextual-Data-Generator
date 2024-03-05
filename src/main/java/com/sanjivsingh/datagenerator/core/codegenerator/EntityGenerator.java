package com.sanjivsingh.datagenerator.core.codegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.sanjivsingh.datagenerator.core.datatype.persist.ParserModel;
import com.sanjivsingh.datagenerator.core.datatype.persist.TypeTypeValue;
import com.sanjivsingh.datagenerator.exception.DataGenFileWriteExceptionTemplate;
import com.sanjivsingh.datagenerator.exception.impl.ExceptionHandlerImp;
/**
 * @author Sanjiv.Singh
 * 
 */
public class EntityGenerator {

	private static final String CLASSES_PATH = "target/classes/";
	private static final String CLASSES_OUTPUT = "target/classes/";
	private static final String path = "com.sanjivsingh.datagenerator.core.codegenerator.temp";

	private String tableName;
	private ParserModel parserModel;
	
	private String className;
	
	public EntityGenerator(String tableName, ParserModel parserModel) {
		super();
		this.tableName = tableName;
		this.parserModel = parserModel;
		
		initialise();
	}

	private void initialise() {
		
		className = "Composite" + tableName;
		final String fullClassPath = path.replace('.', '/') + "/" + className;
		final String writePath = CLASSES_PATH + "/" + fullClassPath + ".java";

		// generate entity source code and save it in .java file
		final StringBuilder source = generateSourceCode(tableName, parserModel,
				writePath);

		// Compile the source code
		try {
			complieSourceCode(writePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Object getEntity(ParserModel parserModel)
			throws ClassNotFoundException, IllegalAccessException,
			InstantiationException, URISyntaxException, NoSuchFieldException,
			IOException {

		final String fullClassName = path + "." + className;
		
		// Get entity instance
		Object whatInstance = null;
		try {
			Class<?> forName = Class.forName(fullClassName);
			Class[] argTypes = {};
			Constructor constructor = forName.getDeclaredConstructor(argTypes);
			Object[] arguments = {};
			whatInstance = constructor.newInstance(arguments);

			Map<String, TypeTypeValue> map = parserModel.getMap();
			for (String field : map.keySet()) {
				TypeTypeValue typeTypeValue = map.get(field);
				String methodParameter = typeTypeValue.getValue();
				Method myMethod = forName.getMethod(
						"set" + field,
						new Class[] { String.class });
				myMethod.invoke(whatInstance, new Object[] { methodParameter });
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return whatInstance;
	}

	private void complieSourceCode(final String writePath) throws IOException {
		File sourceFile1 = new File(writePath);
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager sjfm = compiler.getStandardFileManager(null,
				null, null);

		String testpath = System.getProperty("java.class.path");
		testpath = testpath + ":" + CLASSES_OUTPUT;

		List<String> optionList = new ArrayList<String>();
		optionList.addAll(Arrays.asList("-classpath", testpath, "-d",
				CLASSES_OUTPUT));

		Iterable fileObjects = sjfm.getJavaFileObjectsFromFiles(Arrays
				.asList(sourceFile1));
		JavaCompiler.CompilationTask task = compiler.getTask(null, sjfm, null,
				optionList, null, fileObjects);
		task.call();
		sjfm.close();
		System.out.println("source compiled  : " + writePath);
	}

	private StringBuilder generateSourceCode(String tableName,
			ParserModel parserModel, final String writePath) {

		final String className = "Composite" + tableName;
		final String path = "com.sanjivsingh.datagenerator.core.codegenerator.temp";
		final String fullClassName = path.replace('.', '/') + "/" + className;

		final StringBuilder source = new StringBuilder();
		source.append("\n").append("\n").append("package " + path + ";");
		source.append("\n").append("import javax.persistence.Column;");
		source.append("\n").append("import javax.persistence.Entity;");
		source.append("\n").append("import javax.persistence.Id;");
		source.append("\n").append("import javax.persistence.Table;");
		source.append("\n").append("@Entity");
		source.append("\n").append("@Table(name = \"" + tableName + "\")");

		source.append("\n").append("public class " + className + " {\n");
		source.append("\n").append(" 	public " + className + "() {");
		source.append("\n").append("    }");
		Map<String, TypeTypeValue> map = parserModel.getMap();
		for (String field : map.keySet()) {
			TypeTypeValue typeTypeValue = map.get(field);
			if (typeTypeValue.getType1().equals("Id")) {
				source.append("\n").append("	@" + typeTypeValue.getType1());

			} else {
				source.append("\n").append(
						"	@" + typeTypeValue.getType1() + "(name = \"" + field
								+ "\")");

			}
			source.append("\n").append(
					"	private " + typeTypeValue.getType2() + " " + field + ";");

			source.append("\n").append(
					"	public " + typeTypeValue.getType2() + " get" + field
							+ "() {");
			source.append("\n").append("		return " + field + ";");
			source.append("\n").append("	}");

			source.append("\n").append(
					"	public void set" + field + "(" + typeTypeValue.getType2()
							+ " " + field + ") {");
			source.append("\n").append("		this." + field + " = " + field + ";");
			source.append("\n").append("	}");
		}
		source.append("\n").append("}\n");
		System.out.println(source);
		new DataGenFileWriteExceptionTemplate() {

			@Override
			public void writeProcess(BufferedWriter writer) {
				try {
					writer.write(source.toString());

				} catch (IOException writeException) {
					new ExceptionHandlerImp()
							.handle(writeException,
									"Error processing InputStream for file"
											+ writePath);
				}
			}
		}.handleWriteFile(writePath);

		System.out.println("Source generated : " + writePath);
		return source;
	}

	private String getInitialiseValue(TypeTypeValue typeTypeValue) {
		if (typeTypeValue.getType2().equals("String")) {
			return "\"" + typeTypeValue.getValue() + "\"";
		} else if (typeTypeValue.getType2().equals("boolean")) {
			return typeTypeValue.getValue();
		} else if (typeTypeValue.getType2().equals("int")) {
			return typeTypeValue.getValue();
		} else if (typeTypeValue.getType2().equals("long")) {
			return typeTypeValue.getValue();
		}
		return null;
	}

	public static final void main(String... args)
			throws ClassNotFoundException, URISyntaxException,
			NoSuchFieldException, InstantiationException,
			IllegalAccessException, IOException {
		ParserModel parserModel = new ParserModel();
		parserModel.getMap().put("userId",
				new TypeTypeValue("Id", "String", "011"));
		parserModel.getMap().put("first_name",
				new TypeTypeValue("Column", "String", "Sanjiv"));
		parserModel.getMap().put("last_name",
				new TypeTypeValue("Column", "String", "Singh"));
		parserModel.getMap().put("city",
				new TypeTypeValue("Column", "String", "raebareli"));
		
		EntityGenerator entityGenerator = new EntityGenerator("users", parserModel);
		Object entity = entityGenerator.getEntity(parserModel);
	
		Map<String, String> arg1 = new HashMap<>();
		arg1.put("kundera.nodes", "localhost");
		arg1.put("kundera.port", "9160");
		arg1.put("kundera.keyspace", "KunderaExamples");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(
				"cassandra", arg1);
		EntityManager em = emf.createEntityManager();

		em.persist(entity);
		em.close();
		emf.close();
	}

}