package Data;

import Config.ChatAppConfig;
import Models.Conversation;
import Models.Group;
import Models.User;

import java.io.*;
import java.util.*;

public class DataStorage {

    private static DataStorage DSInstance;

    private final ChatAppConfig chatAppConfig = ChatAppConfig.getConfigInstance();

    private String dataPath = chatAppConfig.getStringProperty("datapath");

    private final String userPath = chatAppConfig.getStringProperty("userPath");

    private final String groupPath = chatAppConfig.getStringProperty("groupPath");

    private final String convPath = chatAppConfig.getStringProperty("convPath");

    protected DataStorage() {
        this.createDataFolder();

    }
    public void createDataFolder() {
        File dataFolder = new File(dataPath);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }


    public static DataStorage getInstance() {
        if (DSInstance == null) {
            DSInstance = new DataStorage();
        }
        return DSInstance;
    }

    public void saveListUserAsByte(ArrayList<User> userArrayList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(userPath);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(userArrayList);
            oos.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                fos.close();
                oos.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ArrayList<User> readListUserAsByte() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<User> list = new ArrayList<>();
        try {
            fis = new FileInputStream(userPath);
            ois = new ObjectInputStream(fis);
            list = (ArrayList<User>) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                fis.close();
                ois.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list;
    }

    public void saveListGroupAsByte(ArrayList<Group> groupArrayList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(groupPath);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(groupArrayList);
            oos.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                fos.close();
                oos.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ArrayList<Group> readListGroupAsByte() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<Group> list = new ArrayList<>();
        try {
            fis = new FileInputStream(groupPath);
            ois = new ObjectInputStream(fis);
            list = (ArrayList<Group>) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                fis.close();
                ois.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list;
    }


    public void saveListConvAsByte(ArrayList<Conversation> conversationArrayList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(convPath);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(conversationArrayList);
            oos.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                fos.close();
                oos.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ArrayList<Conversation> readListConvAsByte() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<Conversation> list = new ArrayList<>();
        try {
            fis = new FileInputStream(convPath);
            ois = new ObjectInputStream(fis);
            list = (ArrayList<Conversation>) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                fis.close();
                ois.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list;
    }
}
