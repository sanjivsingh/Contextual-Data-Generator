package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

public class DLanguage extends BaseDataType {

	private DLanguage(int charIndex, int recordIndex,
			GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
		// TODO Auto-generated constructor stub
	}

	private String[] languages = { "Mandarin", "Spanish", "English", "Hindi",
			"Arabic", "Portuguese", "Bengali", "Russian", "Japanese",
			"Punjabi", "German", "Javanese", "Wu", "Malay/Indonesian",
			"Telugu", "Vietnamese", "Korean", "French", "Marathi", "Tamil",
			"Urdu", "Persian", "Turkish", "Italian", "Cantonese", "Thai",
			"Gujarati", "Jin", "Min Nan", "Polish", "Pashto", "Kannada",
			"Xiang", "Malayalam", "Sundanese", "Hausa", "Oriya", "Burmese",
			"Hakka", "Ukrainian", "Bhojpuri", "Tagalog", "Yoruba", "Maithili",
			"Swahili", "Uzbek", "Sindhi", "Amharic", "Fula", "Romanian",
			"Oromo", "Igbo", "Azerbaijani", "Awadhi", "Gan", "Cebuano",
			"Dutch", "Kurdish", "Lao", "Serbo-Croatian", "Malagasy", "Saraiki",
			"Nepali", "Sinhalese", "Chittagonian", "Zhuang", "Khmer",
			"Assamese", "Madurese", "Somali", "Marwari", "Magahi", "Haryanvi",
			"Hungarian", "Chhattisgarhi", "Greek", "Chewa", "Deccan", "Akan",
			"Kazakh", "Min Bei", "Sylheti", "Zulu", "Czech", "Kinyarwanda",
			"Dhundhari", "Haitian Creole", "Min Dong", "Ilokano", "Quechua",
			"Kirundi", "Swedish", "Hmong", "Shona", "Uyghur", "Hiligaynon",
			"Mossi", "Xhosa", "Belarusian", "Balochi" };

	@Override
	public String getRandomValue() {
		return languages[RandomUtil.nextInt(0, languages.length - 1)];
	}

	public static DataType getInstance(Integer charIndex, Integer recordIndex,
			GeneratorContext generatorContext) {
		return new DLanguage(charIndex, recordIndex, generatorContext);
	}

}
