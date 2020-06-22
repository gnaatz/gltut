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

        int vertexShaderHandle = GL30.glCreateShader(GL30.GL_VERTEX_SHADER);
        GL30.glShaderSource(vertexShaderHandle, vertexShaderText);
        GL30.glCompileShader(vertexShaderHandle);

        int fragmentShaderHandle = GL30.glCreateShader(GL30.GL_FRAGMENT_SHADER);
        GL30.glShaderSource(fragmentShaderHandle, fragmentShaderText);
        GL30.glCompileShader(fragmentShaderHandle);

        int shaderProgramHandle = GL30.glCreateProgram();
        GL30.glAttachShader(shaderProgramHandle, vertexShaderHandle);
        GL30.glAttachShader(shaderProgramHandle, fragmentShaderHandle);
        GL30.glLinkProgram(shaderProgramHandle);

        glUseProgram(shaderProgramHandle);

        // Load vertex data to graphics card
        int vaoHandle = GL30.glGenVertexArrays();
        int vboHandle = GL15.glGenBuffers();
        glBindVertexArray(vaoHandle);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboHandle);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);
        // setup data
        GL30.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 3 * 4, 0);
        GL30.glEnableVertexAttribArray(0);

        while (!window.shouldClose()) {
            window.clearBuffers();
            glUseProgram(shaderProgramHandle);
            glBindVertexArray(vaoHandle);
            glDrawArrays(GL_TRIANGLES, 0, 3);
            window.swapBuffers();
            window.pollEvents();
        }
    }
}
