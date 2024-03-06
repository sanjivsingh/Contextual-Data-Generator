package com.sanjivsingh.datagenerator.core.util;

import net._01001111.text.LoremIpsum;

import org.apache.commons.lang3.text.WordUtils;

import com.sanjivsingh.datagenerator.core.datatype.impl.DURL;

/**
 * The Class DomainUtil.
 */
public class DomainUtil {

	/** The tld. */
	/* Original top-level domains */
	private static String[] TLD = { "aero", "asia", "biz", "cat", "com", "com",
			"com", "com", "com", "com", "com", "coop", "edu", "edu", "edu",
			"edu", "edu", "edu", "gov", "gov", "gov", "gov", "gov", "info",
			"int", "jobs", "mil", "mobi", "museum", "name", "net", "org",
			"org", "org", "org", "org", "org", "org", "post", "pro", "tel",
			"travel" };

	/** The ctld. */
	/* Country code top-level domains */
	private static String[] CTLD = { "ac", "ad", "ag", "in", "im", "co", "is",
			"ws", "ch", "it", "me", "md", "mu", "tv", "vg" };

	/** The protocals. */
	/* Protocals */
	private static String[] PROTOCALS = { "http", "https" };

	/** The file extensions. */
	/* file extension */
	private static String[] FILE_EXTENSIONS = { "html", "jsp", "htm", "jpg",
			"png", "gif", "cgi", "pl", "js", "php", "php3", "shtml", "shtm",
			"asp", "cfm", "cfml" };

	/**
	 * Gets the top domain 2.
	 *
	 * @return the top domain 2
	 */
	private static String getTopDomain2() {

		int nextInt = RandomUtil.nextInt(1, 2);
		String word = null;
		if (nextInt == 1) {
			word = TLD[RandomUtil.nextInt(0, TLD.length - 1)];
		} else {
			word = TLD[RandomUtil.nextInt(0, TLD.length - 1)] + "."
					+ CTLD[RandomUtil.nextInt(0, CTLD.length - 1)];
		}
		return word;
	}

	/**
	 * Gets the top domain.
	 *
	 * @return the top domain
	 */
	public static String getTopDomain() {
		String word = TLD[RandomUtil.nextInt(0, TLD.length - 1)];
		return word;

	}

	/**
	 * Gets the sub domain.
	 *
	 * @param length the length
	 * @return the sub domain
	 */
	private static String getSubDomain(int length) {
		LoremIpsum jlorem = new LoremIpsum();

		String sb = new String();
		for (int i = 0; i < RandomUtil.nextInt(1, length); i++) {
			String word = randomWord(jlorem);
			sb = sb + word + ".";
		}
		return sb;
	}

	/*
	 * A complete domain name is valid if it meets the following criteria:
	 * 
	 * A complete domain name must have one or more subdomain names and one
	 * top-level domain name.
	 * 
	 * A complete domain name must use dots (.) to separate domain names. Domain
	 * names must use only alphanumeric characters and dashes (-).
	 * 
	 * Domain names must not begin or end with dashes (-).
	 * 
	 * Domain names must mot have more than 63 characters.
	 * 
	 * The top-level domain name must be one of the predefined top-level domain
	 * names, like (com), (org), or (ca)
	 */

	/**
	 * Gets the domain name.
	 *
	 * @return the domain name
	 */
	public static String getDomainName() {
		StringBuilder sb = new StringBuilder();
		sb.append(getSubDomain(4));
		sb.append(getTopDomain2());
		return WordUtils.capitalizeFully(sb.toString()).toLowerCase();
	}

	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	/*
	 * 
	 * Email contract :
	 * 
	 * All email addresses consist of 3 parts:
	 * 
	 * [User Name] - Identifier of the user mail box.(up to 64 characters
	 * long)(case-insensitive)
	 * 
	 * [At Sign (@)] - Delimiter to separate the user name and the domain name.
	 * 
	 * [Domain Name] - Identifier of the computer system where the user mail box
	 * is located.(maximum of 253 characters)
	 */
	public static String getEmailAddress() {
		StringBuilder sb = new StringBuilder();
		sb.append(getUserName(2));
		sb.append("@");
		sb.append(getSubDomain(2));
		sb.append(getTopDomain2());
		return WordUtils.capitalizeFully(sb.toString()).toLowerCase();
	}

	/**
	 * Gets the user name.
	 *
	 * @param length the length
	 * @return the user name
	 */
	private static String getUserName(int length) {
		LoremIpsum jlorem = new LoremIpsum();
		String sb = new String();
		for (int i = 0; i < RandomUtil.nextInt(1, length); i++) {
			String word = randomWord(jlorem);
			sb = sb + word + ".";
		}
		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * Gets the url.
	 *
	 * @param dUrl the d url
	 * @return the url
	 */
	public static String getUrl(DURL dUrl) {
		StringBuilder sb = new StringBuilder();
		if (dUrl.isProtocal())
			sb.append(getProtocal() + "://");
		if (dUrl.isHost())
			sb.append(getHostName());
		if (dUrl.isPath())
			sb.append(getUrlPath());
		if (dUrl.isQueryString())
			sb.append("?" + getUrlQueryString(10));
		return sb.toString();
	}

	/**
	 * Gets the url query string.
	 *
	 * @param length the length
	 * @return the url query string
	 */
	private static String getUrlQueryString(int length) {
		LoremIpsum jlorem = new LoremIpsum();
		String sb = new String();
		for (int i = 0; i < RandomUtil.nextInt(1, length); i++) {
			if (i == 0) {
				sb = jlorem.randomWord() + "=" + randomWord(jlorem);
			} else {
				sb = sb + "&" + jlorem.randomWord() + "=" + randomWord(jlorem);
			}
		}
		return sb;
	}

	/**
	 * Random word.
	 *
	 * @param jlorem the jlorem
	 * @return the string
	 */
	private static String randomWord(LoremIpsum jlorem) {
		int nextInt = RandomUtil.nextInt(1, 5);
		String query = null;

		if (nextInt == 3) {
			query = jlorem.randomWord() + "-" + jlorem.randomWord();
		} else {
			query = jlorem.randomWord();
		}
		return query;
	}

	/**
	 * Gets the url path.
	 *
	 * @return the url path
	 */
	private static String getUrlPath() {
		LoremIpsum jlorem = new LoremIpsum();
		String sb = new String();
		for (int i = 0; i < RandomUtil.nextInt(1, 6); i++) {
			String word = "/" + randomWord(jlorem);
			sb = sb + word;
		}

		String returnString = sb
				+ ((RandomUtil.nextInt(1, 2) == 1) ? "" : "."
						+ FILE_EXTENSIONS[RandomUtil.nextInt(0,
								FILE_EXTENSIONS.length - 1)]);
		return returnString;
	}

	/**
	 * Gets the host name.
	 *
	 * @return the host name
	 */
	private static String getHostName() {
		StringBuilder sb = new StringBuilder();
		sb.append(getSubDomain(3));
		sb.append(getTopDomain2());
		return WordUtils.capitalizeFully(sb.toString()).toLowerCase();
	}

	/**
	 * Gets the protocal.
	 *
	 * @return the protocal
	 */
	private static String getProtocal() {
		String word = PROTOCALS[RandomUtil.nextInt(0, PROTOCALS.length - 1)];
		return word;
	}

}
