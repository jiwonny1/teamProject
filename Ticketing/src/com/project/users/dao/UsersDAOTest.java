package com.project.users.dao;

import com.project.users.vo.UsersVO;

import java.util.Scanner;

import com.project.users.vo.PointVO;

public class UsersDAOTest {

    public static void main(String[] args) {
    	System.out.println();
    	Scanner sc = new Scanner(System.in);

    	System.out.print("삭제할 사용자 아이디를 입력하세요: ");
    	String user_id = sc.nextLine();

    	UsersDAO usersDAO = new UsersDAO();
    	UsersVO user = usersDAO.selectOne(user_id);

    	if (user != null) {
    	    System.out.print("비밀번호를 입력하세요: ");
    	    String password = sc.nextLine();
    	    
    	    if (user.getPassword().equals(password)) {
    	        int result = usersDAO.delete(user_id);
    	        
    	        if (result > 0) {
    	            System.out.println("회원 정보가 성공적으로 삭제되었습니다.");
    	        } else {
    	            System.out.println("회원 정보 삭제에 실패하였습니다.");
    	        }
    	    } else {
    	        System.out.println("비밀번호가 일치하지 않습니다. 회원 정보 삭제를 취소합니다.");
    	    }
    	} else {
    	    System.out.println("해당 아이디의 사용자가 존재하지 않습니다.");
    	}

    	sc.close();
    }
}