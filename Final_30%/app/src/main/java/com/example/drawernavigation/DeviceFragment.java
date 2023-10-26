package com.example.drawernavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DeviceFragment extends Fragment {
    public static final String TAG = DeviceFragment.class.getName(); //tạo hằng TAG để xác định DeviceFragment đã tạo
    // Khai báo các view đã tạo ở layout
    private RecyclerView device_rcv;
    private TextView room_name_1;
    private EditText device_edt;
    private Button device_add, device_rmv;
    private ImageButton btn_back;
    private View mView;
    private MainActivity mMainActivity;
    public DeviceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_device, container, false);
        mMainActivity = (MainActivity) getActivity();

        // Ánh xạ view
        device_rcv = mView.findViewById(R.id.device_rcv);
        room_name_1 = mView.findViewById(R.id.room_name_1);
        device_add = mView.findViewById(R.id.device_add);
        device_edt = mView.findViewById(R.id.device_edt);
        device_rmv = mView.findViewById(R.id.device_rmv);
        btn_back = mView.findViewById(R.id.btn_back);

        // Set layout cho recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(mMainActivity);
        device_rcv.setLayoutManager(layoutManager);

        // Nhận dữ liệu từ HomeFragment gồm NameRoom và thứ tự Room tương ứng
        Bundle bundleReceive = getArguments(); // khởi tạo bundle nhận dữ liệu truyền từ HomeFragment
        int index = bundleReceive.getInt("index"); //lưu thứ tự Room vào biến index
        String NameRoomDV = bundleReceive.getString("NameRoom"); //lưu tên phòng vào biến NameRoomDV

        // Hiển thị tên phòng đã chọn
        room_name_1.setText(NameRoomDV + " Devices List");

        // Khởi tạo adapter với tham số truyền vào là list device tương ứng với phòng được chọn
        DeviceAdapter deviceAdapter = new DeviceAdapter(Room.globalRooms.get(index).getDevices());

        // Set adapter đã tạo cho recyclerview
        device_rcv.setAdapter(deviceAdapter);

        // gán listener cho button thêm device
        device_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // gọi phương thức addDevice của đối tượng Room để thêm device với tên lấy từ editText và trạng thái switch là false
                Room.globalRooms.get(index).addDevice(new Device(device_edt.getText().toString(), R.drawable.device_off,false));
                deviceAdapter.notifyItemChanged(Room.globalRooms.get(index).getDevices().size()-1);
                device_rcv.scrollToPosition(Room.globalRooms.get(index).getDevices().size()-1);
            }
        });

        // gán listener cho button xóa device
        device_rmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lưu tên device muốn xóa vào device_name
                String device_name = device_edt.getText().toString();
                // kiểm tra device nào có tên trùng với device_name
                for (int i = 0; i < Room.globalRooms.get(index).getNumDevice(); i++){
                    if(Room.globalRooms.get(index).getDevices().get(i).getName().toString().equals(device_name)){
                        // xóa device bằng phương thức removeDevice
                        Room.globalRooms.get(index).removeDevice(i);
                        deviceAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        // gán listener cho button back để trở về HomeFragment bằng hàm popBackStack()
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager() != null)
                    getFragmentManager().popBackStack();
            }
        });

        return mView;
    }
}