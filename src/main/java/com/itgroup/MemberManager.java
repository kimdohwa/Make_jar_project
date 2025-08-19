package com.itgroup;

import com.itgroup.bean.Member;
import com.itgroup.dao.MemberDao;

import java.util.ArrayList;
import java.util.List;

public class MemberManager {

    private MemberDao dao = null; // 실제 데이터 베이스와 연동하는 다오클래스

    public MemberManager() {
        this.dao = new MemberDao();
    }

    public void selectAll() {// 모든회원의 정보 조회
        List<Member> members = dao.selectAll();

        for (Member item:members){
            String id = item.getId();
            String name = item.getName();
            String password = item.getPassword();
            String gender = item.getGender();
            String birth = item.getBirth();
            String marriage = item.getMarriage();
            int salary = item.getSalary();
            String address = item.getAddress();
            String manager = item.getManager();

            System.out.println(id+"\t"+name+"\t"+password+"\t"+gender+"\t"+birth+"\t"+marriage+"\t"+salary+"\t"+address+"\t"+manager);
        }

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
    } // 받은값을 바로 출력 viod로 진행

    public void findByGender() {
        String gen = "여자" ;
        List<Member> bygender = dao.findByGender(gen);

        for (Member item:bygender){
            String id = item.getId();
            String name = item.getName();
            String password = item.getPassword();
            String gender = item.getGender();
            String birth = item.getBirth();
            String marriage = item.getMarriage();
            int salary = item.getSalary();
            String address = item.getAddress();
            String manager = item.getManager();

            System.out.println(id+"\t"+name+"\t"+password+"\t"+gender+"\t"+birth+"\t"+marriage+"\t"+salary+"\t"+address+"\t"+manager);
        }


    }

    public void getOne() {
        String findid = "xx";
        Member one1 = dao.getOne(findid);

      //  if (one1 == null){
      //      System.out.println("찾으시는 회원이 존재하지 않습니다.");
      //  } >> 예외처리하거나 if사용 / 보통 빠르게 if 가능
        try {
            String id = one1.getId();
            String name = one1.getName();
            String password = one1.getPassword();
            String gender = one1.getGender();
            String birth = one1.getBirth();
            String marriage = one1.getMarriage();
            int salary = one1.getSalary();
            String address = one1.getAddress();
            String manager = one1.getManager();

            System.out.println(id+"\t"+name+"\t"+password+"\t"+gender+"\t"+birth+"\t"+marriage+"\t"+salary+"\t"+address+"\t"+manager);

        }catch (Exception e){
            System.out.println("없음");
            e.printStackTrace();
        }

    }

    public void asc() {
        List<Member> ascMember1 = dao.ascMember();
        // dao.ascMember를 가져와서 타입>리스트)ascMember1에 담기
        // dao에서 데이터베이스와 연동하고 manger에서 실행할 예정 > 맨위에 dao와 연동된 객체 있음

        //담아진 리스트를 출력하기 > 모든리스트 출력 확장 for
        for (Member one:ascMember1){
            //System.out.println(one);>> tostring 으로 전체출력 정보를 변수에 넣어 조립해주기
            String id = one.getId();
            String name = one.getName();
            String password = one.getPassword();
            String gender = one.getGender();
            String birth = one.getBirth();
            String marriage = one.getMarriage();
            int salary = one.getSalary();
            String address = one.getAddress();
            String manager = one.getManager();

            System.out.println(id+"\t"+name+"\t"+password+"\t"+gender+"\t"+birth+"\t"+marriage+"\t"+salary+"\t"+address+"\t"+manager);
        }

    }

    public void deleteData() {
        String findId = "lee";
        int delete = dao.deleteData(findId);

        if (delete == -1){
            System.out.println("탈퇴 실패");
        }else if (delete == 0){
            System.out.println("탈퇴할 회원이 존재하지않습니다.");
        }else if (delete > 0){
            System.out.println(delete+"명이 삭제되었습니다.");
        }
    }
}
