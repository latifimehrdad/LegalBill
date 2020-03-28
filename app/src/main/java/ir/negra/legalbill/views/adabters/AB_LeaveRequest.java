package ir.negra.legalbill.views.adabters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;
import ir.negra.legalbill.R;
import ir.negra.legalbill.databinding.AdabterLeaveBinding;
import ir.negra.legalbill.databinding.AdabterTerafficsBinding;

public class AB_LeaveRequest extends RecyclerView.Adapter<AB_LeaveRequest.CustomHolder>{

    private LayoutInflater layoutInflater;

    public AB_LeaveRequest() {//____________________________________________________________________ Start RequestAdabter

    }//_____________________________________________________________________________________________ Start RequestAdabter


    @NonNull
    @Override
    public AB_LeaveRequest.CustomHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {//_____________________________________________________ Start CustomHolder
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        return new AB_LeaveRequest.CustomHolder(DataBindingUtil.inflate(
                layoutInflater,
                R.layout.adabter_leave,
                parent,
                false));
    }//_____________________________________________________________________________________________ Start CustomHolder



    @Override
    public void onBindViewHolder(AB_LeaveRequest.CustomHolder holder, int position) {//______________ Start onBindViewHolder
        holder.bind(position);
    }//_____________________________________________________________________________________________ Start onBindViewHolder


    @Override
    public int getItemCount() {//___________________________________________________________________ Start getItemCount
        return 10;
    }//_____________________________________________________________________________________________ Start getItemCount




    public class CustomHolder extends RecyclerView.ViewHolder {//___________________________________ Start class CustomHolder

        AdabterLeaveBinding adabterLeaveBinding;

        public CustomHolder(AdabterLeaveBinding binding) {//____________________________________ Start CustomHolder
            super(binding.getRoot());
            this.adabterLeaveBinding = binding;
            ButterKnife.bind(this, adabterLeaveBinding.getRoot());
        }//_________________________________________________________________________________________ End CustomHolder


        public void bind(int position) {//______________________________________ Start bind
            adabterLeaveBinding.executePendingBindings();
        }//_________________________________________________________________________________________ End bind

    }//_____________________________________________________________________________________________ End class CustomHolder

}