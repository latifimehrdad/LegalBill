package ir.negra.legalbill.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hamsaa.persiandatepicker.Listener;
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog;
import ir.hamsaa.persiandatepicker.util.PersianCalendar;
import ir.negra.legalbill.R;
import ir.negra.legalbill.databinding.FragmentLeaveRequestBinding;
import ir.negra.legalbill.utilities.ApplicationUtility;
import ir.negra.legalbill.viewmodels.fragments.VM_LeaveRequest;
import ir.negra.legalbill.views.adabters.AB_LeaveRequest;

public class LeaveRequest extends Fragment {

    private Context context;
    private View view;
    private VM_LeaveRequest vm_leaveRequest;
    private NavController navController;
    private AB_LeaveRequest ab_leaveRequest;

    @BindView(R.id.LinearLayoutDateFrom)
    LinearLayout LinearLayoutDateFrom;

    @BindView(R.id.TextViewDateFrom)
    TextView TextViewDateFrom;

    @BindView(R.id.LinearLayoutDateTo)
    LinearLayout LinearLayoutDateTo;

    @BindView(R.id.TextViewDateTo)
    TextView TextViewDateTo;

    @BindView(R.id.RecyclerViewLeave)
    RecyclerView RecyclerViewLeave;

    @BindView(R.id.LinearLayoutLeaveNew)
    LinearLayout LinearLayoutLeaveNew;

    @BindView(R.id.LinearLayoutDialog)
    LinearLayout LinearLayoutDialog;



    public LeaveRequest() {//_______________________________________________________________________ Start LeaveRequest
    }//_____________________________________________________________________________________________ End LeaveRequest


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {//__________________________________________________________ Start onCreateView
        context = getActivity();
        vm_leaveRequest = new VM_LeaveRequest(context);
        FragmentLeaveRequestBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_leave_request, container,false
        );
        binding.setLeave(vm_leaveRequest);
        view = binding.getRoot();
        ButterKnife.bind(this,view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    @Override
    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        navController = Navigation.findNavController(view);
        SetOnClick();
        TextViewDateFrom.setText(context.getResources().getString(R.string.DateFrom));
        TextViewDateTo.setText(context.getResources().getString(R.string.DateTo));
        ab_leaveRequest = new AB_LeaveRequest();
        RecyclerViewLeave.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL,false));
        RecyclerViewLeave.setAdapter(ab_leaveRequest);
        LinearLayoutDialog.setVisibility(View.GONE);
    }//_____________________________________________________________________________________________ End onStart


    private void SetOnClick() {//___________________________________________________________________ Start SetOnClick

        LinearLayoutLeaveNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutDialog.setVisibility(View.VISIBLE);
            }
        });

        LinearLayoutDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayoutDialog.setVisibility(View.GONE);
            }
        });

        LinearLayoutDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPickerDateFrom();
            }
        });

        TextViewDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPickerDateFrom();
            }
        });




        LinearLayoutDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPickerDateTo();
            }
        });

        TextViewDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPickerDateTo();
            }
        });

    }//_____________________________________________________________________________________________ End SetOnClick


    public PersianCalendar getPersianCalendar() {//_________________________________________________ Start getPersianCalendar
        ApplicationUtility utility = new ApplicationUtility();
        String ChangeDate = utility
                .MiladiToJalali(Calendar.getInstance().getTime(), "FullJalaliNumber")
                .replaceAll("/", "");
        PersianCalendar initDate = new PersianCalendar();
        initDate.setPersianDate(Integer.valueOf(ChangeDate.substring(0, 4)).intValue(), Integer.valueOf(ChangeDate.substring(4, 6)).intValue(), Integer.valueOf(ChangeDate.substring(6, 8)).intValue());
        return initDate;
    }//_____________________________________________________________________________________________ Start getPersianCalendar




    private void ShowPickerDateFrom() {//___________________________________________________________ Start ShowPickerDateFrom
        PersianDatePickerDialog pickerDialog = new PersianDatePickerDialog(context)
                .setPositiveButtonString(context.getString(R.string.Choose))
                .setNegativeButton(context.getString(R.string.Cancel))
                .setTodayButton(context.getString(R.string.Today))
                .setTodayButtonVisible(true)
                .setInitDate(getPersianCalendar())
                .setMaxYear(-1)
                .setMinYear(1300)
                .setActionTextColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .setTitleColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .setPickerBackgroundColor(context.getResources().getColor(R.color.ML_DatePicker))
                .setBackgroundColor(context.getResources().getColor(R.color.ML_White));

        pickerDialog.setListener(new Listener() {
            public void onDateSelected(PersianCalendar persianCalendar) {
                StringBuilder sb = new StringBuilder();
                sb.append(persianCalendar.getPersianYear());
                sb.append(String.format("%02d",persianCalendar.getPersianMonth()));
                sb.append(String.format("%02d",persianCalendar.getPersianDay()));
                //ChangeDate = Integer.valueOf(sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append(persianCalendar.getPersianYear());
                sb2.append("/");
                sb2.append(String.format("%02d",persianCalendar.getPersianMonth()));
                sb2.append("/");
                sb2.append(String.format("%02d",persianCalendar.getPersianDay()));
                TextViewDateFrom.setText(sb2.toString());
            }

            public void onDismissed() {
            }
        });

        pickerDialog.show();
    }//_____________________________________________________________________________________________ End ShowPickerDateFrom

    private void ShowPickerDateTo() {//_____________________________________________________________ Start ShowPickerDateTo
        PersianDatePickerDialog pickerDialog = new PersianDatePickerDialog(context)
                .setPositiveButtonString(context.getString(R.string.Choose))
                .setNegativeButton(context.getString(R.string.Cancel))
                .setTodayButton(context.getString(R.string.Today))
                .setTodayButtonVisible(true)
                .setInitDate(getPersianCalendar())
                .setMaxYear(-1)
                .setMinYear(1300)
                .setActionTextColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .setTitleColor(context.getResources().getColor(R.color.colorPrimaryDark))
                .setPickerBackgroundColor(context.getResources().getColor(R.color.ML_DatePicker))
                .setBackgroundColor(context.getResources().getColor(R.color.ML_White));

        pickerDialog.setListener(new Listener() {
            public void onDateSelected(PersianCalendar persianCalendar) {
                StringBuilder sb = new StringBuilder();
                sb.append(persianCalendar.getPersianYear());
                sb.append(String.format("%02d",persianCalendar.getPersianMonth()));
                sb.append(String.format("%02d",persianCalendar.getPersianDay()));
                //ChangeDate = Integer.valueOf(sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append(persianCalendar.getPersianYear());
                sb2.append("/");
                sb2.append(String.format("%02d",persianCalendar.getPersianMonth()));
                sb2.append("/");
                sb2.append(String.format("%02d",persianCalendar.getPersianDay()));
                TextViewDateTo.setText(sb2.toString());
            }

            public void onDismissed() {
            }
        });

        pickerDialog.show();
    }//_____________________________________________________________________________________________ End ShowPickerDateTo


}
