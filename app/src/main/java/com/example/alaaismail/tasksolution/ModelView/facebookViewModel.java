package com.example.alaaismail.tasksolution.ModelView;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.example.alaaismail.tasksolution.Model.userSession;

public class facebookViewModel extends ViewModel {




    public void RemmberLogin(Context context, String name , String first_name, String last_name){
        userSession userSession = new userSession(context);
        userSession.loginUser(name, true, first_name, last_name);
    }
    public void forgetLogin(Context context,String name , String first_name, String last_name){
        userSession userSession = new userSession(context);

        userSession.loginUser(name, false, first_name, last_name);
    } public void logout(Context context){

        userSession userSession = new userSession(context);
        userSession.loginUser("", false, "", "");
    }


}
