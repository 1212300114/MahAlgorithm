import java.sql.*;

/**
 * Created by Gary on 2017/2/28 0028.
 */


public class jdbcEntrance {

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String dbName = "python";
        String password = "123456";
        String userName = "root";
        String url = "jdbc:mysql://localhost:3306/" + dbName;
        String sql = "select * from user";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("id : " + rs.getInt(1) + " name : "
                        + rs.getString(2));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
