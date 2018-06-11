// UserAidlInterface.aidl
package com.app.test.aidlsecondproject;

// Declare any non-default types here with import statements

import com.app.test.aidlsecondproject.User;

interface UserAidlInterface {
    User getUser();
    User testUserIn(in User User);
    User testUserOut(out User User);
    User testUserInOut(inout User user);
}

