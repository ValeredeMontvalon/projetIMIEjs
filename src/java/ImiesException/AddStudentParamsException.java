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
public class AddStudentParamsException extends Exception {

    /**
     * Creates a new instance of <code>AddSiteParamsException</code> without
     * detail message.
     */
    public AddStudentParamsException() {
    }

    /**
     * Constructs an instance of <code>AddSiteParamsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AddStudentParamsException(String msg) {
        super(msg);
    }
}
