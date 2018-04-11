package com.ims.base.ffmpeg.utils;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/*
 * @author Nitishkumar Singh
 * @Description: class will use ffmpeg to break an source video into clips
 */
public class VideoUtils {

	private static DateTimeFormatter dateTimeFormatterHourMinuteSecondMillisecond = new DateTimeFormatterBuilder()
			.appendPattern(VideoConstant.HOUR_MINUTE_SECOND).appendFraction(ChronoField.MICRO_OF_SECOND,
					VideoConstant.MILLISECOND_MIN_LENGTH, VideoConstant.MILLISECOND_MAX_LENGTH, Boolean.TRUE)
			.parseStrict().toFormatter();

	/**
	 * Private constructor, so no one can create an instance
	 */
	private VideoUtils() {
	}

	/**
	 * Get File extension from source path
	 * 
	 * @Exception VideoException - Video File name is not correct
	 */
	public static String getFileExtension(String sourceVideoFile) throws Exception {
		int lastIndexOfDot = sourceVideoFile.lastIndexOf(VideoConstant.DOT);
		if (lastIndexOfDot > VideoConstant.ZERO)
			return sourceVideoFile.substring(lastIndexOfDot + 1);
		else
			throw new Exception("Video file is not correct");
	}

	public static LocalTime parseHourMinuteSecondMillisecondFormat(String time) {
		return dateTimeFormatterHourMinuteSecondMillisecond.parse(time, LocalTime::from);
	}

	public static String getHourMinuteSecondMillisecondFormat(LocalTime time) {
		String timeInString = dateTimeFormatterHourMinuteSecondMillisecond.format(time);
		if (timeInString.length() > VideoConstant.TIME_STRING_MAX_LENGTH)
			return timeInString.substring(VideoConstant.ZERO, VideoConstant.TIME_STRING_MAX_LENGTH);
		else
			return timeInString;
	}
}
