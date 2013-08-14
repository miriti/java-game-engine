package engine.devices;

import engine.core.types.Color;
import engine.devices.input.Input;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public abstract class Device {

    protected Input input;

    abstract public void beforeRender();

    abstract public void afterRender();

    abstract public boolean isCloseRequested();

    abstract public void setSceneSize(float newSceneWidth, float newSceneHeight);

    abstract public void requestDisplayMode(int screenWidth, int screenHeight, boolean fullscreen);

    abstract public void initDevice();

    abstract public void init();

    abstract public void destroyDivice();

    abstract public void setTitle(String appTitle);

    abstract public String getTitle();

    abstract public void setClearColor(Color color);

    abstract public Input getInput();

    public void setInput(Input input) {
        input.initInput();
        this.input = input;
    }

    public void update(long deltaTime) {
        if(input != null){
        }
    }

    abstract public int getScreenWidth();

    abstract public int getScreenHeight();

    abstract public float getSceneWidth();

    abstract public float getSceneHeight();

    abstract public void terminate();
}
