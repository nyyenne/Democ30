package com.example.drawernavigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>{
    private Context mContext;
    private List<Room> rooms;
    private RoomClickListener roomClickListener;
    // tạo listener thông qua interface
    public interface RoomClickListener{
        // chứa phương thức onRoomClick
        void onRoomClick(Room room, int position);
    }

    // Constructor
    public RoomAdapter(List<Room> rooms, RoomClickListener roomClickListener) {
        this.rooms = rooms;
        this.roomClickListener = roomClickListener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout của device_item đã tạo vào view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent,false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = rooms.get(position); // lấy ra từng đối tượng room trong list
        int item_position = holder.getAdapterPosition(); // lấy vị trí item trong recyclerview
        holder.room_img.setImageResource(room.getImage()); // hiển thị ảnh
        holder.room_name.setText(room.getName()); //hiển thị tên
        holder.room_device.setText("Device: "+ room.getNumDevice()); // hiển thị số device
        // gán listener bắt sự kiện khi nhấn vào room
        holder.room_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //phương thức onRoomClick trả về đối tượng tương ứng với vị trí của item trong recyclerview
                roomClickListener.onRoomClick(room, item_position);
            }
        });
    }

    // phương thức trả về số lượng room
    @Override
    public int getItemCount() {
        return rooms.size();
    }

    // khởi tạo viewholder kế thừa từ RecyclerView.ViewHolder để truyền vào RecyclerView.Adapter
    public class RoomViewHolder extends RecyclerView.ViewHolder {
        // khai báo các view đã tạo ở layout
        ImageView room_img;
        TextView room_name, room_device;
        CardView room_layout;
        // ánh xạ các view
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            room_layout = itemView.findViewById(R.id.room_layout);
            room_img = itemView.findViewById(R.id.room_img);
            room_name = itemView.findViewById(R.id.room_name);
            room_device = itemView.findViewById(R.id.room_device);
        }
    }
}
