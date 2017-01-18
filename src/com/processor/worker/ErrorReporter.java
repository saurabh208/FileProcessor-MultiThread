package com.processor.worker;

import java.util.concurrent.BlockingQueue;

import com.processor.model.RecordBuilder;

public class ErrorReporter implements Runnable {
	private BlockingQueue<String> inDataQueue;
	private String name;

	public ErrorReporter(BlockingQueue<String> inDataQueue) {
		super();
		this.inDataQueue = inDataQueue;
		this.name = "ErrorReportor";
		
	}

	@Override
	public void run() {
		Thread.currentThread().setName(name);
		System.out.println(Thread.currentThread().getName() + ": started");
		String data;
		try {
			while((data = inDataQueue.take()) != null) {
				System.err.println(Thread.currentThread().getName() + ": Reading invalid Data [" + data + "]");
				System.err.println("Erro Data Received :" + data);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": End");
	}
}
