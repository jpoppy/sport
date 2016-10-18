package com.poppy.sport.exception;

public class RankException extends RuntimeException {
	private static final long serialVersionUID = -1771517377099281841L;

	public RankException() {
	}

	public RankException(Exception e) {
		super(e);
	}
}
