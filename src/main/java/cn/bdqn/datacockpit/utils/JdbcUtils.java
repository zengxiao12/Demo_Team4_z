/**
 * Project Name:DataCockpit
 * File Name:JdbcUtils.java
 * Package Name:cn.bdqn.datacockpit.utils
 * Date:2017年8月29日上午9:15:30
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */
/**
 * 
 */

package cn.bdqn.datacockpit.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import cn.bdqn.datacockpit.mapper.RelevanceTable;

/**
 * Description: <br/>
 * Date: 2017年8月29日 上午9:15:30 <br/>
 * 
 * @author jiaoHJ
 * @version
 * @see
 */

public class JdbcUtils {
    private static String driver = null;

    private static String url = null;

    private static String username = null;

    private static String password = null;
    static {
        try {
            Properties ppt = new Properties();
            InputStream is = RelevanceTable.class.getClassLoader().getResourceAsStream("jdbc.properties");
            ppt.load(is);
            driver = ppt.getProperty("driver");
            url = ppt.getProperty("url");
            username = ppt.getProperty("username");
            password = ppt.getProperty("password");
            Class.forName(driver);
        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(Connection conn, Statement st, ResultSet rs) throws Exception {
        if (conn != null) {
            conn.close();
        }
        if (st != null) {
            st.close();
        }
        if (rs != null) {
            rs.close();
        }

    }
}
