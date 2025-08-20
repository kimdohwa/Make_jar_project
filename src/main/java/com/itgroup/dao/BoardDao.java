package com.itgroup.dao;

import com.itgroup.bean.Board;
import com.itgroup.bean.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDao extends SuperDao{


    public BoardDao() {
    }


    private List<Board> makeBean(ResultSet rs) {
        List<Board> allBoards2 = new ArrayList<Board>();
        Board bean = null;
        try {
            while (rs.next()) {
                bean = new Board();
                bean.setNo(rs.getInt("no"));
                bean.setWriter(rs.getString("writer"));
                bean.setPassword(rs.getString("password"));
                bean.setSubject(rs.getString("subject"));
                bean.setContent(rs.getString("content"));
                bean.setReadhit(rs.getInt("readhit"));
                bean.setRegdate(String.valueOf(rs.getDate("regdate")));

                allBoards2.add(bean);
            }
            return allBoards2 ;

        }catch (Exception e){
            e.printStackTrace();
        }
        return allBoards2 ;
    }


    public List<Board> selectAllBoard() {
        List<Board> allBoards =null;
         // 리스트생성
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from boards order by no asc";

        try {
            conn = super.getConnection();//접속객체 구하기
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(); // (resultset)컴퓨터 메모리로 가지고옴

            allBoards = makeBean(rs);
            //resultset 에서 데이터를 가져와서 bean객체에 담아 저장합니다.

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

        return allBoards;
    }

    public List<Board> selectEvenData() {

        List<Board> bean = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from boards where mod(no,2)=0";

        try {
            conn = this.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            bean = makeBean(rs);

        }catch (Exception e){
         e.printStackTrace();
        }
        finally {
            try {
                if (rs != null){rs.close();}
                if (pstmt != null){pstmt.close();}
                if (conn != null){conn.close();}

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return bean;
    }
}
