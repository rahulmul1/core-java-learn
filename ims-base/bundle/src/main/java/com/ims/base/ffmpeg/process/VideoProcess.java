package com.ims.base.ffmpeg.process;

/*
 * @author Nitishkumar Singh
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import com.ims.base.ffmpeg.utils.ExecutorsThreadPoolSingleton;
import com.ims.base.ffmpeg.utils.VideoConstant;
import com.ims.base.ffmpeg.utils.VideoProcessConstant;
import com.ims.base.ffmpeg.utils.VideoUtils;


public class VideoProcess {

	/*
	 * Prevent from creating instance
	 */
	private VideoProcess() {
	}

	/**
	 * @Input 1. sourceVideoFile - Source Video File path 2. outputDirectory - Output Clip Directory Path 3.
	 *        clipDuration - Size of the Clip in seconds 4. buffer - Buffer in milliseconds to be taken
	 * @Output Divides video in clips and saves in output Directory
	 * @Exception VideoException: if Input file has problems
	 */
	public static List<String> convertVideoToClips(String sourceVideoFile, String outputDirectory, String clipVersion,
			long clipDuration, long buffer) throws Exception {

		// get File Extension
		String fileExtention = VideoUtils.getFileExtension(sourceVideoFile);

		// Get Video Duration
		LocalTime duration = VideoToClip.getDuration(sourceVideoFile);

		// Create Start Counter
		LocalTime start = LocalTime.ofSecondOfDay(VideoConstant.ZERO);

		// Create ClipDuration i.e. interval
		LocalTime interval = LocalTime.ofSecondOfDay(clipDuration);

		// Get Singleton Executor Thread Pool
		ExecutorService executor = ExecutorsThreadPoolSingleton.getExecutorService();

		// Create Callable List of Task
		List<Callable<String>> callableList = new ArrayList<>();

		while (start.isBefore(duration)) {
			LocalTime startTime = start;
			LocalTime endTime = start.plus(interval.toSecondOfDay(), ChronoUnit.SECONDS).plus(buffer,
					ChronoUnit.MILLIS);

			callableList.add(() -> submitProcess(sourceVideoFile, outputDirectory, clipVersion, startTime, endTime,
					duration, fileExtention));
			start = start.plus(interval.toSecondOfDay(), ChronoUnit.SECONDS);
		}

		// Execute all tasks
		List<Future<String>> resultList = executor.invokeAll(callableList);
		return resultList.stream().map(VideoProcess::mapFutureClipPath).filter(Objects::nonNull)
				.collect(Collectors.<String> toList());
	}

	/**
	 * Map Single Future<Clip> to Clip. Null will be returned, if clip generation fails for any timestamp duration
	 */

	private static String mapFutureClipPath(Future<String> future) {
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Submit Process for Execution Create Directory with timestamp Create Json file Create Clip
	 * 
	 * @Exception 1. IOException - Folder already exist 2. InterupptedException - Clip Creation Process Interrupted
	 */
	private static String submitProcess(String sourceVideoFile, String outputDirectory, String clipVersion,
			LocalTime startTime, LocalTime endTime, LocalTime duration, String fileExtention)
			throws IOException, InterruptedException, ExecutionException {
		LocalTime singleClipStartTime = LocalTime.now(); // remove
		String clipSubDirectory = createClipDirectory(clipVersion, startTime,
				(endTime.isBefore(duration) ? endTime : duration));

		String localClipDirectory = String.format(VideoProcessConstant.LOCAL_CLIP_SUBDIRECTORY, outputDirectory,
				clipSubDirectory);

		// create Clip Directory
		Files.createDirectories(Paths.get(localClipDirectory));

		String clipFileName = VideoToClip.toClipProcess(sourceVideoFile, localClipDirectory, startTime,
				(endTime.isBefore(duration) ? endTime : duration), fileExtention);

		// remove
		LocalTime singleClipEndTime = LocalTime.now();
		System.out.println("Total Duration to generate Single Clips: "
				+ Duration.between(singleClipStartTime, singleClipEndTime).toMillis() / 1000);
		// Since, Runnable is not supported. Callable needs type
		return String.join(VideoProcessConstant.EMPTY_STRING, clipSubDirectory, clipFileName);
	}

	/**
	 * Create File Directory for each Video Clip
	 * 
	 * @Exception IOExcpetion - If Directory already exist/ not able to create directory
	 */
	private static String createClipDirectory(String process, LocalTime startTime, LocalTime endTime) {
		return String.format(VideoProcessConstant.CLIP_SUBDIRECTORY,
				VideoUtils.getHourMinuteSecondMillisecondFormat(startTime),
				VideoUtils.getHourMinuteSecondMillisecondFormat(endTime), process,
				VideoUtils.getHourMinuteSecondMillisecondFormat(startTime),
				VideoUtils.getHourMinuteSecondMillisecondFormat(endTime),
				VideoUtils.getHourMinuteSecondMillisecondFormat(startTime),
				VideoUtils.getHourMinuteSecondMillisecondFormat(endTime));
	}

}
