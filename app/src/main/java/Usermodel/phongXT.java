package Usermodel;

//khuon

import java.io.Serializable;

public class phongXT implements Serializable {
    public int id;
    public String home_name;
    public Integer home_price;
    public String home_person;
    public String home_phone;
    public String home_describe;
    public int location_id;
    public String home_img;
    public int idPhong;

    public phongXT(int id, String home_name, Integer home_price, String home_person, String home_phone, String home_describe, int location_id, String home_img, int idPhong) {
        this.id = id;
        this.home_name = home_name;
        this.home_price = home_price;
        this.home_person = home_person;
        this.home_phone = home_phone;
        this.home_describe = home_describe;
        this.location_id = location_id;
        this.home_img = home_img;
        this.idPhong = idPhong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHome_name() {
        return home_name;
    }

    public void setHome_name(String home_name) {
        this.home_name = home_name;
    }

    public Integer getHome_price() {
        return home_price;
    }

    public void setHome_price(Integer home_price) {
        this.home_price = home_price;
    }

    public String getHome_person() {
        return home_person;
    }

    public void setHome_person(String home_person) {
        this.home_person = home_person;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    public String getHome_describe() {
        return home_describe;
    }

    public void setHome_describe(String home_describe) {
        this.home_describe = home_describe;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getHome_img() {
        return home_img;
    }

    public void setHome_img(String home_img) {
        this.home_img = home_img;
    }

    public int getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(int idPhong) {
        this.idPhong = idPhong;
    }
}
