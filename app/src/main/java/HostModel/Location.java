package HostModel;

public class Location {
    int Id;
    String Location;


    public Location(int id, String location) {
        Id = id;
        Location = location;
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


}