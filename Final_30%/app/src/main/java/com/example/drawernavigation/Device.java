package com.example.drawernavigation;

// class Device có đối tượng chứa thuộc tính tên và trạng thái device
public class Device {
    private String name;
    private int img;
    private boolean status;
    // Constructor
    public Device(String name, int img, boolean status) {
        this.name = name;
        this.img = img;
        this.status = status;
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

    // phương thức hiển thị hình ảnh ứng với trạng thái switch có ngõ vào là trạng thái
    public int setImage(boolean i) {
        if (i == true){
            this.img = R.drawable.device_on;
        }
        else {
            this.img = R.drawable.device_off;
        }
        return img;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
