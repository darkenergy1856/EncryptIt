package com.darkenergy.EncryptIt.Crypto;

public class CryptoException extends Exception {

    /**
	 *
	 */
	private static final long serialVersionUID = 7734949223338730922L;

	public CryptoException() {
    }

    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}