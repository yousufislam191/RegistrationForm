import java.sql.*;
import javax.swing.JOptionPane;

public class DbConnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private int flag = 0;

    public DbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
                 //Connection
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javalogin","root","");
                st = con.createStatement();
                rs = st.executeQuery("select * from registration");
        } 
        catch (Exception e) {
            System.err.println("Error : " +e);
        }
    }
    public void RegisterInsert(String queryInsert) {
        try {
            st.executeUpdate(queryInsert);
            JOptionPane.showMessageDialog(null, "Registration Complete Successfully");
        } 
        catch (Exception e3) {
            JOptionPane.showMessageDialog(null, "Not Inserted any Data !!" +e3);
        }
    }

    //for login query
    public void Login(String loginQuery, String loginUserEmail, String loginUserPass) {
        try {
            rs = st.executeQuery(loginQuery);
            while(rs.next()) {
                String tableUserEmail = rs.getString(3);
                String tablePass = rs.getString(4);

                if (loginUserEmail.equals(tableUserEmail) && loginUserPass.equals(tablePass)) {
                    flag = 1;
                    break;
                }
            }
            if(flag == 0) {
                JOptionPane.showMessageDialog(null, "wrong username & password");
            }
            else {
                JOptionPane.showMessageDialog(null, "Successfully login");
            }
        }
        catch (Exception e) {
            System.err.println("Login Error :"+e);
        }
    }
}
