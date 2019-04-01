package com.example.cyber_net.e_kinerja.helper;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

public class FunctionError {

    //cek jika kosong
    public static boolean cekEditText(EditText editText) {
        if (TextUtils.isEmpty(editText.getText().toString())) {
            //true jika kosong
            return true;
        } else {
            //false jika ada isinya
            return false;
        }
    }

    public static boolean cekTextView(TextView textView) {
        if (TextUtils.isEmpty(textView.getText().toString())) {
            //true jika kosong
            return true;
        } else {
            //false jika ada isinya
            return false;
        }
    }

    //set Notif Error
    public static void setErrorEditText(EditText editText, String valueNotif) {
        editText.setError(valueNotif);
    }

    public static void kosongkan(EditText editText) {
        editText.setText("");
    }

    public static String getTextEditText(EditText editText) {
        return editText.getText().toString();
    }

    public static String getTextView(TextView textView) {
        return textView.getText().toString();
    }

    public static void setTextView(TextView textView, String value) {
        textView.setText(value);
    }

    public static void setTextEditText(EditText editText, String value) {
        editText.setText(value);
    }
}
