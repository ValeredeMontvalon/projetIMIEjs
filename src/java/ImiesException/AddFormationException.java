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
public class AddFormationException extends Exception{
            /**
     * Creates a new instance of <code>AddSiteException</code> without detail
     * message.
     */
    public AddFormationException() {
    }

    /**
     * Constructs an instance of <code>AddSiteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AddFormationException(String msg) {
        super(msg);
    }
}
