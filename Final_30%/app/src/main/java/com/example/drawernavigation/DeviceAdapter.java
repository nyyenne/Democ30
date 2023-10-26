package com.example.drawernavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>{
    private Context mcontext;
    private List<Device> devices; // list chứa các đối tượng Device

    // Constructor
    public DeviceAdapter(List<Device> devices) {
        this.devices = devices;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout của device_item đã tạo vào view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_item, parent,false);
        return new DeviceViewHolder(view);
    }

    // Đổ các view ra viewholder
    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
        Device device = devices.get(position); // lấy ra từng đối tượng device trong list
        holder.device_img.setImageResource(device.getImage());
        holder.device_name.setText(device.getName()); // hiển thị tên
        holder.swt.setChecked(device.getStatus()); // hiển thị trạng thái switch
        holder.seek.setEnabled(device.getStatus()); // hiển thị trạng thái seekbar
        // gán listener cho switch để cập nhật trạng thái
        holder.swt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                device.setStatus(b); // phương thức setStatus để cập nhật trạng thái của device khi trạng thái switch thay đổi
                holder.device_img.setImageResource(device.setImage(b)); // hiển thị hình theo trạng thái switch bằng phương thức setImage
                holder.seek.setEnabled(b);

                // Nếu Switch được bật, điều khiển độ trong suốt của hình ảnh
                if (b){
                    holder.seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                            // Cài đặt độ trong suốt của ảnh device dựa trên giá trị của SeekBar
                            holder.device_img.setAlpha(i);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });
                }
            }
        });


    }

    // phương thức trả về số lượng device
    @Override
    public int getItemCount() {
        return devices.size();
    }

    // khởi tạo viewholder kế thừa từ RecyclerView.ViewHolder để truyền vào RecyclerView.Adapter
    public class DeviceViewHolder extends RecyclerView.ViewHolder {
        // khai báo các view đã tạo ở layout
        ImageView device_img;
        TextView device_name;
        Switch swt;
        SeekBar seek;
        // ánh xạ các view
        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);
            device_img = itemView.findViewById(R.id.device_img);
            device_name = itemView.findViewById(R.id.device_name);
            swt = itemView.findViewById(R.id.swt);
            seek = itemView.findViewById(R.id.seek);
        }
    }
}
