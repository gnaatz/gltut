public class Main {

    private static Window window;

    public static void main(String args[]) {
         window = new Window(800, 600);

        while(!window.shouldClose()) {
            doSomething();
            render();
        }
    }

    private static void doSomething() {
        window.pollEvents();
    }

    private static void render() {

    }
}
