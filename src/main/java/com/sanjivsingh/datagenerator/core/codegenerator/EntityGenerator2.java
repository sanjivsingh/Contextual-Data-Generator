package com.sanjivsingh.datagenerator.core.codegenerator;

import static java.util.Collections.singletonList;
import static javax.tools.JavaFileObject.Kind.SOURCE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

import com.sanjivsingh.datagenerator.core.datatype.persist.ParserModel;
import com.sanjivsingh.datagenerator.core.datatype.persist.TypeTypeValue;

import sun.misc.Unsafe;

/**
 * @author Sanjiv.Singh
 * 
 */
public class EntityGenerator2 {

	public Object getEntity(String tableName, ParserModel parserModel) throws ClassNotFoundException, IllegalAccessException, InstantiationException, URISyntaxException,
			NoSuchFieldException {

		final String className = "TempClass";
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
				source.append("\n").append("	@" + typeTypeValue.getType1() + "(name = \"" + field + "\")");

			}
			source.append("\n").append("	private " + typeTypeValue.getType2() + " " + field + "  = " + getInitialiseValue(typeTypeValue) + ";");

			source.append("\n").append("	public " + typeTypeValue.getType2() + " get" + field + "() {");
			source.append("\n").append("		return " + field + ";");
			source.append("\n").append("	}");

			source.append("\n").append("	public void set" + field + "(" + typeTypeValue.getType2() + " " + field + ") {");
			source.append("\n").append("		this." + field + " = " + field + ";");
			source.append("\n").append("	}");
		}
		source.append("\n").append("}\n");

		System.out.println(source);

		// A byte array output stream containing the bytes that would be written
		// to the .class file
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		final SimpleJavaFileObject simpleJavaFileObject = new SimpleJavaFileObject(URI.create(fullClassName + ".java"), SOURCE) {

			@Override
			public CharSequence getCharContent(boolean ignoreEncodingErrors) {
				return source;
			}

			@Override
			public OutputStream openOutputStream() throws IOException {
				return byteArrayOutputStream;
			}
		};

		final JavaFileManager javaFileManager = new ForwardingJavaFileManager(ToolProvider.getSystemJavaCompiler().getStandardFileManager(null, null, null)) {

			@Override
			public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
				return simpleJavaFileObject;
			}
		};

		ToolProvider.getSystemJavaCompiler().getTask(null, javaFileManager, null, null, null, singletonList(simpleJavaFileObject)).call();

		final byte[] bytes = byteArrayOutputStream.toByteArray();

		// use the unsafe class to load in the class bytes
		/*
		final Field f = Unsafe.class.getDeclaredField("theUnsafe");
		f.setAccessible(true);
		final Unsafe unsafe = (Unsafe) f.get(null);
		final Class aClass = unsafe.defineClass(fullClassName, bytes, 0, bytes.length, null, null);
		final Object entity = aClass.newInstance();
		return entity;
		*/
		return null;

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

	public static final void main(String... args) throws ClassNotFoundException, URISyntaxException, NoSuchFieldException, InstantiationException, IllegalAccessException {
		ParserModel parserModel = new ParserModel();
		parserModel.getMap().put("userId", new TypeTypeValue("Id", "String", "007"));
		parserModel.getMap().put("first_name", new TypeTypeValue("Column", "String", "Sanjiv"));
		parserModel.getMap().put("last_name", new TypeTypeValue("Column", "String", "Singh"));
		parserModel.getMap().put("city", new TypeTypeValue("Column", "String", "raebareli"));

		Object entity = new EntityGenerator2().getEntity("users", parserModel);
		System.out.println(entity);

		Map<String, String> arg1 = new HashMap<>();
		arg1.put("kundera.nodes", "localhost");
		arg1.put("kundera.port", "9160");
		arg1.put("kundera.keyspace", "KunderaExamples");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cassandra", arg1);
		EntityManager em = emf.createEntityManager();

		em.persist(entity);
		em.close();
		emf.close();
	}

}