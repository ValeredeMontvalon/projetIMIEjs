/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImiesException;

/**
 *
 * @author lor.cdi02
 */
public class SelectFormerWithValidLoginException extends Exception {

    /**
     * Creates a new instance of
     * <code>SelectFormerWithValidLoginException</code> without detail message.
     */
    public SelectFormerWithValidLoginException() {
    }

    /**
     * Constructs an instance of
     * <code>SelectFormerWithValidLoginException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SelectFormerWithValidLoginException(String msg) {
        super(msg);
    }
}
