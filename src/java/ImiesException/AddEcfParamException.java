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
public class AddEcfParamException extends Exception {

    /**
     * Creates a new instance of <code>AddEcfParamException</code> without
     * detail message.
     */
    public AddEcfParamException() {
    }

    /**
     * Constructs an instance of <code>AddEcfParamException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AddEcfParamException(String msg) {
        super(msg);
    }
}
