package h2db.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.tools.Server;
import org.h2.util.NetUtils;

public class H2DBSample {
    Server server;

    public H2DBSample() {
        try {
            //this.server = Server.createTcpServer("-tcp", "-tcpAllowOthers").start();
            this.server = Server.createWebServer("-web", "-webAllowOthers").start();
            String localAddress = NetUtils.getLocalAddress();
            System.out.println("アドレス: " + localAddress);
            System.out.println("サーバポート: " + this.server.getPort());
            System.out.println("URL: " + this.server.getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connect() throws SQLException {
        System.out.println("jdbc:h2:" + this.server.getURL() +
                "/mem:test;DB_CLOSE_DELAY=-1");
        Connection connection = DriverManager.getConnection("jdbc:h2:" + this.server.getURL() +
                "/mem:test;DB_CLOSE_DELAY=-1", "sa", "");

        return connection;
    }

    public void initExe(){
        try (Connection connection = this.connect()) {
            Statement stmt = this.connect().createStatement();
            stmt.executeUpdate("create table if not exists user (firstname varchar(30), lastname varchar(30))");
            stmt.executeUpdate("insert into user values ('f1','p1')");
            stmt.executeUpdate("insert into user values ('f2','p1')");
            stmt.executeUpdate("insert into user values ('f3','p1')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
