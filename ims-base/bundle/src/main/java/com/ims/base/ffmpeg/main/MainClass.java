package com.ims.base.ffmpeg.main;

import com.ims.base.ffmpeg.process.VideoProcess;

public class MainClass {

	// main method
	public static void main(String[] args) {
		try {
			VideoProcess.convertVideoToClips("/Users/rahaggar/DevTools/Java-Project-1/joinvideos/videotoclip.mp4", "/Users/rahaggar/DevTools/Java-Project-1/joinvideos/output", "split_1", 2, 200);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
