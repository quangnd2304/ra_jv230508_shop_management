package ra.entity;

import java.util.Scanner;

public class Categories {
    private int catalogId;
    private String catalogName;
    private int priority;
    private boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, int priority, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.priority = priority;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void displayData() {
        System.out.printf("Mã danh mục: %d - Tên danh mục: %s - Độ ưu tiên: %d - Trạng thái: %s\n",
                this.catalogId, this.catalogName,
                this.priority, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
    }

    public void inputData(Scanner scanner){
        System.out.println("Nhập vào tên sản phẩm:");
        this.catalogName = scanner.nextLine();
        System.out.println("Nhập vào độ ưu tiên của danh mục:");
        this.priority = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập vào trạng thái danh mục:");
        this.catalogStatus = Boolean.parseBoolean(scanner.nextLine());
    }

}
