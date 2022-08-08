package Models;

public class Relationship {
    private String id;
    private String invitedUserId;
    private String invitingUserId;
    private RelationshipStatus status;

    public Relationship(String relationshipId, String invitedUserId, String invitingUserId, RelationshipStatus status) {
        this.id = relationshipId;
        this.invitedUserId = invitedUserId;
        this.invitingUserId = invitingUserId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setRelationshipId(String relationshipId) {
        this.relationshipId = relationshipId;
    }

    public String getInvitedUserId() {
        return invitedUserId;
    }

    public void setInvitedUserId(String invitedUserId) {
        this.invitedUserId = invitedUserId;
    }

    public String getInvitingUserId() {
        return invitingUserId;
    }

    public void setInvitingUserId(String invitingUserId) {
        this.invitingUserId = invitingUserId;
    }

    public RelationshipStatus getStatus() {
        return status;
    }

    public void setStatus(RelationshipStatus status) {
        this.status = status;
    }
}
