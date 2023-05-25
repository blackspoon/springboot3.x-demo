package com.clx.springboot30.utils;

import org.springframework.util.StringUtils;

import java.io.*;

public class SqlFileReader {

    private static final String SQL_FILE_BASE_PATH = "sql/";

    private SqlFileReader() {

    }

    public static String getSql(String sqlName) {

        if (StringUtils.isEmpty(sqlName)) {
            throw new RuntimeException("SQL file is not exist");
        }

        String sqlFileName = sqlName;
        if (!sqlFileName.endsWith(".sql")) {
            sqlFileName = sqlFileName + ".sql";
        }

        String sql = readSqlFile(SQL_FILE_BASE_PATH + sqlFileName);
        if (StringUtils.isEmpty(sql)) {
            throw new RuntimeException("SQL file read fail");
        }

        return sql;
    }

    private static String readSqlFile(String path) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream is = null;
        Reader reader = null;
        BufferedReader br = null;
        String sql = "";

        try {
            StringBuilder sb = new StringBuilder(100);
            is = loader.getResourceAsStream(path);
            if (is == null) {
                return null;
            }
            reader = new InputStreamReader(is, "UTF-8");
            br = new BufferedReader(reader);
            char[] buf = new char[8192];
            int n;
            while ((n = br.read(buf)) >= 0) {
                sb.append(buf, 0, n);
            }
            sql = sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);

        } finally {
            closeStream(br);
            closeStream(reader);
            closeStream(is);
        }

        if (sql.length() > 0 && sql.charAt(0) == '\uFEFF') {
            sql = sql.substring(1);
        }

        return sql;
    }

    private static void closeStream(Closeable cl) {
        try {
            if (cl != null) {
                cl.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
