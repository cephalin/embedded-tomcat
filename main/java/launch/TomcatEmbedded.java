package launch;
 
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
 
public class TomcatEmbedded {

    public static void main(String[] args) throws LifecycleException, IOException {

        // Set to "" to make the application available at the root of the URL.
        String contextPath = "";
        // Set to location of your webapp directory.
        String webappDir = new File("src/main/webapp/").getAbsolutePath();
        // Set to the PORT variable or default to 8080.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        Tomcat tomcat = new Tomcat();
        Path tempPath = Files.createTempDirectory("tomcat-base-dir");
        tomcat.setBaseDir(tempPath.toString());         
        tomcat.addWebapp(contextPath, webappDir);
        tomcat.getConnector().setPort(Integer.valueOf(webPort));
        tomcat.start();
        tomcat.getServer().await();
    }
}