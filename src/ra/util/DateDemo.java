package ra.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class DateDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào ngày sinh của bạn (dd/MM/yyyy): ");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date birthDate = sdf.parse(scanner.nextLine());
            //Convert từ java.util.date sang java.sql.date
            java.sql.Date birth = new java.sql.Date(birthDate.getTime());
            System.out.println("Dữ liệu birth-->"+birth);
        } catch (ParseException e) {
            System.err.println("Không đúng định dạng");
        }

    }
}
