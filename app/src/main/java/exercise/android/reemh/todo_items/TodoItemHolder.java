package exercise.android.reemh.todo_items;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoItemHolder extends RecyclerView.ViewHolder {
    TextView itemDescription;
    TextView itemCreationTime;
    CheckBox itemCheckBox;
    ImageButton itemDeleteButton;

    public TodoItemHolder(@NonNull View itemView) {
        super(itemView);
        this.itemDescription = itemView.findViewById(R.id.itemDescription);
        this.itemCreationTime = itemView.findViewById(R.id.itemCreationTime);
        this.itemCheckBox = itemView.findViewById(R.id.itemCheckBox);
        this.itemDeleteButton = itemView.findViewById(R.id.itemDeleteButton);
    }


}
