package engine.devices.input;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class GamePadInput extends Input {

    private Controller controller;

    @Override
    public void initInput() {
        if (available()) {
            for (Controller c : ControllerEnvironment.getDefaultEnvironment().getControllers()) {
                if (c.getType() == Controller.Type.GAMEPAD) {
                    System.out.println("Gamepad found: " + c.getName());
                    controller = c;
                    System.out.println("Components [" + c.getComponents().length + "]");
                    for (Component cm : c.getComponents()) {
                        //gamePadButtonsDown.put(cm.getIdentifier(), false);
                        System.out.println(cm.getIdentifier() + ": " + cm.getName());
                    }
                    System.out.println("Rumblers [" + c.getRumblers().length + "]");
                    break;
                }
            }
        } else {
            throw new Error("Gamepad unavailable");
        }
    }

    /**
     * Check for gamepad in system
     *
     * @return
     */
    public static boolean available() {
        for (Controller c : ControllerEnvironment.getDefaultEnvironment().getControllers()) {
            if (c.getType() == Controller.Type.GAMEPAD) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(long deltaTime) {
        if (controller.poll()) {
            for (Component c : controller.getComponents()) {
                String idName = c.getIdentifier().getName();
                float data = c.getPollData();
                inputData.put(idName, data);
            }
        }
    }
}
