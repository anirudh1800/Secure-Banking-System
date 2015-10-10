/**
 * 
 */
package com.group9.bankofaz.exception;

/**
 * @author Anirudh Ruia Gali
 *
 */
public class EmployeeListException extends Exception {

	private static final long serialVersionUID = -6346072519722470992L;

	public EmployeeListException() {
	}

	public EmployeeListException(String message) {
		super(message);
	}

	public EmployeeListException(Throwable cause) {
		super(cause);
	}

	public EmployeeListException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmployeeListException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
