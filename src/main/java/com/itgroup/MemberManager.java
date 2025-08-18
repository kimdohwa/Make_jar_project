package com.itgroup;

import com.itgroup.dao.MemberDao;

public class MemberManager {

    private MemberDao dao = null; // 실제 데이터 베이스와 연동하는 다오클래스

    public MemberManager() {
        this.dao = new MemberDao();
    }

    public void selectAll() {// 모든회원의 정보 조회

    }

    public void getSize() {// 총 회원 수 조회 (~명)
        int cnt = dao.getSize();
        String msg ;
        if (cnt == 0){
            msg = "검색된 회원이 존재하지 않습니다.";
        }else {
            msg = "검색된 회원은 총 "+ cnt +"명 입니다.";
        }
        System.out.println(msg);
    }
}
