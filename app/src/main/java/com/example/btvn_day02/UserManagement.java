package com.example.btvn_day02;
import android.content.Context;
import android.content.SharedPreferences;
public class UserManagement {
    private final SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "user_prefs";

    public UserManagement(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean registerUser(String username, String password) {
        // Kiểm tra xem tài khoản đã tồn tại chưa
        if (sharedPreferences.contains(username)) {
            return false; // Tài khoản đã tồn tại
        }

        // Lưu thông tin tài khoản vào SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username, password);
        editor.apply();

        return true; // Đăng ký thành công
    }

    public boolean loginUser(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false; // Tên người dùng hoặc mật khẩu trống
        }
        // Kiểm tra xem tài khoản có tồn tại không và mật khẩu có đúng không
        if (!sharedPreferences.contains(username)) {
            return false; // Tài khoản không tồn tại
        }
        String savedPassword = sharedPreferences.getString(username, "");
        return savedPassword.equals(password);
    }
}