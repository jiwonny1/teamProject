package com.project.users.dao;

import com.project.users.vo.UsersVO;
import com.project.users.vo.PointVO;

public class UsersDAOTest {

    public static void main(String[] args) {
        UsersDAO userDao = new UsersDAO();
        PointDAO pointDao = new PointDAO();

        String user_id = "GKAJT";

        // 사용자 정보 조회
        UsersVO user = userDao.selectOne(user_id);
        System.out.println("User: " + user);

        // 포인트 적립
        int purchasedAmount = 10000; // 구매 가격
        int accumulatedPoints = purchasedAmount / 20; // 5% 적립
        int result = pointDao.accumulatePoints(user_id, accumulatedPoints);
        if (result > 0) {
            System.out.println(accumulatedPoints + " 포인트가 적립되었습니다.");
        } else {
            System.out.println("포인트 적립에 실패했습니다.");
        }

        // 사용자의 포인트 정보 조회
        PointVO pointVO = pointDao.selectOne(user_id);
        System.out.println("PointVO: " + pointVO);

        // 포인트 사용
        int usedPoints = 500; // 차감할 포인트
        result = pointDao.usePoints(user_id, usedPoints);
        if (result > 0) {
            System.out.println(usedPoints + " 포인트가 사용되었습니다.");
        } else {
            System.out.println("포인트 사용에 실패했습니다.");
        }

        // 사용자의 포인트 정보 다시 조회
        pointVO = pointDao.selectOne(user_id);
        System.out.println("PointVO after using: " + pointVO);
    }
}
