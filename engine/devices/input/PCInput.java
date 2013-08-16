package engine.devices.input;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class PCInput extends Input {

    public PCInput() {
    }

    @Override
    public void initInput() {
        try {
            Keyboard.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(PCInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(long deltaTime) {
    }

    @Override
    public float getData(String controlName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void destroyInput() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
