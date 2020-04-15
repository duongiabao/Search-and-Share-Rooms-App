package HostModel;

import java.io.Serializable;

public class Host implements Serializable {
    private int Id;
    private String Name;
    private String Price;

    private String Person;
    private String Phone;
    private String Describe;
    private int LocateId;
    private int StyleId;
    private String Img;
    private  int UserId;

    public Host(int id, String name, String price, String person, String phone, String describe, int locateId, int styleId, String img, int userId) {
        Id = id;
        Name = name;
        Price = price;
        Person = person;
        Phone = phone;
        Describe = describe;
        LocateId = locateId;
        StyleId = styleId;
        Img = img;
        UserId = userId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }

    public int getLocateId() {
        return LocateId;
    }

    public void setLocateId(int locateId) {
        LocateId = locateId;
    }

    public int getStyleId() {
        return StyleId;
    }

    public void setStyleId(int styleId) {
        StyleId = styleId;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}

