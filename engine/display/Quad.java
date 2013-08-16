package engine.display;

import engine.core.types.Color;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class Quad extends DisplayObject {

    protected Vector2f[] texCoords = new Vector2f[]{
        new Vector2f(0, 0),
        new Vector2f(1, 0),
        new Vector2f(1, 1),
        new Vector2f(0, 1)
    };
    protected Color[] vertexColor;
    protected Vector2f[] vertexes;
    private boolean centeredQuad = false;

    public Quad(float quadWidth, float quadHeight, Color quadColor) {
        super();

        width = quadWidth;
        height = quadHeight;
        setColor(quadColor);

        vertexes = new Vector2f[]{
            new Vector2f(0, 0),
            new Vector2f(width, 0),
            new Vector2f(width, height),
            new Vector2f(0, height)
        };


    }

    public Quad(float quadWidth, float quadHeight, Color quadColor, boolean centered) {
        super();

        width = quadWidth;
        height = quadHeight;
        setColor(quadColor);

        vertexes = new Vector2f[]{
            new Vector2f(0, 0),
            new Vector2f(width, 0),
            new Vector2f(width, height),
            new Vector2f(0, height)
        };

        centeredQuad = centered;
    }

    @Override
    public final void setColor(Color color) {
        super.setColor(color);
        vertexColor = new Color[]{
            color,
            color,
            color,
            color
        };
    }

    public Color[] getVertexColor() {
        return vertexColor;
    }

    @Override
    protected void selfRender() {

        if (centeredQuad) {
            vertexes[0].set(-width / 2, -height / 2);
            vertexes[1].set(width / 2, -height / 2);
            vertexes[2].set(width / 2, height / 2);
            vertexes[3].set(-width / 2, height / 2);
        } else {
            vertexes[0].set(0, 0);
            vertexes[1].set(width, 0);
            vertexes[2].set(width, height);
            vertexes[3].set(0, height);
        }

        glBegin(GL_QUADS);

        glTexCoord2f(texCoords[0].x, texCoords[0].y);
        glVertex2f(vertexes[0].x, vertexes[0].y);

        glTexCoord2f(texCoords[1].x, texCoords[1].y);
        glVertex2f(vertexes[1].x, vertexes[1].y);

        glTexCoord2f(texCoords[2].x, texCoords[2].y);
        glVertex2f(vertexes[2].x, vertexes[2].y);

        glTexCoord2f(texCoords[3].x, texCoords[3].y);
        glVertex2f(vertexes[3].x, vertexes[3].y);

        glEnd();
    }

    public void setTiles(float horizontal, float vertical) {
        texCoords[1].x = horizontal;
        texCoords[2].x = horizontal;
        texCoords[2].y = vertical;
        texCoords[3].y = vertical;
    }
}
