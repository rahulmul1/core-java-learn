package com.ims.base.ffmpeg.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.ims.base.ffmpeg.utils.VideoConstant;
import com.ims.base.ffmpeg.utils.VideoUtils;


/*
 * @author Nitishkumar Singh
 * @Description: class will use ffmpeg to break an source video into clips
 */
public class VideoToClip {

	/*
	 * Prevent from creating instance
	 */
	private VideoToClip() {
	}

	/**
	 * Get Video Duration is milliseconds
	 * 
	 * @Exception IOException - File does not exist VideoException- Video File have data issues
	 */
	static LocalTime getDuration(String sourceVideoFile) throws Exception {
		if (!Paths.get(sourceVideoFile).toFile().exists())
			throw new Exception("File does not exist!!");

		Process proc = new ProcessBuilder(VideoConstant.SHELL, VideoConstant.SHELL_COMMAND_STRING_ARGUMENT,
				String.format(VideoConstant.DURATION_COMMAND, sourceVideoFile)).start();
		boolean errorOccured = (new BufferedReader(new InputStreamReader(proc.getErrorStream())).lines()
				.count() > VideoConstant.ZERO);
		String durationInSeconds = new BufferedReader(new InputStreamReader(proc.getInputStream())).lines()
				.collect(Collectors.joining(System.lineSeparator()));
		proc.destroy();
		if (errorOccured || (durationInSeconds.length() == VideoConstant.ZERO))
			throw new Exception("Video File have some issues!");
		else
			return VideoUtils.parseHourMinuteSecondMillisecondFormat(durationInSeconds);
	}

	/**
	 * Create Clips for Video Using Start and End Second
	 * 
	 * @Exception IOException - Clip Creation Process Failed InterruptedException - Clip Creation task get's failed
	 */
	static String toClipProcess(String sourceVideo, String outputDirectory, LocalTime start, LocalTime end,
			String fileExtension) throws IOException, InterruptedException, ExecutionException {

		String clipName = String.format(VideoConstant.CLIP_FILE_NAME,
				VideoUtils.getHourMinuteSecondMillisecondFormat(start),
				VideoUtils.getHourMinuteSecondMillisecondFormat(end), fileExtension);

		String command = String.format(VideoConstant.FFMPEG_OUTPUT_COMMAND, sourceVideo,
				VideoUtils.getHourMinuteSecondMillisecondFormat(start),
				VideoUtils.getHourMinuteSecondMillisecondFormat(end.minus(start.toNanoOfDay(), ChronoUnit.NANOS)),
				outputDirectory, clipName);
		LocalTime startTime = LocalTime.now();
		System.out.println("Clip Name: " + clipName);
		System.out.println("FFMPEG Process Execution Started");
		CompletableFuture<Process> completableFuture = CompletableFuture.supplyAsync(() -> {
			try {
				return executeProcess(command);
			} catch (InterruptedException | IOException ex) {
				throw new RuntimeException(ex);
			}
		});
		completableFuture.get();
		// remove
		LocalTime endTime = LocalTime.now();
		System.out.println("Clip Name: " + clipName);
		System.out.println("FFMPEG Process Execution Finished");
		System.out.println("Duration: " + Duration.between(startTime, endTime).toMillis() / 1000);

		return clipName;
	}

	/**
	 * Create and Execute Process for each command
	 */
	static Process executeProcess(String command) throws InterruptedException, IOException {
		Process clipProcess = Runtime.getRuntime().exec(command);
		clipProcess.waitFor();
		return clipProcess;
	}
}
