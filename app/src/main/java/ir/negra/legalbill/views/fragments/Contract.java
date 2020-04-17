package ir.negra.legalbill.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.negra.legalbill.R;
import ir.negra.legalbill.databinding.FragmentContractBinding;
import ir.negra.legalbill.databinding.FragmentLegallBillBinding;
import ir.negra.legalbill.models.ModelSpinnerItem;
import ir.negra.legalbill.utilities.ApplicationUtility;
import ir.negra.legalbill.viewmodels.fragments.VM_Contract;
import ir.negra.legalbill.viewmodels.fragments.VM_LegalBill;
import ir.negra.legalbill.views.dialogs.searchspinner.MLSpinnerDialog;
import ir.negra.legalbill.views.dialogs.searchspinner.OnSpinnerItemClick;

public class Contract  extends Fragment {

    private Context context;
    private View view;
    private VM_Contract vm_contract;
    private boolean ClickMonth = false;
    private ArrayList<ModelSpinnerItem> MonthList;
    private String MonthId = "-1";
    private MLSpinnerDialog spinnerMonth;

    private boolean ClickYear = false;
    private ArrayList<ModelSpinnerItem> YearList;
    private String YearId = "-1";
    private MLSpinnerDialog spinnerYear;


    @BindView(R.id.TextViewMonth)
    TextView TextViewMonth;

    @BindView(R.id.TextViewYear)
    TextView TextViewYear;


    public Contract() {//___________________________________________________________________________ Start Contract
    }//_____________________________________________________________________________________________ End Contract


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {//__________________________________________________________ Start onCreateView
        context = getActivity();
        vm_contract = new VM_Contract(context);
        FragmentContractBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_contract, container, false
        );
        binding.setContract(vm_contract);
        view = binding.getRoot();
        ButterKnife.bind(this, view);
        return view;
    }//_____________________________________________________________________________________________ End onCreateView


    @Override
    public void onStart() {//_______________________________________________________________________ Start onStart
        super.onStart();
        SetOnclick();
        SetYearAndMonthItems();
    }//_____________________________________________________________________________________________ End onStart


    private void SetYearAndMonthItems() {//_________________________________________________________ Start SetYearAndMonthItems
        MonthList = new ArrayList<>();
        MonthList.add(new ModelSpinnerItem("1", "فروردین"));
        MonthList.add(new ModelSpinnerItem("2", "اردیبهشت"));
        MonthList.add(new ModelSpinnerItem("3", "خرداد"));
        MonthList.add(new ModelSpinnerItem("4", "تیر"));
        MonthList.add(new ModelSpinnerItem("5", "مرداد"));
        MonthList.add(new ModelSpinnerItem("6", "شهریور"));
        MonthList.add(new ModelSpinnerItem("7", "مهر"));
        MonthList.add(new ModelSpinnerItem("8", "آبان"));
        MonthList.add(new ModelSpinnerItem("9", "آذر"));
        MonthList.add(new ModelSpinnerItem("10", "دی"));
        MonthList.add(new ModelSpinnerItem("11", "بهمن"));
        MonthList.add(new ModelSpinnerItem("12", "اسفند"));
        TextViewMonth.setText(getResources().getString(R.string.ChooseMonth));

        ApplicationUtility utility = new ApplicationUtility();
        String y = utility.MiladiToJalali(new Date(), "YearJalaliNumber");
        int year = 1399;
        try {
            year = Integer.valueOf(y);
        } catch (Exception e) {
        }
        YearList = new ArrayList<>();

        for(int i = year ; i > year - 10 ; i--) {
            YearList.add(new ModelSpinnerItem(String.valueOf(i), String.valueOf(i)));
        }
        TextViewYear.setText(getResources().getString(R.string.ChooseYear));


    }//_____________________________________________________________________________________________ End SetYearAndMonthItems


    private void SetOnclick() {//___________________________________________________________________ Start SetOnclick

        TextViewMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickMonth = true;
                SetItemMonth();
            }
        });

        TextViewYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickYear = true;
                SetItemYear();
            }
        });

    }//_____________________________________________________________________________________________ End SetOnclick


    private void SetItemYear() {//__________________________________________________________________ Start SetItemYear
        TextViewYear.setText(getResources().getString(R.string.ChooseYear));
        YearId = "-1";
        //spinnerDialog = new SpinnerDialog(getActivity(),items,"Select or Search City","Close Button Text");// With No Animation
        spinnerYear = new MLSpinnerDialog(
                getActivity(),
                YearList,
                getResources().getString(R.string.SearchYear),
                R.style.DialogAnimations_SmileWindow,
                getResources().getString(R.string.Cancel));// With 	Animation
        spinnerYear.setCancellable(true); // for cancellable
        spinnerYear.setShowKeyboard(false);// for open keyboard by default
        spinnerYear.bindOnSpinerListener(new OnSpinnerItemClick() {
            @Override
            public void onClick(String item, int position) {
                TextViewYear.setText(item);
                YearId = MonthList.get(position).getId();
//                LayoutBank.setBackgroundColor(getResources().getColor(R.color.mlEdit));
            }
        });

        if (ClickYear)
            spinnerYear.showSpinerDialog();
    }//_____________________________________________________________________________________________ End SetItemYear


    private void SetItemMonth() {//_________________________________________________________________ Start SetItemMonth
        TextViewMonth.setText(getResources().getString(R.string.ChooseMonth));
        MonthId = "-1";
        //spinnerDialog = new SpinnerDialog(getActivity(),items,"Select or Search City","Close Button Text");// With No Animation
        spinnerMonth = new MLSpinnerDialog(
                getActivity(),
                MonthList,
                getResources().getString(R.string.SearchMonth),
                R.style.DialogAnimations_SmileWindow,
                getResources().getString(R.string.Cancel));// With 	Animation
        spinnerMonth.setCancellable(true); // for cancellable
        spinnerMonth.setShowKeyboard(false);// for open keyboard by default
        spinnerMonth.bindOnSpinerListener(new OnSpinnerItemClick() {
            @Override
            public void onClick(String item, int position) {
                TextViewMonth.setText(item);
                MonthId = MonthList.get(position).getId();
//                LayoutBank.setBackgroundColor(getResources().getColor(R.color.mlEdit));
            }
        });

        if (ClickMonth)
            spinnerMonth.showSpinerDialog();
    }//_____________________________________________________________________________________________ End SetItemMonth


}