package com.nyzc.gdm.currencyratio.uipacakage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nyzc.gdm.currencyratio.Base.AppLocalWalletUser;
import com.nyzc.gdm.currencyratio.Base.BaseActivity;
import com.nyzc.gdm.currencyratio.Base.MyApp;
import com.nyzc.gdm.currencyratio.Bean.FileSave;
import com.nyzc.gdm.currencyratio.R;
import com.nyzc.gdm.currencyratio.View.CustomTitleBar;

import butterknife.BindView;

public class SetPwdActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.custom_title)
    CustomTitleBar custom_title;
    EditText edit_icurrent_wallet_pwd, edit_new__wallet_pwd, edit_new__wallet_pwd_again;
    TextView at_once_enter, tv_commit;


    public static void start(Context context) {
        Intent starter = new Intent(context, SetPwdActivity.class);
        context.startActivity(starter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_pwd;
    }

    @Override
    protected void init() {
        edit_icurrent_wallet_pwd = findViewById(R.id.edit_icurrent_wallet_pwd);
        edit_new__wallet_pwd = findViewById(R.id.edit_new__wallet_pwd);
        edit_new__wallet_pwd_again = findViewById(R.id.edit_new__wallet_pwd_again);
        at_once_enter = findViewById(R.id.at_once_enter);
        tv_commit = findViewById(R.id.tv_commit);
        tv_commit.setOnClickListener(this);
        at_once_enter.setOnClickListener(this);
        custom_title.setOnTitleClickListener(new CustomTitleBar.TitleOnClickListener() {
            @Override
            public void onLeftClick() {
                finish();
            }
            @Override
            public void onRightClick() {
            }
        });
    }

    String current_pwd;
    String new_pwd;
    String new_again_ped;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.at_once_enter:
                ImportWalletActivity.start(SetPwdActivity.this);
                break;
            case R.id.tv_commit:
                current_pwd = edit_icurrent_wallet_pwd.getText().toString().trim();
                new_pwd = edit_new__wallet_pwd.getText().toString().trim();
                new_again_ped = edit_new__wallet_pwd_again.getText().toString().trim();
                if (TextUtils.isEmpty(current_pwd)) {
                    Toast.makeText(this, "请输入当前密码", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (!current_pwd.equals(MyApp.localWalletUser.getLocalPwd())) {
                        Toast.makeText(this, "当前密码输入错误", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (TextUtils.isEmpty(new_pwd)) {
                    Toast.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (new_pwd.length() < 8) {
                        Toast.makeText(this, "密码请输入不少于8位字符", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        if (new_pwd.equals(current_pwd)) {
                            Toast.makeText(this, "当前密码不可以和新密码一致", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
                if (TextUtils.isEmpty(new_again_ped)) {
                    Toast.makeText(this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    if (!new_pwd.equals(new_again_ped)) {
                        Toast.makeText(this, "新输入密码不一致", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }


                MyApp.localWalletUser.setLocalPwd(new_pwd);
                FileSave.write(SetPwdActivity.this, MyApp.localWalletUser, "localwallet");
                MyApp.localWalletUser = (AppLocalWalletUser) FileSave.read(SetPwdActivity.this, "localwallet");
                finish();
                break;
        }
    }
}
