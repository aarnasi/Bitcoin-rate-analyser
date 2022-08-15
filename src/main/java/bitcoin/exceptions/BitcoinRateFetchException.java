package bitcoin.exceptions;

/**
 * Exception calls to handle Bitcoin rate exceptions.
 */
public final class BitcoinRateFetchException extends Exception{
    public BitcoinRateFetchException(String message){
        super(message);
    }

}
