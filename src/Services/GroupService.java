package Services;

import Data.DataStorage;
import Models.Group;
import Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public void createGroup(String name, boolean isPrivate, User creator) {
        String id = UUID.randomUUID().toString();
        Group g = new Group(id, name, isPrivate);
        List<User> admins = new ArrayList<>();
        admins.add(creator);
        g.setAdmin(admins);
    }

    public boolean joinGroupByCode(String inviteCode, User invitedUser) {
        Group group = findGroupById(inviteCode);
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
}
