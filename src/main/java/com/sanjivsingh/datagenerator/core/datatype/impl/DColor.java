package com.sanjivsingh.datagenerator.core.datatype.impl;

import com.sanjivsingh.datagenerator.core.datatype.BaseDataType;
import com.sanjivsingh.datagenerator.core.datatype.DataType;
import com.sanjivsingh.datagenerator.core.model.GeneratorContext;
import com.sanjivsingh.datagenerator.core.util.RandomUtil;

/**
 * The Class DColor.
 */
public class DColor extends BaseDataType {

	/** The colors. */
	private String[] colors = { "Goldenrod", "Orange", "Purple", "Goldenrod", "Turquoise", "Yellow", "Goldenrod",
			"Blue", "Orange", "Yellow", "Turquoise", "Indigo", "Aquamarine", "Violet", "Blue", "Violet", "Blue",
			"Goldenrod", "Green", "Yellow", "Blue", "Aquamarine", "Yellow", "Puce", "Mauv", "Blue", "Mauv", "Yellow",
			"Teal", "Khaki", "Goldenrod", "Indigo", "Pink", "Maroon", "Purple", "Violet", "Teal", "Fuscia",
			"Aquamarine", "Maroon", "Fuscia", "Khaki", "Teal", "Mauv", "Turquoise", "Fuscia", "Indigo", "Fuscia",
			"Blue", "Green", "Maroon", "Crimson", "Teal", "Puce", "Blue", "Indigo", "Mauv", "Pink", "Pink", "Maroon",
			"Green", "Teal", "Goldenrod", "Orange", "Maroon", "Teal", "Blue", "Aquamarine", "Maroon", "Red", "Blue",
			"Mauv", "Purple", "Indigo", "Aquamarine", "Maroon", "Teal", "Crimson", "Puce", "Pink", "Yellow", "Teal",
			"Teal", "Blue", "Turquoise", "Puce", "Goldenrod", "Yellow", "Maroon", "Blue", "Orange", "Fuscia", "Red",
			"Pink", "Green", "Puce", "Goldenrod", "Maroon", "Mauv", "Green", "Yellow", "Turquoise", "Blue", "Pink",
			"Indigo", "Goldenrod", "Green", "Indigo", "Yellow", "Aquamarine", "Violet", "Turquoise", "Khaki", "Orange",
			"Orange", "Aquamarine", "Puce", "Crimson", "Fuscia", "Pink", "Puce", "Puce", "Crimson", "Goldenrod", "Mauv",
			"Aquamarine", "Indigo", "Violet", "Purple", "Violet", "Fuscia", "Orange", "Aquamarine", "Mauv", "Green",
			"Fuscia", "Blue", "Indigo", "Mauv", "Maroon", "Turquoise", "Purple", "Blue", "Maroon", "Mauv", "Khaki",
			"Pink", "Mauv", "Red", "Mauv", "Green", "Turquoise", "Crimson", "Violet", "Mauv", "Violet", "Goldenrod",
			"Puce", "Crimson", "Fuscia", "Aquamarine", "Indigo", "Crimson", "Fuscia", "Mauv", "Blue", "Crimson",
			"Yellow", "Khaki", "Indigo", "Maroon", "Aquamarine", "Mauv", "Puce", "Yellow", "Blue", "Goldenrod",
			"Yellow", "Pink", "Fuscia", "Yellow", "Khaki", "Khaki", "Indigo", "Yellow", "Teal", "Green", "Indigo",
			"Green", "Pink" };

	/**
	 * Gets the random value.
	 *
	 * @return the random value
	 */
	@Override
	public String getRandomValue() {
		return colors[RandomUtil.nextInt(0, colors.length - 1)];
	}

	/**
	 * Gets the single instance of DColor.
	 *
	 * @param charIndex        the char index
	 * @param recordIndex      the record index
	 * @param generatorContext the generator context
	 * @return single instance of DColor
	 */
	public static DataType getInstance(Integer charIndex, Integer recordIndex, GeneratorContext generatorContext) {
		DColor dc = new DColor(charIndex, recordIndex, generatorContext);
		return dc;
	}

	/**
	 * Instantiates a new d color.
	 *
	 * @param charIndex        the char index
	 * @param recordIndex      the record index
	 * @param generatorContext the generator context
	 */
	private DColor(int charIndex, int recordIndex, GeneratorContext generatorContext) {
		super(charIndex, recordIndex, generatorContext);
	}

}
