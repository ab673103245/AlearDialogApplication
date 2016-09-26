package qianfeng.aleardialogapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String[] items= new String[]{"Android","IOS","HTML5"};
    private boolean[] is = new boolean[items.length];
    private EditText et_username;
    private EditText et_password;
    private AlertDialog alertDialog;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick1(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("google-my:", "onClick: " + items[which]);
            }
        }).setTitle("选择你要的项目");

        builder.create().show();





    }

    public void onClick2(View view) {// 单选
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() { // -1：表示预选的项是没有选中的项的
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("google-my:", "onClick: " + items[which]);
                dialog.dismiss(); // dialog：是当前显示的对话框，利用它来调用dismiss(),就是关闭对话框
            }
        }).setTitle("单选");

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false); // 在对话框以外的地方点击，不能取消单选对话框
        alertDialog.show();
    }

    public void onClick3(View view) {  // 多选

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMultiChoiceItems(items, is, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    is[which] = isChecked; // dialog：是当前显示的对话框，
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < is.length; i++) {
                    if(is[i])
                    {
                        Log.d("google-my:", "onClick: " + items[i]);
                    }
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    public void onClick4(View view) {  // 自定义View对话框

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.login, null);
        builder.setView(inflate);
        et_username = ((EditText) inflate.findViewById(R.id.et_username));
        et_password = ((EditText) inflate.findViewById(R.id.et_password));

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void submit(View view) { // 提交信息
        Log.d("google-my:", "submit: 用户名：" +et_username.getText().toString()+";密码："+et_password.getText().toString() );
        alertDialog.dismiss();
    
    }

    public void open(View view) {  // 打开进度条
        progressDialog = new ProgressDialog(this);

        // 我想打开进度条的时候，不能让用户点击后退按钮
//        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
//            @Override
//            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//
//                if(keyCode == KeyEvent.KEYCODE_BACK)
//                {
//                    return true;
//                }
//                return false;
//            }
//        });


        progressDialog.show();

    }

    @Override
    public void onBackPressed() // 只能屏蔽Activity的Back按键，不能屏蔽其他上下文的Back按键
    {
        if(progressDialog.isShowing())
        {

        }else
        {
            super.onBackPressed();
        }
    }
    public void close(View view) {  // 关闭进度条
        progressDialog.dismiss();
    }
}
