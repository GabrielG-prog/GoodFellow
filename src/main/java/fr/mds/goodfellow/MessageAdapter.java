package fr.mds.goodfellow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends BaseAdapter {
    private List<Message> messages = new ArrayList<>();
    private Context context;

    MessageAdapter(Context context) {
        this.context = context;
    }

    void add(Message message) {
        this.messages.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        MessageViewHolder holder = new MessageViewHolder();
        LayoutInflater messageInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        Message message = messages.get(i);

        if (message.isBelongsToCurrentUser()) {
            assert messageInflater != null;
            convertView = messageInflater.inflate(R.layout.my_message, null);
            holder.messageBody = convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);
            holder.messageBody.setText(message.getText());
        } else {
            assert messageInflater != null;
            convertView = messageInflater.inflate(R.layout.their_message, null);
            holder.avatar = convertView.findViewById(R.id.avatar);
            holder.name = convertView.findViewById(R.id.name);
            holder.messageBody = convertView.findViewById(R.id.message_body);
            convertView.setTag(holder);

            holder.name.setText(message.getMemberData().getName());
            holder.messageBody.setText(message.getText());
            GradientDrawable drawable = (GradientDrawable) holder.avatar.getBackground();
            drawable.setColor(Color.parseColor(message.getMemberData().getColor()));
        }

        return convertView;
    }

}

class MessageViewHolder {
    View avatar;
    public TextView name;
    TextView messageBody;
}
