package exercise.android.reemh.todo_items;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class TodoItemViewHolder extends RecyclerView.ViewHolder {
    ConstraintLayout itemRowLayout;
    TextView itemDescription;
    TextView itemCreationTime;
    CheckBox itemCheckBox;
    ImageButton itemDeleteButton;
    ImageButton itemEditButton;

    public TodoItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemRowLayout = itemView.findViewById(R.id.itemRow);
        this.itemDescription = itemView.findViewById(R.id.itemDescription);
        this.itemCreationTime = itemView.findViewById(R.id.itemCreationTime);
        this.itemCheckBox = itemView.findViewById(R.id.itemCheckBox);
        this.itemDeleteButton = itemView.findViewById(R.id.itemDeleteButton);
        this.itemEditButton = itemView.findViewById(R.id.itemEditButton);
    }


}
