package com.core.utils;

import com.core.reporting.Reporter;

import java.io.*;

public class SerializeHelper {

    private static String siteName = Constants.BASE_URL.substring(Constants.BASE_URL.indexOf("://") + "://".length(), Constants.BASE_URL.indexOf("."));
    private static String buildDir = System.getProperty("serializeDir") != null ? System.getProperty("serializeDir") : System.getProperty("build.dir");
    private static String serializationDir = buildDir + File.separator + "serialized_" + System.getProperty("browser", "firefox")
            + "-" + siteName + File.separator;

    public static <T> void serializeObject(T object, String filename) {
        File dir = new File(serializationDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            FileOutputStream fos = new FileOutputStream(serializationDir + filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            File objectInText = new File(serializationDir + filename + ".txt");
            BufferedWriter txtBufferedWriter = new BufferedWriter(new FileWriter(objectInText, false));
            try {
                oos.writeObject(object);
                txtBufferedWriter.write(object.toString());
            } finally {
                oos.close();
                fos.close();
                txtBufferedWriter.close();
            }
            Reporter.log("Serialized object to file: " + filename);
        } catch (IOException io) {
            Reporter.log("<b>Serialization error!</b>");
        }
    }

    public static <T> T deserializeObject(Class<T> klass, String filename) {
        T temp = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(serializationDir + filename);
            ois = new ObjectInputStream(fis);
            temp = klass.cast(ois.readObject());
        } catch (InvalidClassException ice) {
            Reporter.log("<b>Class \"" + klass.getName() + "\" <br></br> In file \"" + serializationDir + filename + "\" became incompactible!</b>\n");
            System.out.println("<b>Class \"" + klass.getName() + "\" became incompactible!</b>\n");
            ice.printStackTrace();
        } catch (IOException io) {
            Reporter.log("<b>File \"" + serializationDir + filename + "\" Not found! </b>");
            io.printStackTrace();
        } catch (ClassNotFoundException cnfE) {
            Reporter.log("<b>Class \"" + klass.getName() + "\" not found!</b>");
            cnfE.printStackTrace();
        } finally {
            try {
                if (fis != null)
                    fis.close();
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

    /**
     * Used for soft dependencies.
     * For example: if we don't have dependent object - we create it in test
     * and don't show exception in console.
     *
     * @param klass
     * @param filename
     * @param <T>
     * @return
     */
    public static <T> T deserializeObjectNoConsole(Class<T> klass, String filename) {
        T temp = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(serializationDir + filename);
            ois = new ObjectInputStream(fis);
            temp = klass.cast(ois.readObject());
        } catch (InvalidClassException ice) {
            Reporter.log("<b>Class \"" + klass.getName() + "\" <br></br> In file \"" + serializationDir + filename + "\" became incompactible!</b>\n");
        } catch (IOException io) {
            Reporter.log("<b>File \"" + serializationDir + filename + "\" Not found! </b>");
        } catch (ClassNotFoundException cnfE) {
            Reporter.log("<b>Class \"" + klass.getName() + "\" not found!</b>");
        } finally {
            try {
                if (fis != null)
                    fis.close();
                if (ois != null)
                    ois.close();
            } catch (IOException e) {
                Reporter.log("Got impossible exception! O_o (Input Stream close)");
            }
        }
        return temp;
    }
}
