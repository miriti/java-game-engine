package engine.devices.input;

import java.util.HashMap;
import net.java.games.input.Component.Identifier;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
abstract public class Input {

    protected HashMap<String, Float> inputData = new HashMap<>();

    abstract public void initInput();

    abstract public void update(long deltaTime);

    public float getData(String controlName) {
        return inputData.get(controlName);
    }
}
