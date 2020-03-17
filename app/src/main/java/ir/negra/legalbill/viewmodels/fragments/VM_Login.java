package ir.negra.legalbill.viewmodels.fragments;

import android.content.Context;
import android.os.Handler;

import io.reactivex.subjects.PublishSubject;
import ir.negra.legalbill.models.MD_Login;
import ir.negra.legalbill.utilities.StaticFunctions;

public class VM_Login {

    private Context context;
    private PublishSubject<String> publishSubject;

    public VM_Login(Context context) {//____________________________________________________________ Start VM_Login
        this.context = context;
        publishSubject = PublishSubject.create();
    }//_____________________________________________________________________________________________ End VM_Login

    public void ConnectForLogin(MD_Login login) {//_________________________________________________ Start ConnectForLogin

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!StaticFunctions.isCancel)
                    publishSubject.onNext("successfully");
            }
        }, 3000);

    }//_____________________________________________________________________________________________ End ConnectForLogin


    public PublishSubject<String> getPublishSubject() {//___________________________________________ Start getPublishSubject
        return publishSubject;
    }//_____________________________________________________________________________________________ End getPublishSubject
}
