package Data;

import Config.ChatAppConfig;
import Models.Group;
import Models.Message;
import Models.User;

import java.io.*;
import java.util.*;

public class DataStorage {

    private static DataStorage DSInstance;

    private final ChatAppConfig chatAppConfig = ChatAppConfig.getConfigInstance();

    private final String userPath = chatAppConfig.getStringProperty("userPath");

    private final String groupPath = chatAppConfig.getStringProperty("groupPath");

    private final String messPath = chatAppConfig.getStringProperty("messPath");


    protected DataStorage() {
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


    public void saveListMessAsByte(ArrayList<Message> messageArrayList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(userPath);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(messageArrayList);
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

    public ArrayList<Message> readListMessAsByte() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<Message> list = new ArrayList<>();
        try {
            fis = new FileInputStream(convPath);
            ois = new ObjectInputStream(fis);
            list = (ArrayList<Message>) ois.readObject();
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
