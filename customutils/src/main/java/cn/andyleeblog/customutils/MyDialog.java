package cn.andyleeblog.customutils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 自定义dialog
 * author : andy.lee Created on 17-5-12
 * email : andy645928714@gmail.com
 */

public class MyDialog extends Dialog {
    private Button btnYes;
    private Button btnNo;
    private String mTitle;
    private String mMessage;
    private String mYesStr;
    private String mNoStr;

    private OnPositiveClickListener mYesListener;
    private OnNegativeClickListener mNoListener;

    private MyDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
    }

    private MyDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);
        initView();
        initEvent();
    }


    private void initView() {
        btnYes = (Button) findViewById(R.id.dialog_yes);
        btnNo = (Button) findViewById(R.id.dialog_no);
        TextView tvMessage = (TextView) findViewById(R.id.dialog_message);
        TextView tvTitle = (TextView) findViewById(R.id.dialog_title);

        if (mYesStr != null) {
            btnYes.setText(mYesStr);
        }
        if (mNoStr != null) {
            btnNo.setText(mNoStr);
        }
        if (mTitle != null) {
            tvTitle.setText(mTitle);
        }
        if (mMessage != null) {
            tvMessage.setText(mMessage);
        }

    }

    private void initEvent() {
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mYesListener != null) {
                    mYesListener.onClick();
                    dismiss();
                }
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNoListener != null) {
                    mNoListener.onClick();
                    dismiss();
                }
            }
        });


    }

    public interface OnPositiveClickListener {
        void onClick();
    }

    public interface OnNegativeClickListener {
        void onClick();
    }


    public static class Builder {
        private MyDialog dialog;

        public Builder(@NonNull Context context) {
            dialog = new MyDialog(context);
        }

        public Builder(@NonNull Context context, @StyleRes int themeResId) {
            dialog = new MyDialog(context, themeResId);
        }

        public Builder setTitle(@NonNull String title) {
            dialog.mTitle = title;
            return this;
        }

        public Builder setMessage(@NonNull String message) {
            dialog.mMessage = message;
            return this;
        }

        public Builder setPositiveButton(@NonNull String yesStr, OnPositiveClickListener listener) {
            dialog.mYesStr = yesStr;
            dialog.mYesListener = listener;
            return this;
        }

        public Builder setNegativeButton(@NonNull String noStr, OnNegativeClickListener listener) {
            dialog.mNoStr = noStr;
            dialog.mNoListener = listener;
            return this;
        }

        public Builder setCancelable(boolean flag) {
            dialog.setCancelable(flag);
            return this;
        }

        public MyDialog create() {
            return dialog;
        }

        public MyDialog show() {
            MyDialog dialog = create();
            dialog.show();
            return dialog;
        }

    }
}
