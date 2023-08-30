package pay.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import pay.vo.Pay_VO;

public class PayTEST {
    public static void main(String[] args) {
        PayDao payDao = new PayDao();
        
        // SELECTALL
        List<Pay_VO> payments = payDao.selectAll();
        for (Pay_VO payment : payments) {
            System.out.println(payment);
        }
        
        System.out.println();
        
        
        // INSERT
        int paymentId = 1001;
        String userId = "user123";
        String paymentMethod = "Credit Card";
        int totalPrice = 50000;
        String paymentDate = generateRandomDate().toString(); // LocalDate를 String으로 변환
        int reservationId = 5001;

        Pay_VO newPayment = new Pay_VO(paymentId, userId, paymentMethod, totalPrice, paymentDate, reservationId);
        int result = payDao.insert(newPayment);
        if (result > 0) {
            System.out.println("삽입 성공");
        } else {
            System.out.println("삽입 실패");
        }
    
//        // UPDATE
//        int updatedTotalPrice = 55000;
//        result = payDao.update(newPayment); // update 메서드로 수정
//        if (result > 0) {
//            System.out.println("업데이트 성공");
//        } else {
//            System.out.println("업데이트 실패");
//        }
//
        // DELETE
//        result = payDao.delete(1001); // 결제 ID를 매개변수로 수정
//        if (result > 0) {
//            System.out.println("삭제 성공");
//        } else {
//            System.out.println("삭제 실패");
//        }

    }
    private static LocalDate generateRandomDate() {
        // 8월 1일부터 8월 30일 사이에서 랜덤한 날짜 생성
        LocalDate startDate = LocalDate.of(2023, 8, 1);
        LocalDate endDate = LocalDate.of(2023, 8, 30);

        long startDay = startDate.toEpochDay();
        long endDay = endDate.toEpochDay();
        
        long randomDay = ThreadLocalRandom.current().nextLong(startDay, endDay + 1);
        return LocalDate.ofEpochDay(randomDay);
    }
}
