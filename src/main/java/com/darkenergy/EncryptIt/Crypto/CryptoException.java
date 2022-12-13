package com.darkenergy.EncryptIt.Crypto;

import java.io.Serial;

public class CryptoException extends Exception {

    /**
	 *
	 */
    @Serial
	private static final long serialVersionUID = 7734949223338730922L;

    public CryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}