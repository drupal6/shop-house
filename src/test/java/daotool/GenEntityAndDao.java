package daotool;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import shop.db.pool.DBPoolMgr;

/**
 * 
 * @author Administrator
 */
public class GenEntityAndDao {

	private String packageOutPath = "bean"; // 指定实体生成所在包的路径
	private String authorName = ""; // 作者名字
	private static String tablename = ""; // 表名
	private static String className = "";
	private String[] colnames; // 列名数组
	private String[] colnamesT; // 列名数组
	private String[] colTypes; // 列名类型数组
	private int[] colSizes; // 列名大小数组
	private String[] colComment; // 列名注释
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*
	private String dataBase;
	private String subdivision = ""; // 细分包结构

	/**
	 * 
	 * @param type
	 * @param tname
	 * @param outPath
	 * @param stutes 是否覆盖文件
	 * @throws SQLException 
	 */
	public GenEntityAndDao(String type, String tname, String outPath, boolean stutes, boolean idAutoIncreate) throws SQLException {
		String[] beanNameT = tname.split("_");
		String beanName = "";
		if (beanNameT.length > 1) {
			for (int j = 2; j < beanNameT.length; j++) {
				beanName = beanName + initcap(beanNameT[j]);
			}
		}
		beanName = beanNameT[1].toLowerCase() + beanName;
		tablename = tname;
		className = beanName;
		dataBase = type;
		this.packageOutPath = outPath;
		// 创建连接
		Connection con = DBPoolMgr.getInst().getConn();
		PreparedStatement pStemt = null;
		PreparedStatement pStemtN = null;
		String sql = null;
		String sqlN = null;
		String dbName = type;
		// 查要生成实体类的表
		sql = "SELECT * FROM " + tablename;
		sqlN = "SELECT COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS "
				+ "WHERE TABLE_NAME= '" + tablename+ "' AND TABLE_SCHEMA='" + dbName
				+ "' ORDER BY ORDINAL_POSITION";
		
		/*
		 * 1 获取结果集 2 并把相关的字段保存起来
		 */
		pStemt = con.prepareStatement(sql);
		pStemtN = con.prepareStatement(sqlN);
		ResultSetMetaData rsmd = pStemt.getMetaData();
		ResultSet rs = pStemtN.executeQuery();
		int size = rsmd.getColumnCount(); // 统计列
		colnames = new String[size];
		colnamesT = new String[size];
		colTypes = new String[size];
		colSizes = new int[size];
		colComment = new String[size];
		for (int i = 0; i < size; i++) {
			rs.next();
			colnamesT[i] = rsmd.getColumnName(i + 1);
			String[] tmp = rsmd.getColumnName(i + 1).split("_");
			String name = tmp[0].toLowerCase();
			if (tmp.length > 1) {
				for (int j = 1; j < tmp.length; j++) {
					name = name + initcap(tmp[j]);
				}
			}
			colnames[i] = name;
			colTypes[i] = rsmd.getColumnTypeName(i + 1);
			colComment[i] = rs.getString("COLUMN_COMMENT");
			if (colTypes[i].equalsIgnoreCase("datetime")) {
				colTypes[i] = "TIMESTAMP";
				f_util = true;
			}
			if (colTypes[i].equalsIgnoreCase("image")
					|| colTypes[i].equalsIgnoreCase("text")) {
				f_sql = true;
			}
			colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
		}

		try {
			File directory = new File("");
			String contentBean = parseBean(colnames, colTypes, colSizes);
			String outputPathB = directory.getAbsolutePath() + "/src/main/java/"
					+ this.packageOutPath.replace(".", "/") + "/bean/"
					+ subdivision + "/" + initcap(className) + ".java";
			java.io.File f = new java.io.File(outputPathB);
			if(!f.exists()||stutes){
				FileWriter fwB = new FileWriter(outputPathB);
				PrintWriter pwB = new PrintWriter(fwB);
				pwB.println(contentBean);
				pwB.flush();
				pwB.close();
				System.out.println("生成"+outputPathB);
			}

			/*
			 * 生成DaoImpl
			 */
			String contentDaoI = parseDaoI(colnames, colTypes, colSizes, idAutoIncreate);
			String outputPathDI = directory.getAbsolutePath() + "/src/main/java/"
					+ this.packageOutPath.replace(".", "/") + "/dao/"+subdivision+"/"
					+ initcap(className) + "Dao.java";
			f = new java.io.File(outputPathDI);
			if(!f.exists()||stutes){
				FileWriter fwDI = new FileWriter(outputPathDI);
				PrintWriter pwDI = new PrintWriter(fwDI);
				System.out.println("生成"+outputPathDI);
				pwDI.println(contentDaoI);
				pwDI.flush();
				pwDI.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：生成实体类主体代码
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String parseBean(String[] colnames, String[] colTypes, int[] colSizes) {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + this.packageOutPath + ".bean" + subdivision
				+ ";\r\n\r\n");
		// 判断是否导入工具包
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n");
		}
		// 注释部分
		sb.append("/**\r\n");
		sb.append("* " + tablename + " \r\n");
		sb.append("* @author " + this.authorName + "\r\n* @date "
				+ new Date() + "\r\n");
		sb.append("*/\r\n");
		// 实体部分
		// extends DataObject {
		sb.append("public class " + initcap(className)
				+ " {\r\n");
		processAllAttrsBean(sb);// 属性
		processAllMethodBean(sb);// get set方法
		sb.append("}");

		return sb.toString();
	}


	private String parseDaoI(String[] colnames, String[] colTypes,
			int[] colSizes, boolean idAutoIncreate) {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + this.packageOutPath + ".dao"+subdivision+";\r\n");
		sb.append("\r\n");
		sb.append("import java.sql.Connection;\r\n");
		sb.append("import java.sql.ResultSet;\r\n");
		sb.append("import java.sql.SQLException;\r\n");
		sb.append("import java.sql.PreparedStatement;\r\n");
		sb.append("import java.util.List;\r\n");
		sb.append("import java.util.Map;\r\n");
		sb.append("import java.sql.Types;\r\n");
		sb.append("import shop.db.DbParameter;\r\n");
		sb.append("import java.util.HashMap;\r\n");
		sb.append("import java.util.ArrayList;\r\n");
		sb.append("import shop.db.pool.DBPoolMgr;\r\n");
		sb.append("import shop.bean" + subdivision + "."
				+ initcap(className) + ";\r\n");
		sb.append("import shop.db.BaseDao;\r\n");
		
		sb.append("\r\n");
		// 注释部分
		sb.append("/**\r\n");
		sb.append("* " + tablename + " dao实现类的生成\r\n");
		sb.append("* @author " + this.authorName + "\r\n* @date "
				+ new Date() + "\r\n");
		sb.append("*/ \r\n");
		// 实体部分
		sb.append("public class " + initcap(className + "Dao")
				+ " extends BaseDao {\r\n");
		// processAllAttrs(sb);// 属性
		processAllMethodDaoI(sb, idAutoIncreate);// get set方法
		sb.append("}");

		return sb.toString();
	}
	
