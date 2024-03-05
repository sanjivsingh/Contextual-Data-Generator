package com.sanjivsingh.datagenerator.core.datatype.impl;

import net._01001111.text.LoremIpsum;

import org.apache.commons.lang3.text.WordUtils;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DCompany extends BaseDataType {

	private DCompany(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
		// TODO Auto-generated constructor stub
	}

	String[] suffixs = { "Ltd", "Industries", "Inc.", "Corporation",
			"Incorporated", "Foundation", "Institute", "Consulting", "Inc.",
			"LLP", "LLC", "Corp.", "Limited", "Company", "Associates", "PC" };

	@Override
	public String getRandomValue() {

		StringBuilder sb = new StringBuilder();
		LoremIpsum jlorem = new LoremIpsum();
		for (int i = 0; i < RandomUtil.nextInt(1, 3); i++) {
			String word = jlorem.randomWord();
			sb.append(word + " ");
		}
		sb.append(suffixs[RandomUtil.nextInt(0, suffixs.length - 1)]);
		return WordUtils.capitalizeFully(sb.toString());
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		DCompany dc = new DCompany(charIndex, recordIndex, generatorContext);
		return dc;
	}

}
