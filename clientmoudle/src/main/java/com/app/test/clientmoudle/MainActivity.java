package com.app.test.clientmoudle;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.app.test.aidlsecondproject.User;
import com.app.test.aidlsecondproject.UserAidlInterface;
public class MainActivity extends AppCompatActivity {
    // 默认组件名
    private ComponentName defaultComponentName;
    // 健客医生组件名
    private ComponentName doctorComponentName;
    // 网上药店组件名
    private ComponentName jkmallComponentName;
    private TextView textView;
    private Button btn_huifu;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_huifu = (Button) findViewById(R.id.btn_huifu);
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               if (userAidlInterface != null) {
                   try {
                       User user = userAidlInterface.getUser();
                       if (user == null) {
                           return;
                       }
                       if (user.getAge() == 1008611) {
                           textView.setText("数据" + user.getName() + " " + user.getAge());
                           Toast.makeText(MainActivity.this,"更换中.请稍后",Toast.LENGTH_SHORT).show();
                           changeDoctorIcon();
                       }else{
                           Toast.makeText(MainActivity.this,"输入错误",Toast.LENGTH_SHORT).show();
                       }
                   } catch (RemoteException e) {
                       e.printStackTrace();
                   }
               }
           }
       },3000);
        // init ComponentName
        defaultComponentName = getComponentName();
        doctorComponentName = DynamicChangeIcon.getInstance().getComponentName(getBaseContext(),
                "cn.jianke.dynamicchangeicon.doctor");
        jkmallComponentName = DynamicChangeIcon.getInstance().getComponentName(getBaseContext(),
                "cn.jianke.dynamicchangeicon.jkmall");
        // onClick
        textView = (TextView) findViewById(R.id.textView);
        btn_huifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"恢复中.请稍后",Toast.LENGTH_SHORT).show();
                changeJkmallIcon();
            }
        });
        Intent intent = new Intent();
        intent.setAction("android.second.aidl");
        intent.setPackage("com.app.test.aidlsecondproject");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    private UserAidlInterface userAidlInterface;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            userAidlInterface = UserAidlInterface.Stub.asInterface(service);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };
    /**
     * 更改为健客医生app图标
     * @author leibing
     * @createTime 2016/12/22
     * @lastModify 2016/12/22
     * @param
     * @return
     */
    private void changeDoctorIcon(){
        DynamicChangeIcon.getInstance().disableComponent(defaultComponentName);
        DynamicChangeIcon.getInstance().disableComponent(jkmallComponentName);
        DynamicChangeIcon.getInstance().enableComponent(doctorComponentName);
    }
    /**
     * 更改为网上药店app图标
     * @author leibing
     * @createTime 2016/12/22
     * @lastModify 2016/12/22
     * @param
     * @return
     */
    private void changeJkmallIcon(){
        DynamicChangeIcon.getInstance().disableComponent(defaultComponentName);
        DynamicChangeIcon.getInstance().enableComponent(jkmallComponentName);
        DynamicChangeIcon.getInstance().disableComponent(doctorComponentName);
    }
    /**
     * 更改为默认app图标
     * @author leibing
     * @createTime 2016/12/22
     * @lastModify 2016/12/22
     * @param
     * @return
     */
    private void changeDefaultIcon(){
        DynamicChangeIcon.getInstance().enableComponent(defaultComponentName);
        DynamicChangeIcon.getInstance().disableComponent(jkmallComponentName);
        DynamicChangeIcon.getInstance().disableComponent(doctorComponentName);
    }


}
