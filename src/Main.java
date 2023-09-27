import ra.presentation.CategoriesMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***************SHOP MANAGEMENT*************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Báo cáo thống kê");
            System.out.println("4. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    CategoriesMenu.displayMenuCategories(scanner);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn 1-4");
            }
        }while (true);
    }
}