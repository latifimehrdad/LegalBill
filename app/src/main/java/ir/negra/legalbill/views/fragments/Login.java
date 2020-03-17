package ir.negra.legalbill.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cunoraz.gifview.library.GifView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import ir.negra.legalbill.R;
import ir.negra.legalbill.databinding.FragmentLoginBinding;
import ir.negra.legalbill.models.MD_Login;
import ir.negra.legalbill.utilities.StaticFunctions;
import ir.negra.legalbill.viewmodels.fragments.VM_Login;

import static ir.negra.legalbill.utilities.StaticFunctions.TextChangeForChangeBack;

public class Login extends Fragment {

    private Context context;
    private VM_Login vm_login;
    private View view;
    private DisposableObserver<String> disposableObserver;

    @BindView(R.id.RelativeLayoutLogin)
    RelativeLayout RelativeLayoutLogin;

    @BindView(R.id.EditTextNationalCode)
    EditText EditTextNationalCode;

    @BindView(R.id.EditTextAccountNumber)
    EditText EditTextAccountNumber;

    @BindView(R.id.ImageViewLogin)
    ImageView ImageViewLogin;

    @BindView(R.id.TextViewLogin)
    TextView TextViewLogin;

    @BindView(R.id.GifViewLogin)
    GifView GifViewLogin;

    @BindView(R.id.LinearLayoutNationalCode)
    LinearLayout LinearLayoutNationalCode;

    @BindView(R.id.LinearLayoutAccountNumber)
    LinearLayout LinearLayoutAccountNumber;


    public Login() {//______________________________________________________________________________ Start Login
    }//_____________________________________________________________________________________________ End Login



    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {//__________________________________________________________ Start onCreateView
        context = getActivity();
        vm_login = new VM_Login(context);
        FragmentLoginBinding binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_login,
                container,
                false
        );
        binding.setLogin(vm_login);
        view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    @Override
    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        if(disposableObserver != null)
            disposableObserver.dispose();
        disposableObserver = null;
        ObserverObservable();
        SetClick();
        SetTextWatcher();
    }//_____________________________________________________________________________________________ End onStart



    private void SetClick() {//_____________________________________________________________________ Start SetClick

        RelativeLayoutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (StaticFunctions.isCancel) {
                    if (CheckEmpty()) {
                        ShowLoading();
                        MD_Login login = new MD_Login();
                        login.setNationalCode(EditTextNationalCode.getText().toString());
                        login.setAccountNumber(EditTextAccountNumber.getText().toString());
                        vm_login.ConnectForLogin(login);
                    }
                } else {
                    StaticFunctions.isCancel = true;
                    DismissLoading();
                }
            }
        });



    }//_____________________________________________________________________________________________ End SetClick



    private void SetTextWatcher() {//_______________________________________________________________ Start SetTextWatcher
        EditTextNationalCode.addTextChangedListener(TextChangeForChangeBack(
                LinearLayoutNationalCode,
                EditTextNationalCode));

        EditTextAccountNumber.addTextChangedListener(TextChangeForChangeBack(
                LinearLayoutAccountNumber,
                EditTextAccountNumber));
    }//_____________________________________________________________________________________________ End SetTextWatcher


    private Boolean CheckEmpty() {//________________________________________________________________ Start CheckEmpty

        boolean national = true;
        boolean account = true;

        if (EditTextNationalCode.getText().length() == 0) {
            EditTextNationalCode.setBackgroundColor(getResources().getColor(R.color.ML_Edit_Empty_back));
            LinearLayoutNationalCode.setBackgroundResource(R.drawable.dw_edit_empty_back);
            EditTextNationalCode.setError(getResources().getString(R.string.EmptyNationalCode));
            EditTextNationalCode.requestFocus();
            national = false;
        }

        if (EditTextAccountNumber.getText().length() == 0) {
            EditTextAccountNumber.setBackgroundColor(getResources().getColor(R.color.ML_Edit_Empty_back));
            LinearLayoutAccountNumber.setBackgroundResource(R.drawable.dw_edit_empty_back);
            EditTextAccountNumber.setError(getResources().getString(R.string.EmptyAccountNumber));
            EditTextAccountNumber.requestFocus();
            account = false;
        }




        if (national && account)
            return true;
        else
            return false;

    }//_____________________________________________________________________________________________ End CheckEmpty


    private void DismissLoading() {//_______________________________________________________________ Start DismissLoading
        StaticFunctions.isCancel = true;
        TextViewLogin.setText(getResources().getString(R.string.Login));
        RelativeLayoutLogin.setBackground(getResources().getDrawable(R.drawable.dw_button));
        GifViewLogin.setVisibility(View.GONE);
        ImageViewLogin.setVisibility(View.VISIBLE);

    }//_____________________________________________________________________________________________ End DismissLoading


    private void ShowLoading() {//__________________________________________________________________ Start ShowLoading
        StaticFunctions.isCancel = false;
        TextViewLogin.setText(getResources().getString(R.string.Cancel));
        RelativeLayoutLogin.setBackground(getResources().getDrawable(R.drawable.dw_button_connecting));
        GifViewLogin.setVisibility(View.VISIBLE);
        ImageViewLogin.setVisibility(View.INVISIBLE);
    }//_____________________________________________________________________________________________ End ShowLoading



    private void ObserverObservable() {//___________________________________________________________ Start ObserverObservable

        disposableObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(final String s) {
                getActivity()
                        .runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                DismissLoading();
                                switch (s) {
                                    case "successfully":
                                        if(disposableObserver != null)
                                            disposableObserver.dispose();
                                        disposableObserver = null;
                                        break;
                                }
                            }
                        });
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };


        vm_login
                .getPublishSubject()
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(disposableObserver);

    }//_____________________________________________________________________________________________ End ObserverObservable


    @Override
    public void onDestroy() {//_____________________________________________________________________ Start onDestroy
        super.onDestroy();
        disposableObserver.dispose();
    }//_____________________________________________________________________________________________ End onDestroy


}
