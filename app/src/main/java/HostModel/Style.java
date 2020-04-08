package HostModel;

public class Style {
    int Id;
    String styleHome;
    String styleImg;

    public Style(int id, String styleHome, String styleImg) {
        Id = id;
        this.styleHome = styleHome;
        this.styleImg = styleImg;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getStyleHome() {
        return styleHome;
    }

    public void setStyleHome(String styleHome) {
        this.styleHome = styleHome;
    }

    public String getStyleImg() {
        return styleImg;
    }

    public void setStyleImg(String styleImg) {
        this.styleImg = styleImg;
    }
}
