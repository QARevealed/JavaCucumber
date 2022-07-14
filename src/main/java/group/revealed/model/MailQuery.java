package group.revealed.model;

public class MailQuery {
    private String label;
    private String subject;
    private String sender;
    private String receiver;
    private String c1OrderCode;

    public MailQuery() {
    }

    public String getLabel() {
        return this.label;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getSender() {
        return this.sender;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public String getC1OrderCode() {
        return this.c1OrderCode;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setC1OrderCode(String c1OrderCode) {
        this.c1OrderCode = c1OrderCode;
    }
}
