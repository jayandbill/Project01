/**
 * Utilities
 */
package PolynomialExpressionGenerator;

/**
 *
 * @author Nicholson.Bill
 */
public class Util {

    private static boolean debug = true;

    public boolean setDebug(boolean debug) {
        this.debug = debug;
        return debug;
    }
    public static void Debug(String msg) {
        if (debug) System.out.println(msg);
    }
    /**
     * Process an error message.
     * @param msg The error message to be processed.
     */
    public static void LogError(String msg) {
        if (debug == true) {
            System.out.println(msg);
        }
    }
}
