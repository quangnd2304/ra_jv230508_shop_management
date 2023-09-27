package ra.presentation;

import ra.bussiness.CategoriesBussiness;
import ra.entity.Categories;

import java.util.List;
import java.util.Scanner;

public class CategoriesMenu {
    public static void displayMenuCategories(Scanner scanner){
        boolean isExit = true;
        do {
            System.out.println("****************CATEGORIES MANAGEMENT************");
            System.out.println("1. Hiển thị danh mục");
            System.out.println("2. Hiển thị danh mục sắp xếp theo độ ưu tiên tăng dần");
            System.out.println("3. Thêm mới danh mục");
            System.out.println("4. Cập nhật danh mục");
            System.out.println("5. Tìm kiếm danh mục theo tên");
            System.out.println("6. Cập nhật trạng thái danh mục");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    displayListCategories();
                    break;
                case 2:
                    displayListCategoriesSort();
                    break;
                case 3:
                    createNewCatalog(scanner);
                    break;
                case 4:
                    updateCatalog(scanner);
                    break;
                case 5:
                    searchCatalogByName(scanner);
                    break;
                case 6:
                    updateCatalogStatus(scanner);
                    break;
                case 7:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng nhập từ 1-7");
            }
        }while (isExit);
    }

    public static void displayListCategories(){
        //Gọi sang bussiness lấy tất cả danh mục
        List<Categories> listCategories = CategoriesBussiness.getAllCategories();
        //Hiển thị các danh mục
        listCategories.stream().forEach(catalog->catalog.displayData());
    }

    public static void displayListCategoriesSort(){
        //Gọi sang bussiness lấy tất cả danh mục
        List<Categories> listCategories = CategoriesBussiness.getAllCategoriesSort();
        //Hiển thị các danh mục
        listCategories.stream().forEach(catalog->catalog.displayData());
    }

    public static void createNewCatalog(Scanner scanner){
        //1. Nhập dữ liệu danh mục mới
        Categories catalog = new Categories();
        catalog.inputData(scanner);
        //2. Gọi sang bussiness thực hiện thêm mới
        boolean result = CategoriesBussiness.createCatalog(catalog);
        //3. Hiển thị kết quả
        if (result){
            System.out.println("Thêm mới thành công");
        }else{
            System.err.println("Có lỗi trong quá trình xử lý");
        }
    }

    public static void updateCatalog(Scanner scanner){
        System.out.println("Nhập vào mã danh mục cần cập nhật:");
        int catalogId = Integer.parseInt(scanner.nextLine());
        //Nhập dữ liệu cho danh mục cần cập nhật
        Categories catalog = new Categories();
        catalog.inputData(scanner);
        catalog.setCatalogId(catalogId);
        //Gọi sang bussiness thực hiện gọi procedure
        boolean result = CategoriesBussiness.updateCategories(catalog);
        //Hiển thị kết quả
        if (result){
            System.out.println("Thêm mới thành công");
        }else{
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static void searchCatalogByName(Scanner scanner){
        System.out.println("Nhập vào tên danh mục cần tìm:");
        String catalogName = scanner.nextLine();
        //Gọi bussiness
        List<Categories> listCatalog = CategoriesBussiness.searchCategorieByName(catalogName);
        //Hiển thị kết quả
        listCatalog.stream().forEach(catalog->catalog.displayData());
    }

    public static void updateCatalogStatus(Scanner scanner){
        System.out.println("Nhập vào mã danh mục cần cập nhật");
        int catalogId = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập vào trạng thái cập nhật:");
        boolean status = Boolean.parseBoolean(scanner.nextLine());
        boolean result=CategoriesBussiness.updateCategoriesStatus(catalogId,status);
        if (result){
            System.out.println("Cập nhật trạng thái thành công");
        }else{
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    //1. lấy data từ sql (java.sql.date) --> java.util.date: lấy về từ CSDL chuyển tự động
    //2. set dữ liệu vào database: java.util.Date --> java.sql.Date tự chuyển

}
