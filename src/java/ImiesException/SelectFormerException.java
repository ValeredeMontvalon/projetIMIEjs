/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImiesException;

/**
 *
 * @author bouessay.cdi02
 */
public class SelectFormerException extends Exception {

    /**
     * Creates a new instance of <code>SelectFormerException</code> without
     * detail message.
     */
    public SelectFormerException() {
    }

    /**
     * Constructs an instance of <code>SelectFormerException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SelectFormerException(String msg) {
        super(msg);
    }
}
