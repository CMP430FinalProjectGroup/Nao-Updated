package com.example.nao;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


class Recent_Messaged_User_Adapter extends RecyclerView.Adapter<Recent_Messaged_User_Adapter.ViewHolder>  {

    // Member variables.
    private ArrayList<User> mUsersData;
    private Context mContext;
    private ImageView mUsersImage;
    private Recent_Messaged_User_Adapter mAdapter;

    Recent_Messaged_User_Adapter(Context context, ArrayList<User> userData) {
        this.mUsersData = userData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public Recent_Messaged_User_Adapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item_recent_messaged_user, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(Recent_Messaged_User_Adapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        User currentUser = mUsersData.get(position);

        holder.setIsRecyclable(false); // edited here for the duplicate item problem

        // Populate the textviews with data.
        holder.bindTo(currentUser);


    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mUsersData.size();
    }

    public Recent_Messaged_User_Adapter() {
        super();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {


        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mUsersImage = itemView.findViewById(R.id.person_image_id);


            itemView.setOnClickListener(this);
            //itemView.setOnLongClickListener(this);


        }

        void bindTo(User currentUser){
            mTitleText.setText(currentUser.getName());
            Glide.with(mContext).load(currentUser.getImageResource()).into(mUsersImage);

        }

        @Override
        public void onClick(View v) {

            User currentUser = mUsersData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, ChatActivity.class);

            detailIntent.putExtra("title", currentUser.getName());
            detailIntent.putExtra("image_resource",
                    currentUser.getImageResource());
            detailIntent.putExtra("bio", currentUser.getBio());
            mContext.startActivity(detailIntent);

        }


    }

}
