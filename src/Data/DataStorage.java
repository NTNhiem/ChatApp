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

    private final String dataPath = chatAppConfig.getStringProperty("dataPath");

    private final String userPath = chatAppConfig.getStringProperty("userPath");

    private final String groupPath = chatAppConfig.getStringProperty("groupPath");

    private final String convPath = chatAppConfig.getStringProperty("convPath");

    private final String attachmentPath = chatAppConfig.getStringProperty("attachmentPath");


    protected DataStorage() {
        this.createDataFolder();
        this.createAttachmentFolder();
    }

    public void createDataFolder() {
        File dataFolder = new File(dataPath);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    public void createAttachmentFolder() {
        File dataFolder = new File(attachmentPath);
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

    public void saveUserList(List<User> userArrayList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(dataPath + "\\" + userPath);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(userArrayList);
            oos.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fos != null && oos != null) {
                    fos.close();
                    oos.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public ArrayList<User> readUserList() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<User> list = new ArrayList<>();
        try {
            if (checkPathExists(dataPath + "\\" + userPath)) {
                fis = new FileInputStream(dataPath + "\\" + userPath);
                ois = new ObjectInputStream(fis);
                list = (ArrayList<User>) ois.readObject();
            } else {
                return list;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fis != null && ois != null) {
                    fis.close();
                    ois.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list;
    }

    public void saveGroupList(List<Group> groupArrayList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(dataPath + "\\" + groupPath);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(groupArrayList);
            oos.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fos != null && oos != null) {
                    fos.close();
                    oos.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public List<Group> readGroupList() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<Group> list = new ArrayList<>();
        try {
            if (checkPathExists(dataPath + "\\" + groupPath)) {
                fis = new FileInputStream(dataPath + "\\" + groupPath);
                ois = new ObjectInputStream(fis);
                list = (ArrayList<Group>) ois.readObject();
            } else {
                return list;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fis != null && ois != null) {
                    fis.close();
                    ois.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list;
    }


    public void saveConversationList(List<Conversation> conversationList) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(dataPath + "\\" + convPath);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(conversationList);
            oos.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fos != null && oos != null) {
                    fos.close();
                    oos.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public List<Conversation> readConversationList() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<Conversation> list = new ArrayList<>();
        try {
            if (checkPathExists(dataPath + "\\" + userPath)) {
                fis = new FileInputStream(dataPath + "\\" + convPath);
                ois = new ObjectInputStream(fis);
                list = (ArrayList<Conversation>) ois.readObject();
            } else {
                return list;
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (fis != null && ois != null) {
                    fis.close();
                    ois.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return list;
    }

    public void saveAttachment(String directory, int id) {
        File src = new File(directory);
        File dest = new File(attachmentPath + "\\" + id);
        copyFileUsingStream(src, dest);
    }

    private void copyFileUsingStream(File source, File dest) {
//        try (InputStream is = new FileInputStream(source); OutputStream os = new FileOutputStream(dest)) {
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = is.read(buffer)) > 0) {
//                os.write(buffer, 0, length);
//            }
//        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        int i = 0;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(dest);
            while ((i = fis.read()) != -1) {
                fos.write(i);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean checkPathExists(String path) {
        File file = new File(path);
        return file.exists();
    }
}
