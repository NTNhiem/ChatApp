package Services;

import Config.ChatAppConfig;
import Data.DataStorage;
import Models.*;

import java.util.*;

public class RelationshipService {
    private List<Conversation> conversationList;
    private DataStorage dataStorage;
    private final ChatAppConfig chatAppConfig = ChatAppConfig.getConfigInstance();

    public RelationshipService() {
    }

    public boolean sendInvitation(User invitingUser, User invitedUser) {
        Map<String, User> friendMap = invitingUser.getFriendMap();
        if (!friendMap.containsKey(invitedUser.getUsername())) {
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

    public void changeInvitationStatus(boolean approved, User invitingUser, User invitedUser) {
        Map<String, Relationship> invitingUserRelationship = invitingUser.getRelationshipMap();
        String invitingUserId = invitingUser.getId();
        String invitedUserId = invitedUser.getId();
        String relationshipId = invitingUserId + invitedUserId;
        Relationship relationship = invitingUserRelationship.get(relationshipId);
        if (approved) {
            invitingUser.addNewFriend(invitedUser);
            invitedUser.addNewFriend(invitedUser);
            relationship.setStatus(RelationshipStatus.friended);
        } else {
            relationship.setStatus(RelationshipStatus.declined);
        }
        invitingUser.setRelationship(relationship);
        invitedUser.setRelationship(relationship);
    }

    public List<User> getFriendList(User user) {
        return user.getFriendList();
    }

    public List<User> searchFriendList(User user, String keyword) {
        keyword = keyword.trim().toLowerCase();
        List<User> filteredFriendList = new ArrayList<>();
        Map<String, User> friendMap = user.getFriendMap();
        if (!friendMap.isEmpty()) {
            for (Map.Entry<String, User> entry : friendMap.entrySet()) {
                String name = entry.getKey().toLowerCase();
                if (name.contains(keyword) || name.startsWith(keyword)) {
                    filteredFriendList.add(entry.getValue());
                }
            }
        }
        return filteredFriendList;
    }

    public List<User> findNewFriend(User user) {
        List<User> newFriendList = new ArrayList<>();
        List<User> currentFriendList = user.getFriendList();
        Map<String, User> currentFriendMap = user.getFriendMap();

        for (User friend : currentFriendList) {
            List<User> friendsOfFriend = friend.getFriendList();
            for (User newFriend : friendsOfFriend) {
                String newFriendUsername = newFriend.getUsername();
                if (!currentFriendMap.containsKey(newFriendUsername)) {
                    newFriendList.add(newFriend);
                }
            }
        }

        List<Group> currentGroupList = user.getGroups();
        for (Group group : currentGroupList) {
            for (User member : group.getMembers()) {
                if (!currentFriendMap.containsKey(member.getUsername())) {
                    newFriendList.add(member);
                }
            }
        }
        return newFriendList;
    }
}
