package com.itgroup.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SuperDao {
    public SuperDao() {

            //해당 드라이버는 ojdbv6.jar 파일에 포함되어있는 자바클래스입니다.
            String driver = "oracle.jdbc.driver.OracleDriver";
            try {

                Class.forName(driver); // 동적 객체를 생성하는 문법입니다.

            } catch (ClassNotFoundException e) {
                System.out.println("해당 드라이버가 존재하지않습니다.");
                throw new RuntimeException(e);
            }
        }

    public Connection getConnection() {
        Connection conn = null; // 접속객체

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "oraman";
        String password = "oracle";

        try {
            conn = DriverManager.getConnection(url, id, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;//= DriverManager.getConnection(url, id, password);
    }

    }

