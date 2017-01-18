package com.processor.validation;

public interface ValidationChain<T> {
	public boolean execute(T data);
}
