package opengltut;

import opengltut.util.FileUtil;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class Main {

    public static void main(String[] args) {
        // Sets up window
        Window window = new Window(800, 600);
        window.setBackground(0.2f, 0.4f, 0.2f, 1.0f);

        // Vertex data
        float[] vertices = {
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.0f,  0.5f, 0.0f
        };

        // Load, compile and link shaders to shader program
        String vertexShaderText = FileUtil.readFile("res/vertexShader.glsl");
        String fragmentShaderText = FileUtil.readFile("res/fragmentShader.glsl");
        
        // Create Vertex Shader on the GPU
        int vertexShaderHandle = GL30.glCreateShader(GL30.GL_VERTEX_SHADER);
        // Upload the source code to the GPU
        GL30.glShaderSource(vertexShaderHandle, vertexShaderText);
        // Tell the GPU to compile the vertex shader
        GL30.glCompileShader(vertexShaderHandle);

        int fragmentShaderHandle = GL30.glCreateShader(GL30.GL_FRAGMENT_SHADER);
        GL30.glShaderSource(fragmentShaderHandle, fragmentShaderText);
        GL30.glCompileShader(fragmentShaderHandle);

        // Create a shader program on the gpu
        int shaderProgramHandle = GL30.glCreateProgram();
        // Attach both shaders to it
        GL30.glAttachShader(shaderProgramHandle, vertexShaderHandle);
        GL30.glAttachShader(shaderProgramHandle, fragmentShaderHandle);
        // link the shaders so a usable program is created
        GL30.glLinkProgram(shaderProgramHandle);

        // Tell the GPU to use this program
        // As OpenGL is state based, you do not need to set this again as long as you don't change it
        glUseProgram(shaderProgramHandle);

        // Load vertex data to graphics card
        // Create an array holding information on the whereabouts of the data
        int vaoHandle = GL30.glGenVertexArrays();
        // Create the actual data buffer
        int vboHandle = GL15.glGenBuffers();
        // Binding the vao so the GPU knows which one to use
        glBindVertexArray(vaoHandle);
        // Bind the vbo
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboHandle);
        // Upload the vertex data create earlier
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
        // setup data
        // 0 means that this is the first set of elements
        // 3 means that there is three values (x, y, z) for it
        // GL_FLOAT is the data format
        // the data is not normalized
        // you need to advance 3 (x, y, z) times 4 (size of a float) bytes to get to the next position set
        // this is the pointer to the first set of data
        GL30.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 3 * 4, 0);
        GL30.glEnableVertexAttribArray(0);

        // this is called the bare-bones of a game loop
        // all the game logic as well as rendering takes place here
        // it runs as long as there is no close-signal sent
        while (!window.shouldClose()) {
            // clears all buffers used by our window creation
            window.clearBuffers();
            glUseProgram(shaderProgramHandle);
            glBindVertexArray(vaoHandle);
            // actually draws our triangle
            glDrawArrays(GL_TRIANGLES, 0, 3);
            // more window specific stuff which is neccessary but not important to this tutorial
            window.swapBuffers();
            window.pollEvents();
        }
    }
}
