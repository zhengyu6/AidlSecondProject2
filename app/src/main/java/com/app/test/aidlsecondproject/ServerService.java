package com.app.test.aidlsecondproject;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by ${liumengqiang} on 2017/9/15.
 */

public class ServerService extends Service {
    String data="数据";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
    UserAidlInterface.Stub stub = new UserAidlInterface.Stub() {
        @Override
        public User getUser() throws RemoteException {
            int i = Integer.valueOf(data);
            return new User("您输入的是",i);
        }

        @Override
        public User testUserIn(User User) throws RemoteException {
            return null;
        }

        @Override
        public User testUserOut(User User) throws RemoteException {
            return null;
        }

        @Override
        public User testUserInOut(User user) throws RemoteException {
            return null;
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        data = intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);
    }
}
