package Services;

import Data.DataStorage;
import Models.Relationship;
import Models.RelationshipStatus;
import Models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RelationshipService {

    public RelationshipService() {
    }

    

    public boolean inviteFriend(User invitingUser, User invitedUser) {
        Map<String, User> friendMap = invitingUser.getFriends();
        if (friendMap.get(invitedUser.getUsername()) == null) {
            String invitingUserId = invitingUser.getId();
            String invitedUserId = invitedUser.getId();
            RelationshipStatus status = RelationshipStatus.pending;
            String relationshipId = invitingUserId + invitedUserId;
            Relationship relationship = new Relationship(relationshipId,invitingUserId,invitedUserId,status);
            invitingUser.addNewRelationship(relationship);
            invitedUser.addNewRelationship(relationship);
            return true;
        }
        return false;
    }

    public void approveFriendInvitation(User invitingUser, User invitedUser) {

    }

    public List<User> getFriendList(User user) {
        List<User> friendList = new ArrayList<>();
        Map<String, User> friendMap = user.getFriends();
        if (!friendMap.isEmpty()) {
            friendList = (List<User>) friendMap.values();
        }
        return friendList;
    }

    public List<User> searchFriendList(User user, String keyword) {
        List<User> filteredFriendList = new ArrayList<>();
        Map<String, User> friendMap = user.getFriends();
        if (!user.getFriends().isEmpty()) {
            for (Map.Entry<String, User> entry : friendMap.entrySet()) {
                String name = entry.getKey();
                if (name.contains(keyword) || name.startsWith(keyword)) {
                    filteredFriendList.add(entry.getValue());
                }
            }
        }
        return filteredFriendList;
    }
}
