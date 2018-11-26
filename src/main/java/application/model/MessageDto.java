package application.model;

public class MessageDto {
    private String userName;
    private Long userId;
    private String text;

    public MessageDto(Long userId, String text, String userName) {
        this.userName = "@" + userName;
        this.userId = userId;
        this.text = text;
    }

    public String getUserName() { return userName; }

    public String getText() {
        return text;
    }

    public Long getUserId() {
        return userId;
    }
}