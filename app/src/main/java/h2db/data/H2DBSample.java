package h2db.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.Driver;
import org.h2.tools.Server;

public class H2DBSample {
    public Server webServer;
    public Server tcpServer;
    public String webPort;
    public String tcpPort;

    public H2DBSample(String webPort, String tcpPort) {
        this.webPort = webPort;
        this.tcpPort = tcpPort;

        try {
            this.tcpServer = Server.createTcpServer("-tcp", "-ifNotExists", "-tcpPort", this.tcpPort).start();
            this.webServer = Server.createWebServer("-web", "-ifNotExists", "-webPort", this.webPort).start();
            System.out.println("URL(web): " + this.webServer.getURL());
            System.out.println("URL(tcp): " + this.tcpServer.getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public H2DBSample(){
        this.webPort = "8088";
        this.tcpPort = "9000";

        try {
            this.tcpServer = Server.createTcpServer("-tcp", "-ifNotExists", "-tcpPort", this.tcpPort).start();
            this.webServer = Server.createWebServer("-web", "-ifNotExists", "-webPort", this.webPort).start();
            System.out.println("URL(web): " + this.webServer.getURL());
            System.out.println("URL(tcp): " + this.tcpServer.getURL());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initExe(){
        Driver.load();

        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test;", "sa", "")) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(this.getCreateSql());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getCreateSql() {
        String path = "/create.sql";

        try(InputStream is = getClass().getResourceAsStream(path)){

            BufferedReader br = new BufferedReader((new InputStreamReader(is)));

            String result = this.loadFill(br);

            return result.toString();

        } catch(IOException e){

            e.printStackTrace();

            return null;
        }

    }

    private String loadFill(BufferedReader br){
        boolean flag = false;
        try {
            flag = br.ready();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();

        while(flag){
            try {sb.append(br.readLine() + " ");}
            catch (IOException e) {e.printStackTrace();}
            try {flag = br.ready();}
            catch (IOException e) {e.printStackTrace();}
        }
        System.out.println("sql: " + sb.toString());

        return sb.toString();
    }
}
