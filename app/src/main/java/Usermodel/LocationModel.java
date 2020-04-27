package Usermodel;

public class LocationModel {
    int Id;
    String Location;
    String Image;

    public LocationModel(int id, String location, String image) {
        Id = id;
        Location = location;
        Image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
