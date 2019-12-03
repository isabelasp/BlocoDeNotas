package br.ifsc.edu.firebaseddm;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class PessoaAdapter extends RecyclerView.Adapter<PessoaAdapter.MyViewHolder> {
    Context mContext;
    int mResource;
    ArrayList<Pessoa> mDataSet;

    public PessoaAdapter(Context mContext, int mResource, ArrayList<Pessoa> mDataSet) {
        this.mContext = mContext;
        this.mResource = mResource;
        this.mDataSet = mDataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View itemView = layoutInflater.inflate(mResource, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pessoa ṕessoa = mDataSet.get(position);
        holder.edNome.setText(ṕessoa.getNome());
        holder.edCpf.setText(ṕessoa.getCpf());
        holder.edSexo.setText(ṕessoa.getSexo());
        holder.edId.setText(ṕessoa.getId());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    //Declaração da Classa MyViewHolder
    public class  MyViewHolder extends  RecyclerView.ViewHolder{
        TextView edId,edNome,edCpf,edSexo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            edId = itemView.findViewById(R.id.edId);
            edNome = itemView.findViewById(R.id.edNome);
            edCpf = itemView.findViewById(R.id.edCpf);
            edSexo = itemView.findViewById(R.id.edSexo);

        }
    }

}
