package com.processor.worker;

import java.util.concurrent.BlockingQueue;

import com.processor.model.RecordBuilder;

public class HeartBeat extends Thread {

	BlockingQueue<String> fileReaderQueue;
	BlockingQueue<RecordBuilder> validateDataQueue;
	BlockingQueue<String> inValidateDataQueue;
	
	private static final int MAX_LIMIT = 200;
	
	public HeartBeat(BlockingQueue<String> fileReaderQueue, BlockingQueue<RecordBuilder> validateDataQueue,
			BlockingQueue<String> inValidateDataQueue) {
		super();
		this.fileReaderQueue = fileReaderQueue;
		this.validateDataQueue = validateDataQueue;
		this.inValidateDataQueue = inValidateDataQueue;
		super.setDaemon(true);
	}

	@Override
	public void run() {
		while(true) {
//			if(fileReaderQueue.size() > MAX_LIMIT)
		}
		
	}

}
