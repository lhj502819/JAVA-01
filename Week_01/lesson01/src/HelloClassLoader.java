import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @since 2021/1/9
 */
public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) throws Exception {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class<?> hello = helloClassLoader.findClass("Hello");
        Object o = hello.newInstance();
        Method hello1 = hello.getDeclaredMethod("hello");
        hello1.invoke(o);
    }

    @Override
    protected Class<?> findClass(String name) {
        try {
            String path = HelloClassLoader.class.getResource("/") + "Hello.xlass";
            File file = new File(path.replace("file:/" , ""));
            FileInputStream input = new FileInputStream(file);
            byte[] buffer = new byte[(int)file.length()];
            byte[] newBuffer = new byte[(int)file.length()];
            for (int i = 0 ; i<buffer.length ; i++) {
                byte oldValue = buffer[i];
                buffer[i] = (byte) (255 - oldValue);
                newBuffer[i] =  (byte) (255 - oldValue);
            }
            return defineClass(name , newBuffer , 0 ,newBuffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
