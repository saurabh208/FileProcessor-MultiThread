package com.processor.worker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class TextFileReader implements Runnable {

	private BlockingQueue<String> fileReaderQueue;
	private File inputFile;
	private BufferedReader fileReader;
	private String name;

	public TextFileReader(BlockingQueue<String> fileReaderQueue, File inputFile) {
		super();
		this.fileReaderQueue = fileReaderQueue;
		this.inputFile = inputFile;
		this.name = "TextFileReader";

	}

	@Override
	public void run() {
		Thread.currentThread().setName(name);
		System.out.println(Thread.currentThread().getName() + ": started");
		try {
			this.fileReader = new BufferedReader(new FileReader(inputFile.getAbsolutePath()));
			String line;
			while ((line = fileReader.readLine()) != null) {
				System.out.println(Thread.currentThread().getName() + ": putting line [" + line + "]");
				fileReaderQueue.put(line);
			}
			fileReaderQueue.put("");
			System.out.println(Thread.currentThread().getName() + ": No more Read");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (this.fileReader != null) {
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(Thread.currentThread().getName() + ": End");
	}

}
