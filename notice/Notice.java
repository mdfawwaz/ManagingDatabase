package notice;
public class Notice {
    private String title;
    private String content;
    private String contactName;
    private String contactPhone;

    public Notice(String title, String content, String contactName, String contactPhone) {
        this.title = title;
        this.content = content;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    @Override
    public String toString() {
        return "[" + title + "]\n" + content + "\nContact: " + contactName + "\nContact: " + contactPhone;
    }
}
