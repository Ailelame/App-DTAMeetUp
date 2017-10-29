package fr.dta.formtion.dta_meetup.eventlistfragments;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.dta.formtion.dta_meetup.R;
import fr.dta.formtion.dta_meetup.database.local.Event;

/**
 * Created by Arnaud Ringenbach on 28/10/2017.
 */

public class EventListRecyclerViewAdapter extends RecyclerView.Adapter<EventListRecyclerViewAdapter.ViewHolder> {

    private final List<Event> mValues;
    private final EventListFragment.OnListFragmentInteractionListener mListener;

    public EventListRecyclerViewAdapter(List<Event> items, EventListFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mEvent = mValues.get(position);
        holder.mEventNameView.setText(mValues.get(position).getEventName());
        Log.d("DAY OF WEEK : ", mValues.get(position).getWeekDay());
        holder.mEventWeekDayView.setText((mValues.get(position).getWeekDay()));
        holder.mEventTimeView.setText(mValues.get(position).getFormatedTime());
        holder.mEventMonthDayView.setText(Integer.toString(mValues.get(position).getEventDayOfMonth()));
        holder.mEventInterestedNbView.setText(Integer.toString(mValues.get(position).getEventNbInterested()) + " intéressé(s)");
        holder.mEventTypeView.setText(mValues.get(position).getEventType());

        /*
        This sets up the communication with our main activity
        When the user clicks on an item of the recyclerview
        The main activity gets the id of the event selected
         */
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mEvent.getEventId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues != null)
            return mValues.size();
        else
            return -1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mEventNameView;
        public final TextView mEventWeekDayView;
        public final TextView mEventTimeView;
        public final TextView mEventMonthDayView;
        public final TextView mEventInterestedNbView;
        public final TextView mEventTypeView;

        public Event mEvent;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mEventNameView = view.findViewById(R.id.eventLabelTextView);
            mEventWeekDayView = view.findViewById(R.id.weekDayTextView);
            mEventTimeView = view.findViewById(R.id.timeTextView);
            mEventMonthDayView = view.findViewById(R.id.dayMonthTextView);
            mEventInterestedNbView = view.findViewById(R.id.interestedNumberTextView);
            mEventTypeView = view.findViewById(R.id.categoryTextView);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mEventNameView.getText() + "'";
        }
    }
}
