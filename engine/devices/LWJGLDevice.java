package engine.devices;

import engine.core.types.Color;
import engine.devices.input.Input;
import engine.devices.input.PCInput;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Michael Miriti <michael@miriti.ru>
 */
public class LWJGLDevice extends Device {
    
    protected String windowTitle = "LWJGL Game";
    private float sceneHeight;
    private float sceneWidth;
    private int currentScreenWidth;
    private int currentScreenHeight;
    private boolean terminateFlag = false;
    
    @Override
    public void setSceneSize(float newSceneWidth, float newSceneHeight) {
        sceneWidth = newSceneWidth;
        sceneHeight = newSceneHeight;
    }
    
    @Override
    public void initDevice() {
    }
    
    @Override
    public void init() {
        initOpenGL();
        input = new PCInput();
        input.initInput();
    }
    
    private void initOpenGL() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        
        glDisable(GL_DEPTH_TEST);
    }
    
    @Override
    public void setTitle(String appTitle) {
        windowTitle = appTitle;
        Display.setTitle(appTitle);
    }
    
    @Override
    public String getTitle() {
        return windowTitle;
    }
    
    @Override
    public void beforeRender() {
        glClear(GL_COLOR_BUFFER_BIT);
        
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, sceneWidth, sceneHeight, 0, -1000, 1000);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }
    
    @Override
    public void afterRender() {
        Display.update();
        Display.sync(60);
    }
    
    @Override
    public boolean isCloseRequested() {
        return Display.isCloseRequested() || terminateFlag;
    }
    
    @Override
    public void destroyDivice() {
        AL.destroy();
        Display.destroy();
        Mouse.destroy();
    }
    
    @Override
    public void requestDisplayMode(int screenWidth, int screenHeight, boolean fullscreen) {
        boolean fullscreenSuccess = false;
        try {
            if (fullscreen) {
                DisplayMode[] modes = Display.getAvailableDisplayModes();
                
                for (int i = 0; i < modes.length; i++) {
                    if ((modes[i].getWidth() == screenWidth) && (modes[i].getHeight() == screenHeight) && (modes[i].getBitsPerPixel() == 32)) {
                        Display.setDisplayModeAndFullscreen(modes[i]);
                        fullscreenSuccess = true;
                        break;
                    }
                }
            }
            
            if ((!fullscreen) || (!fullscreenSuccess)) {
                Display.setDisplayMode(new DisplayMode(screenWidth, screenHeight));
            } else {
                if (!fullscreenSuccess) {
                    System.err.println("Fullscreen failed");
                }
            }
            
            Display.create();
            
            currentScreenWidth = screenWidth;
            currentScreenHeight = screenHeight;
        } catch (LWJGLException ex) {
            Logger.getLogger(LWJGLDevice.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void setClearColor(Color color) {
        glClearColor(color.r, color.g, color.b, color.a);
    }
    
    @Override
    public int getScreenWidth() {
        return currentScreenWidth;
    }
    
    @Override
    public int getScreenHeight() {
        return currentScreenHeight;
    }
    
    @Override
    public void terminate() {
        terminateFlag = true;
    }
    
    @Override
    public float getSceneWidth() {
        return sceneWidth;
    }
    
    @Override
    public float getSceneHeight() {
        return sceneHeight;
    }
    
    @Override
    public Input getInput() {
        return input;
    }
}
