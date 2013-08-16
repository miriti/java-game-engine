package engine.core;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public final class Logger {

    static public void trace(String s) {
        System.out.println(s);
    }

    static public void trace(float f) {
        System.out.println(f);
    }

    static public void trace(boolean b) {
        System.out.println(b);
    }
}
