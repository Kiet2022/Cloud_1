
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( urlPatterns = {"/count"})
public class Testing extends HttpServlet{
    // private final static String JDBC_URL =
    // "jdbc:mysql://ec2-54-169-248-237.ap-southeast-1.compute.amazonaws.com:3306/"
    // + "cloudcomputing";
    private final static String JDBC_URL = "jdbc:mysql://localhost:3306/" + "clouddatabase";
    private final static String DB_USER = "root";

    private final static String DB_PASSWORD = "1931200013";

    private static final long serialVersionUID = 1;
    
    @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//done
BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            Connection mySQLClient = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            PreparedStatement st = mySQLClient.prepareStatement("SELECT COUNT(*) as count FROM customer");
            ResultSet rs = st.executeQuery();
            String report = "";
            if(rs.next()){
                //System.out.println(rs.getString("count"));
                report = rs.getString("count");
            }
            resp.setContentType("text/plain");
            resp.setStatus(200);
            writer.write(report);
            writer.flush();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

