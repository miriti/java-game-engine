package engine.devices.input;

import engine.Engine;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class PCInput extends Input {

    private boolean cursorVisible = true;
    private boolean mouseGrabbed = false;

    public PCInput() {
    }

    @Override
    public void initInput() {
        try {
            Keyboard.create();
            Mouse.create();
        } catch (LWJGLException ex) {
            Logger.getLogger(PCInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isMouseGrabbed() {
        return mouseGrabbed;
    }

    public void setMouseGrabbed(boolean mouseGrabbed) {
        this.mouseGrabbed = mouseGrabbed;
        Mouse.setGrabbed(mouseGrabbed);
    }

    public boolean isCursorVisible() {
        return cursorVisible;
    }

    public void setCursorVisible(boolean cursorVisible) {
        this.cursorVisible = cursorVisible;
        try {
            if (cursorVisible) {
                Mouse.setNativeCursor(null);
            } else {
                Mouse.setNativeCursor(new Cursor(1, 1, 0, 0, 1, BufferUtils.createIntBuffer(1), null));
            }
        } catch (LWJGLException ex) {
            Logger.getLogger(PCInput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(long deltaTime) {
        int mouseX = (int) (Mouse.getX() * (Engine.getInstance().getDevice().getSceneWidth() / Engine.getInstance().getDevice().getScreenWidth()));
        int mouseY = (int) (Engine.getInstance().getDevice().getSceneHeight() - Mouse.getY() * (Engine.getInstance().getDevice().getSceneHeight() / Engine.getInstance().getDevice().getScreenHeight()));
        inputIntData.put("mousex", mouseX);
        inputIntData.put("mousey", mouseY);

        inputIntData.put("mousedx", Mouse.getDX());
        inputIntData.put("mousedy", Mouse.getDY());

        Mouse.poll();

        for (int i = 0; i < Mouse.getButtonCount(); i++) {
            inputBoolData.put(Mouse.getButtonName(i).toLowerCase(), Mouse.isButtonDown(i));
        }

        Keyboard.poll();

        while (Keyboard.next()) {
            int keyEvent = Keyboard.getEventKey();
            String keyName = Keyboard.getKeyName(keyEvent).toLowerCase();
            inputBoolData.put(keyName, Keyboard.getEventKeyState());
        }
    }

    @Override
    public float getFloatData(String controlName) {
        return 0.0f;
    }

    @Override
    public void destroyInput() {
        Keyboard.destroy();
        Mouse.destroy();
    }

    @Override
    public String getType() {
        return "pc";
    }

    @Override
    public boolean isLeft() {
        return getBoolData("a") || getBoolData("left");
    }

    @Override
    public boolean isRight() {
        return getBoolData("d") || getBoolData("right");
    }

    @Override
    public boolean isUp() {
        return getBoolData("w") || getBoolData("up");
    }

    @Override
    public boolean isDown() {
        return getBoolData("s") || getBoolData("down");
    }
}
