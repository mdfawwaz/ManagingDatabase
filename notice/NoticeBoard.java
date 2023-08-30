package notice;
import java.util.LinkedList;
import java.util.Queue;

public class NoticeBoard {
    private static final int MAX_NOTICES = 6;
    private Queue<Notice> notices = new LinkedList<>();

    public void addNotice(Notice notice) {
        if (notices.size() >= MAX_NOTICES) {
            notices.poll(); // Remove the least recently posted notice
        }
        notices.offer(notice);
    }

    public void displayNotices() {
        for (Notice notice : notices) {
            System.out.println(notice);
            System.out.println();
        }
    }
}
