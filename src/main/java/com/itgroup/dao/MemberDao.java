package com.itgroup.dao;

import com.itgroup.bean.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public int getSize() {//문장전달하기
        String sql = "select count(*) as cnt from members ";

        Connection conn = null;//접속객체 구하기
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int cnt = 0;

        try {
            conn = this.getConnection();//1. 접속객체
            pstmt = conn.prepareStatement(sql);// 2. 문장객체
            rs = pstmt.executeQuery(); //3. 결과객체

            if (rs.next()) {//한건이라도 조회되면 트루
                cnt = rs.getInt("cnt"); // "cnt"라는 컬럼의 값을 **정수(int)**로 꺼내오는 부분
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // 프로그램을 닫을때는 객체생성순서의 반대로 진행 3>2>1
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return cnt;
    }

    public List<Member> selectAll() {// 모든회원의 정보 조회
        List<Member> members = new ArrayList<Member>(); // 리스트생성

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from members order by name asc";

        try {
            conn = this.getConnection();//접속객체 구하기
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); // (resultset)컴퓨터 메모리로 가지고옴

            while (rs.next()) {
                /*System.out.print(rs.getString("id")+" ");
                System.out.print(rs.getString(2)+" ");
                System.out.print(rs.getInt(7)+" ");
                System.out.println(rs.getString("gender"));*/

                Member bean = new Member(); //while 은 각 칼럼의 정보를 bean에담아 리스트에 저장한다

                bean.setId(rs.getString("id"));
                bean.setName(rs.getString("name"));
                bean.setGender(rs.getString("gender"));
                bean.setBirth(rs.getString("birth"));
                bean.setMarriage(rs.getString("marriage"));
                bean.setSalary(rs.getInt("salary"));
                bean.setAddress(rs.getString("address"));
                bean.setManager(rs.getString("manager"));

                members.add(bean);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return members;
    }

    public List<Member> findByGender(String gender) {
        List<Member> bygender = new ArrayList<Member>();

        String sql = "select * from members where gender = ? order by name asc";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = this.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, gender);
            rs = ps.executeQuery();

            while (rs.next()) {
                Member bean = new Member();

                bean.setId(rs.getString("id"));
                bean.setName(rs.getString("name"));
                bean.setGender(rs.getString("gender"));
                bean.setBirth(rs.getString("birth"));
                bean.setMarriage(rs.getString("marriage"));
                bean.setSalary(rs.getInt("salary"));
                bean.setAddress(rs.getString("address"));
                bean.setManager(rs.getString("manager"));

                bygender.add(bean);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        return bygender;
    }

    public Member getOne(String id) {//해당사용자의 정보를 bean 형태로 반환해줍니다.

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Member one = null;

        String sql = "select * from members where id = ? ";

        try {
            conn = this.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id); // -> 1번째 물음표를 id로 치환하세요
            rs = ps.executeQuery();

            if (rs.next()) {
                one = new Member(); // if가 이루어질때 객체가 생성되어야함

                one.setId(rs.getString("id"));
                one.setName(rs.getString("name"));
                one.setGender(rs.getString("gender"));
                one.setBirth(rs.getString("birth"));
                one.setMarriage(rs.getString("marriage"));
                one.setSalary(rs.getInt("salary"));
                one.setAddress(rs.getString("address"));
                one.setManager(rs.getString("manager"));
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return one;
        }
    }

    public List<Member> ascMember() {//manager 에서 타입을 list로 선언하여 반환타입 list
        /*데이터베이스를 가져와서 멤버클래스의 한 행에 대입 시킬것이므로 Member와 연동시켜줘야함(객체)
        객체는 리스트 형식으로 만들어 줘야함 */
        List<Member> ascmember3 = new ArrayList<Member>();

        String sql = "select * from members order by name desc "; // sql 명령어 입력창

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //메소드의 가장 바깥쪽에서 선언 > 다음에 finally로 닫아줘야하기 때문에
        //여러 행을 가져와야하기때문에 while 문장으로 실행
        try {
            conn = this.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {//rs가 값을 가져옴(1행)> 컬럼의 정보를 list Member 에 담아줘야함 > 반환타입이므로
                //Member의 변수에 담아주려면 set사용 > Member를 사용하려면 객체 생성해야함
                Member one = new Member();//다시 시작할때 초기화

                one.setId(rs.getString("id"));
                one.setName(rs.getString("name"));
                one.setGender(rs.getString("gender"));
                one.setBirth(rs.getString("birth"));
                one.setMarriage(rs.getString("marriage"));
                one.setSalary(rs.getInt("salary"));
                one.setAddress(rs.getString("address"));
                one.setManager(rs.getString("manager"));

                ascmember3.add(one);//리스트에 담아주기
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return ascmember3;  //반환값은 만들어진 리스트를 반환함

    }

    public int deleteData(String findId) {

        Connection conn = null;
        PreparedStatement ps = null;
        int rs = -1; //삭제가 아예 안되었다 > 0으로하면 접속은되었으나 id가없어서 삭제되지않음

        String sql = "delete from members where id = ?";

        try {
            conn = this.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, findId);
            rs = ps.executeUpdate();

            conn.commit(); // 커밋

        } catch (Exception e) {
            try {
                conn.rollback(); // 접속실패시 > 롤백 / 롤백도 무조건 예외처리해줘야함
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            e.printStackTrace();
        }
        finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return rs;
        }
    }
}
