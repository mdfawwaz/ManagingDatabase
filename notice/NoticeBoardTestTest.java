package notice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NoticeBoardTestTest {
    private NoticeBoard noticeBoard;

    @BeforeEach
    public void setUp() {
        noticeBoard = new NoticeBoard();
    }

    @Test
    public void testAddNotice() {
        Notice notice1 = new Notice("Notice 7", "Content", "Name", "654-366-6334");
        noticeBoard.addNotice(notice1);

        assertEquals(notice1, noticeBoard.getNotices().peek());
    }

    @Test
    public void testAddMultipleNotices() {
        Notice notice1 = new Notice("Notice 1", "WELCOME", "ABC", "123-456-7890");
        Notice notice2 = new Notice("Notice 2", "GOOD DAY", "XYZ", "987-654-3210");

        noticeBoard.addNotice(notice1);
        noticeBoard.addNotice(notice2);

        assertEquals(notice1, noticeBoard.getNotices().element());
        assertEquals(notice2, noticeBoard.getNotices().element());
    }

    @Test
    public void testMaxNoticesReached() {
        for (int i = 1; i <= 7; i++) {
            Notice notice = new Notice("Notice " + i, "Content " + i, "Contact Name", "Contact Phone");
            noticeBoard.addNotice(notice);
        }

        assertEquals(6, noticeBoard.getNotices().size());
        assertEquals("Notice 2", noticeBoard.getNotices().peek().getTitle());
    }

}
