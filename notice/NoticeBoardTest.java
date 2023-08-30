package notice;

public class NoticeBoardTest {
    public static void main(String[] args) {
        NoticeBoard noticeBoard = new NoticeBoard();

        noticeBoard.addNotice(new Notice("Notice 1", "Hello", "MDF", "123-456-7890"));
        noticeBoard.addNotice(new Notice("Notice 2", "Dear", "MHDF", "123-456-7890"));
        noticeBoard.addNotice(new Notice("Notice 3", "Welcome to Prodapt", "FAWWAZ", "123-456-7890"));
        noticeBoard.addNotice(new Notice("Notice 4", "Have a nice Day", "FAWAZ", "123-456-7890"));
        noticeBoard.addNotice(new Notice("Notice 5", "Welcome to Java", "MHMDFAWWAZ", "123-456-7890"));
        noticeBoard.addNotice(new Notice("Notice 6", "NoticeBoard", "RAOOF", "123-456-7890"));

        noticeBoard.displayNotices();
    }
}

