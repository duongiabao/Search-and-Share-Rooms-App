package Usermodel;

public class loaiPhong {
    public int Id;
    public String tenLoaiPhong;
    public String hinhAnhPhong;

    public loaiPhong(int id, String tenLoaiPhong, String hinhAnhPhong) {
        Id = id;
        this.tenLoaiPhong = tenLoaiPhong;
        this.hinhAnhPhong = hinhAnhPhong;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public String getHinhAnhPhong() {
        return hinhAnhPhong;
    }

    public void setHinhAnhPhong(String hinhAnhPhong) {
        this.hinhAnhPhong = hinhAnhPhong;
    }
}
