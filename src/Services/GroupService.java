package Services;

import Data.DataStorage;
import Models.Group;
import Models.User;

import java.security.SecureRandom;
import java.util.*;


public class GroupService {
    private List<Group> groupList;
    private DataStorage dataStorage;

    public GroupService() {
        this.dataStorage = DataStorage.getInstance();
        this.groupList = this.dataStorage.readListGroupAsByte();
    }

    public Group findGroupById(String id) {
        for (Group group : groupList) {
            if (group.getId().equals(id)) {
                return group;
            }
        }
        return null;
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
        Group g = new Group(id, name, isPrivate);
        List<User> admins = new ArrayList<>();
        admins.add(creator);
        g.setAdmin(admins);
    }

    public boolean joinGroupByCode(String inviteCode, User invitedUser) {
        Group group = findGroupByInviteCode(inviteCode);
        if (!group.isPrivate()) {
            List<User> members = group.getMembers();
            if (!members.contains(invitedUser)) {
                members.add(invitedUser);
                group.setMembers(members);
                return true;
            }
        }
        return false;
    }

    public boolean joinGroupByUser(Group group, User invitedUser) {
        List<User> members = group.getMembers();
        if (!members.contains(invitedUser)) {
            members.add(invitedUser);
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
}
