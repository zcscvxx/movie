package movie.config;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SaveProperties {
	public static void main(String[] args) throws IOException {
		Properties p = new Properties();
		p.put("driver", "oracle.jdbc.driver.OracleDriver");
		p.put("url", "jdbc:oracle:thin:@192.168.0.52:1521:XE");
		p.setProperty("user", "sem");
		p.setProperty("password", "java");
		FileOutputStream out = new FileOutputStream("oracle.properties");
		p.store(out, "JDBC Config Setting");
		out.close();
		System.out.println("저장완료!");
	}
}