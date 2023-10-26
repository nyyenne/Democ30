package com.example.drawernavigation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
// class Room có đối tượng chứa thuộc tính tên và list device tương ứng với từng đối tượng
// class Room implements Serializable để truyền dữ liệu
public class Room implements Serializable {
private String name;
private int img;
private List<Device> devices;

// tạo list chứa các đối tượng Room phạm vi public static để truy cập ở bất cứ đâu
public static List<Room> globalRooms = new ArrayList<Room>();

    // Constructor
    public Room(String name, int img, List<Device> devices) {
        this.name = name;
        this.img = img;
        this.devices = devices;
    }

    // Getter và Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return img;
    }

    public void setImage(int img) {
        this.img = img;
    }

    // phương thức trả về list device của từng đối tượng room
    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    // phương thức thêm device của từng đối tượng room
    public void addDevice(Device dv){
        devices.add(dv);
    }

    // phương thức xóa device của từng đối tượng room
    public void removeDevice(int position){
        devices.remove(position);
    }

    // phương thức trả về số lượng device của từng đối tượng room
    public int getNumDevice(){
        return devices.size();
    }
}
