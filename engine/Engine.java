package engine;

import engine.core.types.Color;
import engine.devices.Device;
import engine.display.DisplayObject;
import java.util.Date;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class Engine extends DisplayObject {

    private static Engine instance = null;
    private DisplayObject currentState = null;
    private Device device;
    private static long currentTime = 0;

    public Engine(Device onDevice, int windowWidth, int windowHeight, boolean fullscreen) {
        super();
        System.out.println("Initialization...");
        device = onDevice;
        instance = this;
        width = windowWidth;
        height = windowHeight;

        device.initDevice();
        device.requestDisplayMode(windowWidth, windowHeight, fullscreen);
        device.init();
        setSceneSize(windowWidth, windowHeight);
        device.setClearColor(Color.CORNFLOWER_BLUE);
    }

    public void setSceneSize(float sceneWidth, float sceneHeight) {
        device.setSceneSize(sceneWidth, sceneHeight);
    }

    public void setTitle(String title) {
        device.setTitle(title);
    }

    public static long getCurrentTime() {
        return currentTime;
    }

    public Device getDevice() {
        return device;
    }

    public void start() {
        long lastTime = new Date().getTime();

        while (!device.isCloseRequested()) {
            currentTime = new Date().getTime();
            long deltaTime = currentTime - lastTime;
            lastTime = currentTime;

            if (deltaTime == 0) {
                continue;
            }

            if (device.getInput() != null) {
                device.getInput().update(deltaTime);
            }

            super.update(deltaTime);

            device.beforeRender();
            
            super.render();

            device.afterRender();
        }

        device.destroyDivice();
    }

    public void quit() {
        device.terminate();
    }

    public DisplayObject getCurrentState() {
        return currentState;
    }

    public void setCurrentState(DisplayObject currentState) {
        if (this.currentState != null) {
            removeChild(this.currentState);
        }
        this.currentState = currentState;
        addChild(currentState);
    }

    public void setClearColor(Color color) {
        device.setClearColor(color);
    }

    public static Engine getInstance() {
        return instance;
    }
}
