package com.itgroup;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true){
            Scanner scan = new Scanner(System.in);

            MemberManager manager = new MemberManager();

            System.out.println("메뉴 선택");
            System.out.println("0:종료, 1:목록 조회, 2:가입, 3:수정, 4:총 회원수, 5:탈퇴, 6:회원 정보, 7:xx, 8:xx");

            int menu = scan.nextInt(); // 선택한 메뉴
            switch (menu){
                case 0 :
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0); // 운영체제 종료를 알리고 빠져나가기
                    // exit 안하면 whill 다시 무한루프
                    break;
                case 1 :
                    manager.selectAll();
                    System.exit(0);
                    break;
                case 2 :
                    break;
                case 3 :
                    break;
                case 4 :
                    manager.getSize();
                    System.exit(0);
                    break;
                case 5 :
                    break;
                case 6 :
                    break;
                case 7 :
                    break;
                case 8 :
                    break;
            }
        }
    }
}