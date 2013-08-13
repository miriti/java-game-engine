package engine.devices;

import engine.core.types.Color;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public abstract class Device {
    abstract public void beforeRender();
    abstract public void afterRender();
    abstract public boolean isCloseRequested();
    abstract public void setSceneSize(float newSceneWidth, float newSceneHeight);
    abstract public void requestDisplayMode(int screenWidth, int screenHeight, boolean fullscreen);
    abstract public void initDevice();
    abstract public void destroyDivice();
    abstract public void setTitle(String appTitle);
    abstract public String getTitle();
    abstract public void setClearColor(Color color);
    abstract public int getScreenWidth();
    abstract public int getScreenHeight();
    abstract public float getSceneWidth();
    abstract public float getSceneHeight();
    abstract public void terminate();
}
