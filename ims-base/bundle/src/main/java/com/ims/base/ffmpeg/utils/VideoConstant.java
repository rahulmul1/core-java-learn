package com.ims.base.ffmpeg.utils;
/*
 * @author Nitishkumar Singh
 * @Description: class will use ffmpeg to break an source video into clips
 */
public final class VideoConstant {
	// hide construtor
	private VideoConstant() {
	}

	public static final int ZERO = 0;

	public static final String DURATION_COMMAND = "ffprobe -i %s -sexagesimal -show_entries format=duration -v quiet -of csv='p=0'";
	public static final String CLIP_FILE_NAME = "%s_%s.%s";
	public static final String FFMPEG_OUTPUT_COMMAND = "ffmpeg -y -i %s -ss %s -t %s -async 1 -strict -2 %s%s";
	public static final String SHELL = "bash";
	public static final String SHELL_COMMAND_STRING_ARGUMENT = "-c";

	public static final String HOUR_MINUTE_SECOND = "H:mm:ss";
	public static final int MILLISECOND_MIN_LENGTH = 3;
	public static final int MILLISECOND_MAX_LENGTH = 6;

	public static final int TIME_STRING_MAX_LENGTH = 11;

	public static final String DOT = ".";
}
