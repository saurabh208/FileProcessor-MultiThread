package com.processor.main;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.processor.model.RecordBuilder;
import com.processor.worker.DatabaseDumper;
import com.processor.worker.ErrorReporter;
import com.processor.worker.TextFileReader;
import com.processor.worker.Validator;

public class Controller {

	public static void main(String[] args) {
		System.out.println(" ***** System Booting Up !!! ******");
		BlockingQueue<String> fileReaderQueue = new LinkedBlockingQueue<>();
		BlockingQueue<RecordBuilder> validateDataQueue = new LinkedBlockingQueue<>();
		BlockingQueue<String> inValidateDataQueue = new LinkedBlockingQueue<>();
		
		File inputFile = new File("G:\\Work\\FileDump.csv");
		Thread fileReaderWorker = new Thread(new TextFileReader(fileReaderQueue, inputFile));
		Thread validatorWorker = new Thread(new Validator(fileReaderQueue, validateDataQueue, inValidateDataQueue));
		Thread databaseWorker = new Thread(new DatabaseDumper(validateDataQueue));
		Thread errorWorker = new Thread(new ErrorReporter(inValidateDataQueue));
		
		fileReaderWorker.start();
		validatorWorker.start();
		databaseWorker.start();
		errorWorker.start();

	}

}
