package dk.sdu.kpm.utils;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * http://javatechniques.com/blog/faster-deep-copies-of-java-objects/
 */
public class DeepCopy<T> {

    /**
     * Returns a copy of the object, or null if the object cannot
     * be serialized.
     */
    public T copy(T orig) {
        T obj = null;
        try {
            // Write the object out to a byte array
            FastByteArrayOutputStream fbos =
                    new FastByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(fbos);
            out.writeObject(orig);
            out.flush();
            out.close();

            // Retrieve an input stream from the byte array and read
            // a copy of the object back in.
            ObjectInputStream in =
                    new ObjectInputStream(fbos.getInputStream());
            obj = (T) in.readObject();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}

