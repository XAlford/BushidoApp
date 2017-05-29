package com.alford.bushidoapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by WilliamAlford on 5/29/17.
 */

public class MessengerAdapter extends RecyclerView.Adapter<MessengerAdapter.ViewHolder> {


    //inspired by thaleslima/firebase-chat-sample on github

    private List<MessageObject> mDataSet;


    private static final int TEXT_RIGHT = 1;
    private static final int TEXT_LEFT = 2;



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.text_msg);
        }
    }

    public MessengerAdapter(List<MessageObject> dataSet) {
        mDataSet = dataSet;
    }

    public void addMsg (MessageObject messageObject){
        mDataSet.add(messageObject);
        notifyItemInserted(mDataSet.size()-1);


    }


    @Override
    public MessengerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
//        if (viewType == TEXT_RIGHT) {
//            view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.right_messenger, parent, false);
//        } else {
//            view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.left_messenger, parent, false);
//        }

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.left_messenger, parent, false);

        return new ViewHolder(view);
    }

//    @Override
//    public int getItemViewType(int position) {
////        if (mDataSet.get(position).equals("key"))
////            return TEXT_RIGHT;
//
//        return TEXT_LEFT;
//    }

    @Override
    public void onBindViewHolder(MessengerAdapter.ViewHolder holder, int position) {

        MessageObject msg = mDataSet.get(position);
        holder.mTextView.setText(msg.getMessage());



    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
