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
public class LoginFormerParamsException extends Exception {

    /**
     * Creates a new instance of <code>LoginFormerParamsException</code> without
     * detail message.
     */
    public LoginFormerParamsException() {
    }

    /**
     * Constructs an instance of <code>LoginFormerParamsException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public LoginFormerParamsException(String msg) {
        super(msg);
    }
}