	/**
	 * 功能：生成所有属性
	 * 
	 * @param sb
	 */
	private void processAllAttrsBean(StringBuffer sb) {
		sb.append("\r\n");
		for (int i = 0; i < colnames.length; i++) {
				sb.append("\t/** " + colComment[i] + " */\r\n");
				sb.append("\tprivate " + sqlType2JavaType(colTypes[i], colSizes[i]) + " "
						+ colnames[i] + ";\r\n\n");
		}
	}

	/**
	 * 功能：生成所有方法
	 * 
	 * @param sb
	 */
	private void processAllMethodBean(StringBuffer sb) {
		for (int i = 0; i < colnames.length; i++) {
			String type = sqlType2JavaType(colTypes[i], colSizes[i]);
			sb.append("\tpublic void set" + initcap(colnames[i]) + "("
					+ type + " " + colnames[i]
					+ ") {\r\n");
			sb.append("\t\tthis." + colnames[i] + " = " + colnames[i] + ";\r\n");
			sb.append("\t}\r\n\n");
			sb.append("\tpublic " + type + " get"
					+ initcap(colnames[i]) + "() {\r\n");
			sb.append("\t\treturn " + colnames[i] + ";\r\n");
			sb.append("\t}\r\n\n");
		}
	}

	/**
	 * 生成所有方法
	 * 
	 * @param sb
	 */
	private void processAllMethodDaoI(StringBuffer sb, boolean idAutoIncreate) {
		sb.append("\r\n");
		// create方法
		createM(sb, idAutoIncreate);
		// 查询list
		getListM(sb);
		// ById查询
		getBeanM(sb);
		// update方法
		updateM(sb);
		resultToBeanM(sb);
		openConnM(sb);
	}

	private void openConnM(StringBuffer sb) {
		sb.append("\t@Override\r\n");
		sb.append("\tprotected Connection openConn() {\r\n");
		sb.append("\t\treturn DBPoolMgr.getInst().getConn();\r\n");
		sb.append("\t}\r\n");
	}

