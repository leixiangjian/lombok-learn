package com.lombok.learn.annotation.nonnull;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NonNullAnnotation {

	public static void main(String[] args) {
		NonNullAnnotation nonNullAnnotation = new NonNullAnnotation();
		String name = "test";
		log.info(nonNullAnnotation.nonNull(name));
	}

	public String nonNull(@NonNull String name) {
		return name;
	}
}
