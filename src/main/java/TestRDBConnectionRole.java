
@WebServlet({ "/rdbconnectrole" })

public class TestRDBConnectRole extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream(), "UTF-8"));

        Connection connection;

        try {

            connection = RDSConnectionRole.getDBConnectionUsingIamRole();

            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT 'Success!' FROM DUAL;");

            while (rs.next()) {

                String id = rs.getString(1);

                System.out.println(id); // Should print "Success!"

                writer.write(id);

                writer.flush();

            }

            // rs.close();

            stmt.close();

            connection.close();

            writer.close();

            // RDSConnection.clearSslProperties();

        } catch (Exception e) {

            writer.write(e.toString());

            writer.flush();

            writer.close();

        }

    }

}