	/**
	 * 更新方法
	 * 
	 * @param sb
	 */
	private void updateM(StringBuffer sb) {
		sb.append("\tpublic boolean update" + initcap(className) + "("
				+ initcap(className) + " " + className
				+ ") {\r\n");
		StringBuffer sbT = new StringBuffer();
		StringBuffer sbF = new StringBuffer();
		StringBuffer sbP = new StringBuffer();
		for (int i = 0; i < colnames.length; i++) {
			String type = colTypes[i];
			
			if (type.equals("INT")) {
				type = "INTEGER";
			}

			if (i == 0) {
				sbF.append("\t\tparam.put(" + (colnames.length)
						+ ", new DbParameter(Types." + type.toUpperCase()
						+ ", " + className + ".get" + initcap(colnames[i])
						+ "()));\r\n");
			}else{
				sbP.append("\t\tparam.put(" + (i) + ", new DbParameter(Types."
						+ type.toUpperCase() + ", " + className + ".get"
						+ initcap(colnames[i]) + "()));\r\n");
				sbT.append(colnamesT[i] + "=?");
				if (i < colnames.length - 1) {
					sbT.append(",");
				}
			}

		}
		sb.append("\t\tboolean result = false;\r\n");
		
		sb.append("\t\tString sql = \"update " + tablename + " set "
				+ sbT.toString() + " where "+colnamesT[0]+"=?;\";\r\n");
		sb.append("\t\tMap<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();\r\n");
		sb.append(sbP.toString());
		sb.append(sbF.toString());
		sb.append("\t\tresult = execNoneQuery(sql, param) > -1;\r\n");
		
		sb.append("\t\treturn result;\r\n");
		sb.append("\t}\r\n\r\n");
	}

	/**
	 * 获取单个bean对象的方法
	 * 
	 * @param sb
	 */
	private void getBeanM(StringBuffer sb) {
		String type = sqlType2JavaType(colTypes[0], colSizes[0]);
		sb.append("\tpublic " + initcap(className) + " get"
				+ initcap(className) + "ById("+type+" id) {\r\n");
		sb.append("\t\tString sql = \"select * from " + tablename
				+ " where "+colnamesT[0]+"=?;\";\r\n");
		sb.append("\t\tMap<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();\r\n");
		String paramType = colTypes[0];
		if(paramType.equalsIgnoreCase("INT")) {
			paramType = "INTEGER";
		}
		sb.append("\t\tparam.put(1, new DbParameter(Types."+ paramType +", id));\r\n");
		sb.append("\t\tPreparedStatement pstmt = execQuery(sql, param);\r\n");
		sb.append("\t\tResultSet rs = null;\r\n");
		sb.append("\t\t" + initcap(className) + " " + className
				+ " = null;\r\n");
		sb.append("\t\tif (pstmt != null) {\r\n");
		sb.append("\t\t\ttry {\r\n");
		sb.append("\t\t\t\trs = pstmt.executeQuery();\r\n");
		sb.append("\t\t\t\tif(rs.next()){\r\n");
		sb.append("\t\t\t\t\t" + className + " = resultToBean(rs);\r\n");
		sb.append("\t\t\t\t}\r\n");
		sb.append("\t\t\t} catch (SQLException e) {\r\n");
		sb.append("\t\t\t\te.printStackTrace();;\r\n");
		sb.append("\t\t\t} finally {\r\n");
		sb.append("\t\t\t\tcloseConn(pstmt, rs);\r\n");
		sb.append("\t\t\t}\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t\treturn " + className + ";\r\n");
		sb.append("\t}\r\n\r\n");
	}

	/**
	 * 生成bean转换方法
	 * 
	 * @param sb
	 */
	private void resultToBeanM(StringBuffer sb) {
		sb.append("\tpublic " + initcap(className)
				+ " resultToBean(ResultSet rs) throws SQLException {\r\n");
		sb.append("\t\t" + initcap(className) + " " + className
				+ " = new " + initcap(className) + "();\r\n");
		for (int i = 0; i < colnames.length; i++) {
			sb.append("\t\t" + className + ".set" + initcap(colnames[i])
					+ "(rs.get" + initcap(sqlType2ResultSetType(colTypes[i], colSizes[i]))
					+ "(\"" + colnamesT[i] + "\"));\r\n");
		}
		sb.append("\t\treturn " + className + ";\r\n");
		sb.append("\t}\r\n\r\n");
	}

