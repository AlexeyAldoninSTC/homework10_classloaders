package app;

import app.classloader.MyClassLoader;
import app.service.SourceFileFiller;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException, InterruptedException {
        Thread fileFiller = new Thread(new SourceFileFiller());
        fileFiller.start();
        fileFiller.join();

        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        javac.run(null, System.out, System.err, "src/main/java/app/samples/SomeClass.java");

        MyClassLoader loader = new MyClassLoader(ClassLoader.getSystemClassLoader());

        Class<?> someClass = loader.loadClass("SomeClass");
        Object instance = someClass.getConstructors()[0].newInstance();
        someClass.getMethods()[0].invoke(instance);
    }
}
