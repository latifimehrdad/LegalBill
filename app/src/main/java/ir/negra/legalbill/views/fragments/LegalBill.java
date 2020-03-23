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

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.negra.legalbill.R;
import ir.negra.legalbill.databinding.FragmentLegallBillBinding;
import ir.negra.legalbill.models.ModelSpinnerItem;
import ir.negra.legalbill.viewmodels.fragments.VM_LegalBill;
import ir.negra.legalbill.views.dialogs.searchspinner.MLSpinnerDialog;
import ir.negra.legalbill.views.dialogs.searchspinner.OnSpinnerItemClick;

public class LegalBill extends Fragment {

    private Context context;
    private View view;
    private VM_LegalBill vm_legalBill;
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



    public LegalBill() {//__________________________________________________________________________ Start LegalBill
    }//_____________________________________________________________________________________________ End LegalBill


    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {//__________________________________________________________ Start onCreateView
        context = getActivity();
        vm_legalBill = new VM_LegalBill(context);
        FragmentLegallBillBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_legall_bill, container, false
        );
        binding.setLegal(vm_legalBill);
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
        MonthList.add(new ModelSpinnerItem("1","فروردین"));
        MonthList.add(new ModelSpinnerItem("2","اردیبهشت"));
        MonthList.add(new ModelSpinnerItem("3","خرداد"));
        MonthList.add(new ModelSpinnerItem("4","تیر"));
        MonthList.add(new ModelSpinnerItem("5","مرداد"));
        MonthList.add(new ModelSpinnerItem("6","شهریور"));
        MonthList.add(new ModelSpinnerItem("7","مهر"));
        MonthList.add(new ModelSpinnerItem("8","آبان"));
        MonthList.add(new ModelSpinnerItem("9","آذر"));
        MonthList.add(new ModelSpinnerItem("10","دی"));
        MonthList.add(new ModelSpinnerItem("11","بهمن"));
        MonthList.add(new ModelSpinnerItem("12","اسفند"));
        TextViewMonth.setText(getResources().getString(R.string.ChooseMonth));

        YearList = new ArrayList<>();
        YearList.add(new ModelSpinnerItem("1389","1389"));
        YearList.add(new ModelSpinnerItem("1390","1390"));
        YearList.add(new ModelSpinnerItem("1391","1391"));
        YearList.add(new ModelSpinnerItem("1392","1392"));
        YearList.add(new ModelSpinnerItem("1393","1393"));
        YearList.add(new ModelSpinnerItem("1394","1394"));
        YearList.add(new ModelSpinnerItem("1395","1395"));
        YearList.add(new ModelSpinnerItem("1396","1396"));
        YearList.add(new ModelSpinnerItem("1397","1397"));
        YearList.add(new ModelSpinnerItem("1398","1398"));
        YearList.add(new ModelSpinnerItem("1399","1399"));
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
