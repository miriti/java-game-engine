package engine.display;

import engine.core.types.Color;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class Image extends Quad {

    protected Texture currentTexture;

    public Image(Texture texture) {
        super(texture.getImageWidth(), texture.getImageHeight(), Color.WHITE);
        currentTexture = texture;
    }

    public Image(Texture texture, float imageWidth, float imageHeight) {
        super(imageWidth, imageHeight, Color.WHITE);
        currentTexture = texture;
    }

    @Override
    protected void selfRender() {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        currentTexture.bind();
        super.selfRender();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }
}
