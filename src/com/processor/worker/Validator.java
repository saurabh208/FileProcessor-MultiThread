package com.processor.worker;

import java.util.concurrent.BlockingQueue;

import com.processor.model.RecordBuilder;

public class Validator implements Runnable{
	
	private static final String RECORD_SEPARATOR = ",";
	private static final int TOTAL_FIELD = 5;
	
	private BlockingQueue<String> inLineDataQueue;
	private BlockingQueue<RecordBuilder> outValidateDataQueue;
	private BlockingQueue<String> outInValidateDataQueue;
	private String name;
	
	public Validator(BlockingQueue<String> inLineDataQueue, BlockingQueue<RecordBuilder> outValidateDataQueue,
			BlockingQueue<String> outInValidateDataQueue) {
		super();
		this.inLineDataQueue = inLineDataQueue;
		this.outValidateDataQueue = outValidateDataQueue;
		this.outInValidateDataQueue = outInValidateDataQueue;
		this.name = "Validator";
	}

	@Override
	public void run() {
		Thread.currentThread().setName(name);
		System.out.println(Thread.currentThread().getName() + ": started");
		String inLineData;
		try {
			while((inLineData = inLineDataQueue.take()) != null) {
				System.out.println(Thread.currentThread().getName() + ": Reading line [" + inLineData + "]");
				String[] splittedRecord = inLineData.split(RECORD_SEPARATOR);
				if(splittedRecord.length == 6) {
					System.out.println(Thread.currentThread().getName() + ": putting Validate Line [" + splittedRecord[0] + "]");
					outValidateDataQueue.put(new RecordBuilder(splittedRecord[0]));
				} else {
					System.err.println(Thread.currentThread().getName() + ": putting Invalidate Line [" + splittedRecord[0] + "]");
					outInValidateDataQueue.put(splittedRecord[0]);
				}
			}
			outValidateDataQueue.put(null);
			outInValidateDataQueue.put(null);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": End");
	}
}
