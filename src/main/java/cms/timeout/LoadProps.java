package cms.timeout;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by freelance on 23/02/2015.
 */
public class LoadProps {

    public static Properties getProperties() {
        return prop;
    }

    public static void setProp(Properties prop) {
        LoadProps.prop = prop;
    }

    public static void setProperty(String key, String value)
    {
        if(getProperty(key)!=null)
        {
            getProperties().setProperty(key,value);
        }
    }

    static Properties prop,merged;
    static String propertiesFileLocation = "src/test/Properties/";

    public static String getProperty(String key)
    {
        if(getProperties()==null)
        {
            loadPropertyFile();
        }

        return merged.getProperty(key);
    }
    private static void loadPropertyFile() {
        prop = new Properties();
        merged = new Properties();
        FileInputStream input = null;
        try {
            if (System.getProperty("test_phase") == null || System.getProperty("test_phase").equals(""))
            input = new FileInputStream(propertiesFileLocation + "TestData" + ".properties");
            prop.load(input);
            merged.putAll(prop);
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
