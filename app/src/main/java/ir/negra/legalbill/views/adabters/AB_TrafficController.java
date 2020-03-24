package ir.negra.legalbill.views.adabters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;
import ir.negra.legalbill.R;
import ir.negra.legalbill.databinding.AdabterTerafficsBinding;

public class AB_TrafficController extends RecyclerView.Adapter<AB_TrafficController.CustomHolder>{

    private LayoutInflater layoutInflater;

    public AB_TrafficController() {//______________________________________________ Start RequestAdabter

    }//_____________________________________________________________________________________________ Start RequestAdabter


    @NonNull
    @Override
    public AB_TrafficController.CustomHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {//_____________________________________________________ Start CustomHolder
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        return new AB_TrafficController.CustomHolder(DataBindingUtil.inflate(
                layoutInflater,
                R.layout.adabter_teraffics,
                parent,
                false));
    }//_____________________________________________________________________________________________ Start CustomHolder



    @Override
    public void onBindViewHolder(AB_TrafficController.CustomHolder holder, int position) {//______________ Start onBindViewHolder
        holder.bind(position);
    }//_____________________________________________________________________________________________ Start onBindViewHolder


    @Override
    public int getItemCount() {//___________________________________________________________________ Start getItemCount
        return 10;
    }//_____________________________________________________________________________________________ Start getItemCount




    public class CustomHolder extends RecyclerView.ViewHolder {//___________________________________ Start class CustomHolder

        AdabterTerafficsBinding adabterTerafficsBinding;

        public CustomHolder(AdabterTerafficsBinding binding) {//____________________________________ Start CustomHolder
            super(binding.getRoot());
            this.adabterTerafficsBinding = binding;
            ButterKnife.bind(this, adabterTerafficsBinding.getRoot());
        }//_________________________________________________________________________________________ End CustomHolder


        public void bind(int position) {//______________________________________ Start bind
            adabterTerafficsBinding.executePendingBindings();
        }//_________________________________________________________________________________________ End bind

    }//_____________________________________________________________________________________________ End class CustomHolder

}