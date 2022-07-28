package Models;

public class Relationship {
    private int relationshipId;
    private String invitedUserId;
    private String invitingUserId;
    private RelationshipStatus status;

    public Relationship(int relationshipId, String invitedUserId, String invitingUserId, RelationshipStatus status) {
        this.relationshipId = relationshipId;
        this.invitedUserId = invitedUserId;
        this.invitingUserId = invitingUserId;
        this.status = status;
    }

    public int getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(int relationshipId) {
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
