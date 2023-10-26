package com.example.drawernavigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    // Khai báo các view đã tạo ở layout
    private RecyclerView room_rcv;
    private View mView;
    private EditText room_edt;
    private Button room_add, room_rmv;
    private MainActivity mMainActivity;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mMainActivity = (MainActivity) getActivity();

        // Ánh xạ view
        room_rcv = mView.findViewById(R.id.room_rcv);
        room_add = mView.findViewById(R.id.room_add);
        room_rmv = mView.findViewById(R.id.room_rmv);
        room_edt = mView.findViewById(R.id.room_edt);

        // Set layout cho recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(mMainActivity);
        room_rcv.setLayoutManager(layoutManager);

        // Khởi tạo adapter
        RoomAdapter roomAdapter = new RoomAdapter(Room.globalRooms, new RoomAdapter.RoomClickListener() {
            @Override
            public void onRoomClick(Room room, int position) {
                // hàm goToDeviceFragment có tham số gồm tên của đối tượng room và vị trí của item trong recyclerview
                // để truyền cho DeviceFragment
                mMainActivity.goToDeviceFragment(room.getName().toString(), position);
            }
        });
        // Set adapter đã tạo cho recyclerview
        room_rcv.setAdapter(roomAdapter);

        // tạo arraylist chứa các thiết bị mặc định cũa mỗi room
        ArrayList<Device> defaultDevices = new ArrayList<>();
        defaultDevices.add(new Device("Device 1", R.drawable.device_off,false));
        defaultDevices.add(new Device("Device 2", R.drawable.device_off,false));
        defaultDevices.add(new Device("Device 3", R.drawable.device_off,false));

        // gán listener cho button thêm room
        room_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Room.globalRooms.add(new Room(room_edt.getText().toString(),R.drawable.room ,defaultDevices));
                roomAdapter.notifyItemChanged(Room.globalRooms.size()-1);
                room_rcv.scrollToPosition(Room.globalRooms.size()-1);

            }
        });

        // gán listener cho button xóa room
        room_rmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String room_name = room_edt.getText().toString();
                for (int i = 0; i < Room.globalRooms.size(); i++){
                    Room room = Room.globalRooms.get(i);
                    if(room.getName().toString().equals(room_name)){
                        Room.globalRooms.remove(i);
                        roomAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        return mView;
    }
}