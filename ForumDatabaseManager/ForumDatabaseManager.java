package javaFxGame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import java.sql.*;

public class ForumDatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/instagram";
    private static final String DB_USER = "fawwazuddin";
    private static final String DB_PASSWORD = "fawwaz@123";

    public static void likePostWithID(int a, int b, int c) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO LIKES (LIKES_ID, USER_ID, POST_ID) VALUES (?, ?, ?)";
            try (PreparedStatement stmnt = connection.prepareStatement(sql)) {
                stmnt.setInt(1, a);
                stmnt.setInt(2, b);
                stmnt.setInt(3, c);
                stmnt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getLikesAndComments(int postId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sqlLikes = "SELECT LIKES.user_id FROM LIKES " +
                              "JOIN USERS ON LIKES.USER_ID = USERS.USER_ID " +
                              "WHERE post_id = ?";
            
            String sqlComments = "SELECT COMMENT_TEXT FROM COMMENTS " +
                                 "JOIN USERS ON COMMENTS.USER_ID = USERS.USER_ID " +
                                 "WHERE post_id = ?";
            
            try (PreparedStatement statementLikes = connection.prepareStatement(sqlLikes);
                 PreparedStatement statementComments = connection.prepareStatement(sqlComments)) {
                statementLikes.setInt(1, postId);
                statementComments.setInt(1, postId);
                
                try (ResultSet resultSetLikes = statementLikes.executeQuery();
                     ResultSet resultSetComments = statementComments.executeQuery()) {
                    System.out.println("Likes for Post " + postId + ":");
                    while (resultSetLikes.next()) {
                        String username = resultSetLikes.getString("username");
                        System.out.println(username + " liked this post.");
                    }
                    
                    System.out.println("\nComments for Post " + postId + ":");
                    while (resultSetComments.next()) {
                        String username = resultSetComments.getString("username");
                        String content = resultSetComments.getString("content");
                        System.out.println(username + " commented: " + content);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void addCommentWithInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your user ID: ");
            int userId = scanner.nextInt();
            
            System.out.print("Enter the post ID you want to comment on: ");
            int postId = scanner.nextInt();
            
            scanner.nextLine(); 
            
            System.out.print("Enter your comment: ");
            String commentContent = scanner.nextLine();
            
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO COMMENTS (COMMENT_TEXT, USER_ID, POST_ID) VALUES (?, ?, ?)";
                try (PreparedStatement stmnt = connection.prepareStatement(sql)) {
                    stmnt.setString(1, commentContent);
                    stmnt.setInt(2, userId);
                    stmnt.setInt(3, postId);
                    stmnt.executeUpdate();
                    System.out.println("Comment added successfully!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addComment(int a,String b,  int c,int d ) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO COMMENTS (COMMENT_ID, COMMENT_TEXT, USER_ID, POST_ID) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmnt = connection.prepareStatement(sql)) {
                stmnt.setInt(1, a);
                stmnt.setString(2, b);
                stmnt.setInt(3, c);
                stmnt.setInt(4, d);
                stmnt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        //  likePostWithID(13, 3, 2); 
    	  //addComment(23,"Great post!", 2, 1);
          //addCommentWithInput();
        getLikesAndComments(1);

    }
}
