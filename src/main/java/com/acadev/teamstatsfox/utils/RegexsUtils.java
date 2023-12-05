package com.acadev.teamstatsfox.utils;

public class RegexsUtils {
	
	/** Sizes */
	public static final int SIZE_MIN_DNI = 5;
	public static final int SIZE_MAX_DNI = 8;

	/** Regex patterns */
	public static final String REGEX_ONLY_DIGITS = "[0-9]*";
	public static final String REGEX_ONLY_LETTERS = "[a-zA-Z]*";
	public static final String REGEX_ONLY_LETTERS_UPPER = "[A-Z]*";
	public static final String REGEX_ONLY_LETTERS_LOWER = "[a-z]*";
	public static final String REGEX_ONLY_LETTERS_AND_DIGITS = "[a-zA-Z0-9]*";
	public static final String REGEX_ONLY_LETTERS_UPPER_AND_DIGITS = "[A-Z0-9]*";
	public static final String REGEX_ONLY_LETTERS_LOWER_AND_DIGITS = "[a-z0-9]*";

	public static final String REGEX_POSITIONS = "ARQUERO|DEFENSOR|VOLANTE|DELANTERO";
	public static final String REGEX_SECOND_POSITIONS = "ARQUERO|DEFENSOR|VOLANTE|DELANTERO";
	
}
