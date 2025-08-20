package com.itgroup;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true){
            Scanner scan = new Scanner(System.in);

            DataManager manager = new DataManager();

            System.out.println("메뉴 선택");
            System.out.println("0:종료, 1:목록 조회, 2:가입, 3:수정, 4:총 회원수, 5:탈퇴, 6:회원 정보, 7:성별, 8:반대 이름순으로 출력하기");
            System.out.println("11:게시물 전체, 12:등록, 13:수정, 14:전체건수, 15:삭제, 16: 1건 정보");

            int menu = scan.nextInt(); // 선택한 메뉴
            switch (menu){
                case 0 :
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0); // 운영체제 종료를 알리고 빠져나가기
                    // exit 안하면 whill 다시 무한루프
                    break;
                case 1 :
                    manager.selectAll();
                    break;

                case 2 :
                    manager.insertData();
                    break;

                case 3 :
                    manager.upDate();
                    break;

                case 4 :
                    manager.getSize();
                    break;

                case 5 :
                    manager.deleteData();
                    break;
                case 6 :
                    manager.getOne();
                    break;
                case 7 :
                    manager.findByGender();
                    break;

                case 8 :
                    manager.asc();
                    break;

                case 11 :
                    manager.selectAllBoard();

                    break;

                case 12 :

                    break;
                case 13 :

                    break;
                case 14 :

                    break;
                case 15 :

                    break;
                case 16 :
                    manager.selectEvenData();
                    break;

            }
        }
    }
}