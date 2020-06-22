import logger.Logger;
import logger.LoggerFactory;
import logger.Tag;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.system.Callback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private static final Tag tag = new Tag("Window");

    private long window;
    private int width, height;

    /**
     * Actual displayed window. Creates a GL context (and Capabilities).
     * Changes or operations directly performed on the window are implemented in this class. Any operations related to
     * drawing an image or displaying information are located in the Rendering Machinery.
     * @param width
     * @param height
     */
    public Window(int width, int height) {
        Logger logger = LoggerFactory.getConsoleLogger();
        logger.info(tag, "Creating window...");
        this.width = width;
        this.height = height;
        glfwSetErrorCallback((code, description) -> {
            logger.error(tag, "Error with code " + code + ": " + description);
        });
        if(!glfwInit()) {
            logger.error(tag,"GLFW could not be initialized");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(width, height, "gnengine", NULL, NULL);

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);
        glfwShowWindow(window);
        glfwRequestWindowAttention(window);
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        glfwSetCursorPos(window, 400, 300);

        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);

        GL.createCapabilities();

        Callback debugProc = GLUtil.setupDebugMessageCallback();

        logger.info(tag, "Window created.");
    }

    /**
     * Returns if any "close" calls have been sent to the window.
     * @return should close
     */
    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    /**
     * Swaps buffers. Should be called once every frame.
     */
    public void swapBuffers() {
        glfwSwapBuffers(window);
    }

    /**
     * Clears the Color Buffer as well as the Depth Buffer.
     */
    public void clearBuffers() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    /**
     * Polls GLFW events
     */
    public void pollEvents() {
        glfwPollEvents();
    }

    /**
     * Background color of the window. This is the only visual change handled by the window itself.
     * @param red red value
     * @param green green value
     * @param blue blue value
     * @param alpha alpha value - should be 1
     */
    public void setBackground(float red, float green, float blue, float alpha) {
        glClearColor(red, green, blue, alpha);
    }

    /**
     * Returns the window handle used by GLFW.
     * @return GLFW window handle
     */
    public long getWindowHandle() {
        return window;
    }

    /**
     * Returns the width of the Window.
     * @return width of window
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the window
     * @return height of window
     */
    public int getHeight() {
        return height;
    }

}
