package com.example.drawernavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lệnh thêm vào để khi keyboard ảo pop-up sẽ không đẩy recylcerview lên trên
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Khởi tạo fragmentTransaction để thêm Fragment vào Activity và quản lý thao tác với Fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //thêm một HomeFragment vào Activity ở phần tử có id content_frame trong layout của MainActivity
        fragmentTransaction.add(R.id.containers, new HomeFragment());
        //hiển thị Fragment được thêm vào trên giao diện
        fragmentTransaction.commit();

        // Ánh xạ view
        drawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView navView = findViewById(R.id.nav_view);

        // Thiết lập Toolbar hoạt động như ActionBar
        setSupportActionBar(toolbar);

        // Khởi tạo Drawer Toggle để đóng mở Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // gán cho navigationview listener bắt sự kiện khi chọn các mục trong menu drawer
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Gán id của mục đươợc chọn vào biến id
                int id = item.getItemId();
                // Kiểm tra id và chuyển đổi sang fragment tương ứng với id
                if (id==R.id.home){
                    transaction.replace(R.id.containers, new HomeFragment());
                    transaction.commit();
                } else if (id==R.id.setting) {
                    transaction.replace(R.id.containers, new SettingFragment());
                    transaction.commit();
                }
                // đóng drawer sau khi đã chọn mục home hoặc setting bất kì
                drawerLayout.closeDrawer(GravityCompat.START);
                // nếu chọn mục logout sẽ thoát app
                if (id==R.id.logout){
                    System.exit(0);
                }
                return true;
            }
        });
    }


    // Hàm được gọi khi nhấn vào Room bất kỳ (tham số truyền vào gồm tên Room và thứ tự của Room)
    // chuyển từ Fragment từ Room sang Device
    public void goToDeviceFragment(String nameRoom, int index) {

        //khởi tạo một fragmentTransaction để thêm Fragment vào Activity và quản lý thao tác với Fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        DeviceFragment deviceFragment = new DeviceFragment(); //tạo DeviceFragment mới

        // khơ tạo Bundle để chuyển dữ liệu từ HomeFragment sang DeviceFragment
        Bundle bundle = new Bundle();
        bundle.putString("NameRoom", nameRoom);
        bundle.putInt("index", index);
        //chuyển dữ liệu gồm tên phòng (key:NameRoom) và vị trí của phòng (key:index)
        deviceFragment.setArguments(bundle); //đặt Bundle chứa dữ liệu vào DeviceFragment để DeviceFragment có thể truy cập và sử dụng dữ liệu này sau khi được tạo

        fragmentTransaction.replace(R.id.containers, deviceFragment); //thay thế HomeFragment bằng DeviceFragment vào layout content_frame
        fragmentTransaction.addToBackStack(DeviceFragment.TAG); //thêm DeviceFragment vào backstack để quay lại HomeFragment
        fragmentTransaction.commit(); //thực hiện chuyển từ HomeFragment sang DeviceFragment
    }
}