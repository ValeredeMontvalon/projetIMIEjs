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
public class AddPromotionParametreException extends Exception {

    /**
     * Creates a new instance of <code>AddPromotionParametreException</code>
     * without detail message.
     */
    public AddPromotionParametreException() {
    }

    /**
     * Constructs an instance of <code>AddPromotionParametreException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public AddPromotionParametreException(String msg) {
        super(msg);
    }
}
