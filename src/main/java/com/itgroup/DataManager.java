package com.itgroup;

import com.itgroup.bean.Board;
import com.itgroup.bean.Member;
import com.itgroup.dao.BoardDao;
import com.itgroup.dao.MemberDao;

import java.util.List;
import java.util.Scanner;

public class DataManager {

    private MemberDao Mdao = null; // 실제 데이터 베이스와 연동하는 다오클래스
    private Scanner scam = null;
    private BoardDao Bdao = null;

    public DataManager() {
        this.Mdao = new MemberDao();
        this.scam= new Scanner(System.in);
        this.Bdao = new BoardDao();
    }

    public void selectAll() {// 모든회원의 정보 조회
        List<Member> members = Mdao.selectAll();

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
        int cnt = Mdao.getSize();
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
        List<Member> bygender = Mdao.findByGender(gen);

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
        String findid = "lee";
        Member one1 = Mdao.getOne(findid);

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
        List<Member> ascMember1 = Mdao.ascMember();
        // Mdao.ascMember를 가져와서 타입>리스트)ascMember1에 담기
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
        //String findId = "lee";
        System.out.println("아이디를 입력해주세요");
        String findId = scam.next();
        int delete = Mdao.deleteData(findId);

        if (delete == -1){
            System.out.println("탈퇴 실패");
        }else if (delete == 0){
            System.out.println("탈퇴할 회원이 존재하지않습니다.");
        }else if (delete > 0){
            System.out.println(delete+"명이 삭제되었습니다.");
        }
    }

    public void insertData() {// 생성자에 스캐너 객체 생성
        Member bean = new Member();
        int cnt = -1 ;

        System.out.println("id 입력 : ");
        String id = scam.next();

        System.out.println("이름 입력 : ");
        String name = scam.next();

        System.out.println("비번 입력 : ");
        String password = scam.next();

        System.out.println("성별 입력 : ");
        String gender = scam.next();

        System.out.println("생년월일 입력 : ");
        String birth = scam.next();

        System.out.println("혼인여부 입력 : ");
        String marriage = scam.next();

        System.out.println("월급 입력 : ");
        int salary = scam.nextInt();

        System.out.println("주소 입력 : ");
        String address = scam.next();

        System.out.println("관리자 입력 : ");
        String manager = scam.next();

        bean.setId(id);
        bean.setName(name);
        bean.setPassword(password);
        bean.setGender(gender);
        bean.setBirth(birth);
        bean.setMarriage(marriage);
        bean.setSalary(salary);
        bean.setAddress(address);
        bean.setManager(manager);


        cnt = Mdao.insertData(bean); //회원가입을 위해 매개변수입력 > 매개변수는 bean(1행) 정보입력필요> 객체생성

        if (cnt == -1){
            System.out.println("연결불가");
        }else if (cnt == 1){
            System.out.println("아이디"+ id + "로 가입을 성공했습니다.");
        }

    }

    public void upDate() {
        System.out.println("수정할 id 입력 : ");
        String id = scam.next();

        Member bean = Mdao.getOne(id); // one은 기존에 입력되어있는 정보를 말함 >> 1. id로 행객체를 반환하여 bean에 담김
        System.out.println("수정할 이름 입력 : ");
        String name = scam.next();
        System.out.println("수정할 비번 입력 : ");
        String password = scam.next();
        System.out.println("수정할 성별 입력 : ");
        String gender = scam.next();
        System.out.println("수정할 생년월일 입력 : ");
        String birth = scam.next();
        System.out.println("수정할 혼인여부 입력 : ");
        String marriage = scam.next();
        System.out.println("수정할 월급 입력 : ");
        int salary = scam.nextInt();
        System.out.println("수정할 주소 입력 : ");
        String address = scam.next();
        System.out.println("수정할 관리자 입력 : ");
        String manager = scam.next();

        bean.setName(name); ;
        bean.setPassword(password) ;
        bean.setGender(gender);
        bean.setBirth(birth);
        bean.setMarriage(marriage);
        bean.setSalary(salary);
        bean.setAddress(address);
        bean.setManager(manager);
        bean.setId(id);

        int cnt = Mdao.upDate(bean);

        if (cnt == -1){
            System.out.println("수정 실패");
        }else if (cnt == 0){
            System.out.println("수정할 회원이 존재하지않습니다.");
        }else if (cnt > 0){
            System.out.println(cnt+"명이 수정되었습니다.");
        }


    }

    public void selectAllBoard() {
        List<Board> allBoards = Bdao.selectAllBoard();

        for (Board one:allBoards){
            int no = one.getNo();
            String writer = one.getWriter();
            String password = one.getPassword();
            String subject = one.getSubject();
            String content = one.getContent();
            int readhit = one.getReadhit();
            String regdate = one.getRegdate();

            String msg = no+"\t"+writer+"\t"+password+"\t"+subject+"\t"+content+"\t"+readhit+"\t"+regdate;
            System.out.println(msg);
        }
    }
    public void selectEvenData() {
        List<Board> onedata = Bdao.selectEvenData() ;

        for (Board one:onedata){
            int no = one.getNo();
            String writer = one.getWriter();
            String password = one.getPassword();
            String subject = one.getSubject();
            String content = one.getContent();
            int readhit = one.getReadhit();
            String regdate = one.getRegdate();

            String msg = no+"\t"+writer+"\t"+password+"\t"+subject+"\t"+content+"\t"+readhit+"\t"+regdate;
            System.out.println(msg);
        }
    }
}
