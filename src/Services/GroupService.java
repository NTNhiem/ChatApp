package Services;

import Config.ChatAppConfig;
import Data.DataStorage;
import Models.Conversation;
import Models.Group;
import Models.User;

import java.security.SecureRandom;
import java.util.*;


public class GroupService {
    private List<Group> groupList;
    private DataStorage dataStorage;
    private final ChatAppConfig chatAppConfig = ChatAppConfig.getConfigInstance();

    public GroupService() {
        this.dataStorage = DataStorage.getInstance();
        this.groupList = this.dataStorage.readGroupList();
    }

    public Group findGroupById(String id) {
        for (Group group : groupList) {
            if (group.getId().equals(id)) {
                return group;
            }
        }
        return null;
    }

    public List<Group> findGroupByName(String keyword) {
        keyword = keyword.trim().toLowerCase();
        List<Group> filteredGroup = new ArrayList<>();
        for (Group group : groupList) {
            String name = group.getName().toLowerCase();
            if (name.contains(keyword) || name.startsWith(keyword)) {
                filteredGroup.add(group);
            }
        }
        return filteredGroup;
    }

    public Group findGroupByInviteCode(String code) {
        for (Group group : groupList) {
            if (group.getInviteCode().equals(code)) {
                return group;
            }
        }
        return null;
    }

    public void createGroup(String name, boolean isPrivate, User creator) {
        String id = generateGroupID();
        Group group = new Group(id, name, isPrivate);
        List<User> admins = new ArrayList<>();
        admins.add(creator);
        creator.addGroup(group);
        group.setAdmin(admins);
        this.groupList.add(group);
    }

    public boolean joinGroupByCode(String inviteCode, User invitedUser) {
        Group group = findGroupByInviteCode(inviteCode);
            if (!group.isPrivate()) {
                return joinGroupByInvitation(group, invitedUser);
            }
        return false;
    }

    public boolean joinGroupByInvitation(Group group, User invitedUser) {
        List<User> members = group.getMembers();
        if (!members.contains(invitedUser)) {
            members.add(invitedUser);
            invitedUser.addGroup(group);
            group.setMembers(members);
            return true;
        }
        return false;
    }

    public String generateGroupID() {
        String id;
        do {
            id = UUID.randomUUID().toString();
        } while (findGroupById(id) != null);
        return id;
    }

    public List<Group> getUserGroupList(User user) {
        return user.getGroups();
    }

    public boolean removeUserFromGroup(Group group, User user) {
        boolean status = false;
        String userId = user.getId();
        List<User> memberList = group.getMembers();
        for (User member : memberList) {
            if (member.getId().equals(userId)) {
                memberList.remove(member);
                group.setMembers(memberList);
                status = true;
                break;
            }
        }

        List<User> adminList = group.getAdmin();
        for (User admin : adminList) {
            if (admin.getId().equals(userId)) {
                adminList.remove(admin);
                adminList.add(memberList.get(0));
                group.setAdmin(adminList);
                status = true;
            }
        }
        return status;
    }

    public void saveGroupData() {
        this.dataStorage.saveGroupList(this.groupList);
    }

    public List<Group> getAllGroup(User currUser) {
        return currUser.getGroups();
    }

    public List<User> getAllGroupMember(Group group) {
        return group.getMembers();
    }

    public User findUserByID(String id, Group group) {
        for (User user : group.getMembers()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        for (User user : group.getAdmin()) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }
}
