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

    public Quad(float quadWidth, float quadHeight, Color quadColor) {
        super();

        width = quadWidth;
        height = quadHeight;
        color = quadColor;

        vertexColor = new Color[]{
            quadColor,
            quadColor,
            quadColor,
            quadColor
        };

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
        color = quadColor;

        vertexColor = new Color[]{
            quadColor,
            quadColor,
            quadColor,
            quadColor
        };

        // TODO Bydlocode
        if (centered) {
            vertexes = new Vector2f[]{
                new Vector2f(-width / 2, -height / 2),
                new Vector2f(width / 2, -height / 2),
                new Vector2f(width / 2, height / 2),
                new Vector2f(-width / 2, height / 2)
            };
        } else {
            vertexes = new Vector2f[]{
                new Vector2f(0, 0),
                new Vector2f(width, 0),
                new Vector2f(width, height),
                new Vector2f(0, height)
            };
        }
    }

    public Color[] getVertexColor() {
        return vertexColor;
    }

    @Override
    protected void selfRender() {
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
}
