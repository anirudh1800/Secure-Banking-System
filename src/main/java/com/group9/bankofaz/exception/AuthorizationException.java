/**
 * 
 */
package com.group9.bankofaz.exception;

/**
 * @author Anirudh Ruia Gali
 *
 */
public class AuthorizationException extends Exception {

	private static final long serialVersionUID = -921468449528158485L;

	public AuthorizationException() {
	}

	public AuthorizationException(String message) {
		super(message);
	}

	public AuthorizationException(Throwable cause) {
		super(cause);
	}

	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthorizationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
