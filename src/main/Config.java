package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author goepfert
 */
public class Config {

    private static Config instance = null;
    //
    private Properties confPropsDefaults;
    private Properties confProps;
    //
    HashMap<String, Subscriber> subscribers;

    private Config() {
        confPropsDefaults = new Properties();
        confProps = new Properties(confPropsDefaults);
        subscribers = new HashMap<String, Subscriber>();
        setDefaults();
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private void setDefaults() {
        confPropsDefaults.setProperty("nX", "" + 200);
        confPropsDefaults.setProperty("nY", "" + 40);
        confPropsDefaults.setProperty("nPixel", "" + 0);
        confPropsDefaults.setProperty("colorDepth", "" + 8);
        confPropsDefaults.setProperty("sphereSize", "" + 1);
        confPropsDefaults.setProperty("lightSourceBrightness", "" + 0.5f);
        confPropsDefaults.setProperty("ledBrightness", "" + 2.5f);
        confPropsDefaults.setProperty("rotSpeed", "" + 10000);
        confPropsDefaults.setProperty("freeMove", "" + 0);        
    }

    public void loadProperties(String filename) {
        //System.out.println(System.getProperty("user.dir"));
        try {
            FileInputStream propInFile = new FileInputStream(filename);
            confProps.load(propInFile);
            confProps.list(System.out);
        } catch (FileNotFoundException e) {
            System.err.println("Can’t find " + filename);
        } catch (IOException e) {
            System.err.println("I/O failed.");
        }
    }

    public Properties getConfProps() {
        return confProps;
    }

    public void subscribe(String name, Subscriber obj) {
        subscribers.put(name, obj);
    }

    public void unsubscribe(String name) {
        Object getObj = subscribers.get(name);
        if (getObj != null) {
            subscribers.remove(name);
        }
    }

    public void notifySubscribers() {
        for (String name : subscribers.keySet()) {
            notifySubscriber(name);
        }
    }

    public void notifySubscriber(String name) {
        Object getObj = subscribers.get(name);
        if (getObj != null) {
            ((Subscriber) getObj).readSubscription();
        }
    }
}