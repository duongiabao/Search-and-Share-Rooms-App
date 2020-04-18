package Usermodel;

public class TienNghi {
    public String tenTiennghi;
    public String hinhAnhTienNghi;

    public TienNghi(String tenTiennghi, String hinhAnhTienNghi) {
        this.tenTiennghi = tenTiennghi;
        this.hinhAnhTienNghi = hinhAnhTienNghi;
    }

    public String getTenTiennghi() {
        return tenTiennghi;
    }

    public void setTenTiennghi(String tenTiennghi) {
        this.tenTiennghi = tenTiennghi;
    }

    public String getHinhAnhTienNghi() {
        return hinhAnhTienNghi;
    }

    public void setHinhAnhTienNghi(String hinhAnhTienNghi) {
        this.hinhAnhTienNghi = hinhAnhTienNghi;
    }
}
