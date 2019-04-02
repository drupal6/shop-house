package shop;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gene
 * 2019年4月1日
 *
 */
public class Setting {
	
	private static Setting instance = new Setting();
	
	public static Setting getInst() {
		return instance;
	}
	public boolean init() {
		Properties properties = loadProperties();
		if(properties == null) {
			return false;
		}
		this.ip = getValue(properties, "db.ip", "127.0.0.1");
		this.port = getInt(properties, "db.port", 3306);
		this.dbName = getValue(properties, "db.dbname", "shop");
		this.userName = getValue(properties, "db.username", "root");
		this.password = getValue(properties, "db.password", "123456");
		this.barcodeLen = getInt(properties, "barcodelen", 13);
		return true;
	}
	
	private Properties loadProperties() {
		try {
			Properties properties = new Properties();
			InputStream inputStream = new BufferedInputStream(Setting.class.getClassLoader().getResourceAsStream("setting.properties"));
			properties.load(inputStream);
			return properties;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 读取值
	 * @param key
	 * @return
	 */
	private String getValue(Properties properties, String key, String defaultValue) {
		if (properties == null) {
			return defaultValue;
		}
		String value = properties.getProperty(key);
		if(value == null) {
			return defaultValue;
		}
		return value;
	}

	/**
	 * 读取值
	 * @param key
	 * @return
	 */
	private int getInt(Properties properties, String key) {
		if (properties == null) {
			return 0;
		}
		String value = properties.getProperty(key);
		if (isNumeric(value)) {
			return Integer.parseInt(value);
		}
		return 0;
	}
	
	/**
	 * 读取值
	 * @param key
	 * @return
	 */
	private int getInt(Properties properties, String key, int defalueValue) {
		if (properties == null) {
			return defalueValue;
		}
		String value = properties.getProperty(key);
		if (isNumeric(value)) {
			return Integer.parseInt(value);
		}
		return defalueValue;
	}
	
	private boolean isNumeric(String str) {
		if(str == null || str.isEmpty()) {
			return false;
		}
		//先判断number不为空。
		Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
	}
	
	private String ip;
	private int port;
	private String dbName;
	private String userName;
	private String password;
	private int barcodeLen;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBarcodeLen() {
		return barcodeLen;
	}
	public void setBarcodeLen(int barcodeLen) {
		this.barcodeLen = barcodeLen;
	}
	@Override
	public String toString() {
		return "Setting [ip=" + ip + ", port=" + port + ", dbName=" + dbName + ", userName=" + userName + ", password="
				+ password + ", barcodeLen=" + barcodeLen + "]";
	}
}
