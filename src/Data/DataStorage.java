package Data;

import Models.Group;
import Models.Message;
import Models.User;

import java.io.*;
import java.util.*;

public class DataStorage {
    private final String userPath = "UserData";
    private final String groupPath = "GroupData";
    private final String messPath = "MessageData";

    private final String fileNameChar = "Chat_Char.backup";
    private final String fileNameByte = "Chat_Byte.backup";

    public DataStorage() {
        this.createUserFolder();
        this.createGroupFolder();
        this.createMessFolder();
    }

    public void createUserFolder() {
        File dataFolder = new File(userPath);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    public void saveListUserAsByte(ArrayList<User> userArrayList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(userPath + "\\" + fileNameByte);
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
            fis = new FileInputStream(userPath + "\\" + fileNameByte);
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

    public void createGroupFolder() {
        File dataFolder = new File(groupPath);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    public void saveListGroupAsByte(ArrayList<Group> groupArrayList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(groupPath + "\\" + fileNameByte);
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
            fis = new FileInputStream(groupPath + "\\" + fileNameByte);
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

    public void createMessFolder() {
        File dataFolder = new File(groupPath);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    public void saveListMessAsByte(ArrayList<Message> messageArrayList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(userPath + "\\" + fileNameByte);
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
            fis = new FileInputStream(messPath + "\\" + fileNameByte);
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
