package engine.devices.input;

import java.util.HashMap;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
abstract public class Input {

    abstract public String getType();
    protected HashMap<String, Float> inputFloatData = new HashMap<>();
    protected HashMap<String, Integer> inputIntData = new HashMap<>();
    protected HashMap<String, Boolean> inputBoolData = new HashMap<>();

    abstract public boolean isLeft();

    abstract public boolean isRight();

    abstract public boolean isUp();

    abstract public boolean isDown();

    abstract public void initInput();

    abstract public void destroyInput();

    abstract public void update(long deltaTime);

    public boolean getBoolData(String controlName) {
        return inputBoolData.get(controlName) == null ? false : inputBoolData.get(controlName);
    }

    public int getIntData(String controlName) {
        return inputIntData.get(controlName) == null ? 0 : inputIntData.get(controlName);
    }

    public float getFloatData(String controlName) {
        return inputFloatData.get(controlName) == null ? 0.0f : inputFloatData.get(controlName);
    }
}