	/**
	 * 生成获取列表方法
	 * 
	 * @param sb
	 */
	private void getListM(StringBuffer sb) {
		sb.append("\tpublic List<" + initcap(className) + "> get"
				+ initcap(className) + "List() {\r\n");
		// String sql="select * from t_user;";
		sb.append("\t\tString sql = \"select * from " + tablename + ";\";\r\n");
		sb.append("\t\tPreparedStatement pstmt = execQuery(sql, null);\r\n");
		sb.append("\t\tResultSet rs = null;\r\n");
		sb.append("\t\tList<" + initcap(className)
				+ "> infos = null;\r\n");
		sb.append("\t\tif (pstmt != null) {\r\n");
		sb.append("\t\t\tinfos = new ArrayList<" + initcap(className)
				+ ">();\r\n");
		sb.append("\t\t\ttry {\r\n");
		sb.append("\t\t\t\trs = pstmt.executeQuery();\r\n");
		sb.append("\t\t\t\twhile (rs.next()) {\r\n");
		sb.append("\t\t\t\t\tinfos.add(resultToBean(rs));\r\n");
		sb.append("\t\t\t\t}\r\n");
		sb.append("\t\t\t} catch (SQLException e) {\r\n");
		sb.append("\t\t\t\te.printStackTrace();\r\n");
		sb.append("\t\t\t} finally {\r\n");
		sb.append("\t\t\t\tcloseConn(pstmt, rs);\r\n");
		sb.append("\t\t\t}\r\n");
		sb.append("\t\t}\r\n");
		sb.append("\t\treturn infos;\r\n");
		sb.append("\t}\r\n\r\n");
	}

	/**
	 * 生成创建方法
	 * 
	 * @param sb
	 */
	private void createM(StringBuffer sb, boolean idAutoIncreate) {
		sb.append("\tpublic boolean create" + initcap(className) + "("
				+ initcap(className) + " " + className
				+ ") {\r\n");
		StringBuffer sbT = new StringBuffer();
		StringBuffer sbF = new StringBuffer();
		StringBuffer sbP = new StringBuffer();
		String keySetMethonName = null;
		for (int i = 0; i < colnames.length; i++) {
			if(idAutoIncreate && i == 0) {
				keySetMethonName = initcap(colnames[i]);
			}
			String type = colTypes[i];
			sbT.append(colnamesT[i]);
			sbF.append("?");
			if (type.equals("INT")) {
				type = "INTEGER";
			}
			
			sbP.append("\t\tparam.put(" + (i + 1) + ", new DbParameter(Types."
					+ type.toUpperCase() + ", " + className + ".get"
					+ initcap(colnames[i]) + "()));\r\n");
			if (i != colnames.length - 1) {
				sbT.append(",");
				sbF.append(",");
			}
		}
		sb.append("\t\tboolean result = false;\r\n");
		
		sb.append("\t\tString sql = \"insert into " + tablename + " ("
				+ sbT.toString() + ") values (" + sbF.toString() + ");\";\r\n");
		sb.append("\t\tMap<Integer, DbParameter> param = new HashMap<Integer, DbParameter>();\r\n");
		sb.append(sbP.toString());
		if(idAutoIncreate) {
			sb.append("\t\tint id = execLastId(sql, param);\r\n");
			sb.append("\t\tif(id > 0) {\r\n");
			sb.append("\t\t\t"+className+".set"+keySetMethonName+"(id);\r\n");
			sb.append("\t\t\tresult = true;\r\n");
			sb.append("\t\t}\r\n");
		}else {
			sb.append("\t\tresult = execNoneQuery(sql, param) > -1;\r\n");
		}
		sb.append("\t\treturn result;\r\n");
		sb.append("\t}\r\n\r\n");
	}

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {

		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	/**
	 * 功能：获得列的数据类
	 * 
	 * @param sqlType
	 * @param makeProto TODO
	 * @return
	 */
	private String sqlType2JavaType(String sqlType, int size) {

		if (sqlType.equalsIgnoreCase("bit") 
				|| sqlType.equalsIgnoreCase("int")
				|| sqlType.equalsIgnoreCase("smallint")
				|| sqlType.equalsIgnoreCase("tinyint")) {
			if(size > 11) {
				return "long";
			}else {
				return "int";
			}
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("decimal")
				|| sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")
				|| sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("varchar")
				|| sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar")
				|| sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")
				|| sqlType.equalsIgnoreCase("image")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")
				|| sqlType.equalsIgnoreCase("timestamp")) {
			return "Date";
		}

		return null;
	}

	/**
	 * 
	 * @param sqlType
	 * @return
	 */
	private String sqlType2ResultSetType(String sqlType, int size) {

		if (sqlType.equalsIgnoreCase("bit") 
				|| sqlType.equalsIgnoreCase("int")
				|| sqlType.equalsIgnoreCase("smallint")
				|| sqlType.equalsIgnoreCase("tinyint")) {
			if(size > 11) {
				return "long";
			}else {
				return "int";
			}
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("decimal")
				|| sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")
				|| sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("varchar")
				|| sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar")
				|| sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")
				|| sqlType.equalsIgnoreCase("image")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "datetime";
		}else if(sqlType.equalsIgnoreCase("timestamp")){
			return "timestamp";
		}

		return null;
	}
}