package engine.core.types;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class Color {

    public static final Color BLACK;
    public static final Color WHITE;
    public static final Color CORNFLOWER_BLUE;
    public static final Color RED;
    public static final Color LIME;
    public static final Color GREEN;
    public static final Color YELLOW;

    static {
        BLACK = new Color(0, 0, 0);
        CORNFLOWER_BLUE = new Color(100 / 255f, 149 / 255f, 237 / 255f);
        WHITE = new Color(1, 1, 1);
        RED = new Color(1, 0, 0);
        LIME = new Color(0, 1, 0);
        GREEN = new Color(0, 0.5f, 0);
        YELLOW = new Color(1, 1, 0);
    }
    public float r;
    public float g;
    public float b;
    public float a;

    public Color(float nr, float ng, float nb) {
        set(nr, ng, nb, 1);
    }

    public Color(float nr, float ng, float nb, float na) {
        set(nr, ng, nb, na);
    }

    public final void set(float nr, float ng, float nb, float na) {
        r = nr;
        g = ng;
        b = nb;
        a = na;
    }

    @Override
    public Color clone() {
        return new Color(r, g, b, a);
    }
}
