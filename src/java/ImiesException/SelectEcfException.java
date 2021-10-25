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
public class SelectEcfException extends Exception{
        /**
     * Creates a new instance of <code>AddEcfParamException</code> without
     * detail message.
     */
    public SelectEcfException() {
    }

    /**
     * Constructs an instance of <code>AddEcfParamException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SelectEcfException(String msg) {
        super(msg);
    }
}
