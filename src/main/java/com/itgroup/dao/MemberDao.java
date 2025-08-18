package com.itgroup.dao;

import java.sql.*;

public class MemberDao {

    public MemberDao() {
        //해당 드라이버는 ojdbv6.jar 파일에 포함되어있는 자바클래스입니다.
        String driver = "oracle.jdbc.driver.OracleDriver";
        try {

            Class.forName(driver); // 동적 객체를 생성하는 문법입니다.

        } catch (ClassNotFoundException e) {
            System.out.println("해당 드라이버가 존재하지않습니다.");
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        Connection conn = null; // 접속객체

        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "oraman";
        String password = "oracle";

        try {
            conn = DriverManager.getConnection(url, id, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }

    public int getSize() {
        String sql = "select count(*) as cnt from members ";

        PreparedStatement pstmt =null ;
        ResultSet rs = null;
        Connection conn = null;//접속객체 구하기
        int cnt = 0;


        try {
            conn = this.getConnection();//1. 접속객체
            pstmt = conn.prepareStatement(sql);// 2. 문장객체
            rs = pstmt.executeQuery(); //3. 결과객체

            if(rs.next()){//한건이라도 조회되면 트루
                 cnt = rs.getInt("cnt"); // 컬럼 이름 가져오기
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // 프로그램을 닫을때는 객체생성순서의 반대로 진행 3>2>1
        finally {
            try{
                if (rs != null){rs.close();}
                if (pstmt != null){pstmt.close();}
                if (conn != null){conn.close();}

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return cnt;
    }

}
