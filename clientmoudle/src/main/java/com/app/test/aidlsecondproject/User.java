package com.app.test.aidlsecondproject;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ${liumengqiang} on 2017/9/15.
 */

public class User implements Parcelable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public User(){

    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected User(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(age);
    }
    /**
     * 参数是一个Parcel,用它来存储与传输数据
     * @param parcel
     */
    public void readFromParcel(Parcel parcel) {
        //注意，此处的读值顺序应当是和writeToParcel()方法中一致的
        name = parcel.readString();
        age = parcel.readInt();
    }
}