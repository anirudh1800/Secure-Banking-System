/**
 * 
 */
package com.group9.bankofaz.exception;

/**
 * @author Anirudh Ruia Gali
 *
 */
public class IllegalTransactionException extends Exception {
	
	private static final long serialVersionUID = -6898566779271468872L;

	public IllegalTransactionException() {
	}

	public IllegalTransactionException(String message) {
		super(message);
	}

	public IllegalTransactionException(Throwable cause) {
		super(cause);
	}

	public IllegalTransactionException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalTransactionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

