package com.ims.base.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * The Class LoggerUtil.
 */
public final class LoggerUtil {

	/** The place holder for Application Error Log. */
	private static final String APPLICATION_ERROR = "Business Error";

	/** The place holder for Application Error Log. */
	private static final String SYSTEM_ERROR = "System Error";

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/**
	 * Instantiates a new logger util.
	 */
	private LoggerUtil() {

	}

	/**
	 * Gets the info log.
	 * 
	 * @param className
	 *            the class name
	 * @param messageText
	 *            the message text
	 * @param msgArray
	 *            the msg array
	 * 
	 * @return the info log
	 */
	@SuppressWarnings("rawtypes")
	public static void infoLog(final Class className, final String messageText,
			final Object... msgArray) {
		final Logger logger = LoggerFactory.getLogger(className);
		logger.info(messageText, msgArray);

	}

	/**
	 * Gets the debug log.
	 * 
	 * @param className
	 *            the class name
	 * @param messageText
	 *            the message text
	 * @param msgArray
	 *            the msg array
	 * @return the debug log
	 */
	@SuppressWarnings("rawtypes")
	public static void debugLog(final Class className,
			final String messageText, final Object... msgArray) {
		final Logger logger = LoggerFactory.getLogger(className);
		logger.debug(messageText, msgArray);
	}

	/**
	 * System Error log.
	 * 
	 * @param className
	 *            the class name
	 * @param messageText
	 *            the message text
	 * @param msgArray
	 *            the msg array
	 */
	@SuppressWarnings("rawtypes")
	public static void systemErrorLog(final Class className,
			final String messageText, final Object... msgArray) {

		LoggerUtil.errorLog(className, LoggerUtil.SYSTEM_ERROR
				+ LoggerUtil.SPACE + messageText, msgArray);
	}

	/**
	 * Business Error log.
	 * 
	 * @param className
	 *            the class name
	 * @param messageText
	 *            the message text
	 * @param msgArray
	 *            the msg array
	 */
	@SuppressWarnings("rawtypes")
	public static void applicationErrorLog(final Class className,
			final String messageText, final Object... msgArray) {

		LoggerUtil.errorLog(className, LoggerUtil.APPLICATION_ERROR
				+ LoggerUtil.SPACE + messageText, msgArray);

	}

	/**
	 * Error log.
	 * 
	 * @param className
	 *            the class name
	 * @param messageText
	 *            the message text
	 * @param msgArray
	 *            the msg array
	 */
	@SuppressWarnings("rawtypes")
	private static void errorLog(final Class className,
			final String messageText, final Object... msgArray) {
		final Logger logger = LoggerFactory.getLogger(className);
		logger.error(messageText, msgArray);
	}

	

}
