package com.processor.worker;

import java.util.concurrent.BlockingQueue;

import com.processor.model.RecordBuilder;

public class DatabaseDumper implements Runnable {
	private BlockingQueue<RecordBuilder> inDataQueue;
	private String name;

	public DatabaseDumper(BlockingQueue<RecordBuilder> inDataQueue) {
		super();
		this.inDataQueue = inDataQueue;
		this.name = "DataBaseDumper";
	}

	@Override
	public void run() {
		Thread.currentThread().setName(name);
		System.out.println(Thread.currentThread().getName() + ": started");
		RecordBuilder data;
		try {
			while((data = inDataQueue.take()) != null) {
				System.out.println(Thread.currentThread().getName() + ": Reading valid Data [" + data + "]");
				System.out.println("Data Received : " + data);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": End");
	}
}
