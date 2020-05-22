package fr.mds.goodfellow;

public class Message {
    private String text;
    private MemberData memberData;
    private boolean belongsToCurrentUser;

    public Message(String text, MemberData data, boolean belongsToCurrentUser) {
        this.text = text;
        this.memberData = data;
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    String getText() {
        return text;
    }

    MemberData getMemberData() {
        return memberData;
    }

    boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }
}
