package app.classloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader {

    private final String PATH_TO_BIN = "src/main/java/app/samples/";

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            byte[] b = Files.readAllBytes(Paths.get(PATH_TO_BIN + className + ".class"));
            return defineClass(null, b, 0, b.length);
        } catch (IOException ex) {
            return super.findClass(className);
        }
    }
}
