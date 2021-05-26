package exercise.android.reemh.todo_items;

import android.graphics.Color;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TodoItem implements Serializable {
    public static final boolean IN_PROGRESS = false;
    public static final int IN_PROGRESS_COLOR = Color.blue(100);
    public static final boolean DONE = true;
    public static final int DONE_COLOR = Color.GREEN;
    private static final String DEFAULT_DESCRIPTION = "This TodoItem Has No Description";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
    private String itemDescription;
    private boolean itemStatus;
    private int itemColor;
    private final Calendar itemCreationTime = Calendar.getInstance();


    TodoItem(String description) {
        super();
        this.itemDescription = description;
        this.itemStatus = IN_PROGRESS;
        this.itemColor = IN_PROGRESS_COLOR;
        this.itemCreationTime.setTimeInMillis(System.currentTimeMillis());
    }

    TodoItem(){
        super();
        this.itemDescription = DEFAULT_DESCRIPTION;
        this.itemStatus = IN_PROGRESS;
        this.itemColor = IN_PROGRESS_COLOR;
        this.itemCreationTime.setTimeInMillis(System.currentTimeMillis());
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public boolean getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(boolean itemStatus) {
        this.itemStatus = itemStatus;
    }

    public int getItemColor() { return itemColor; }

    public void setItemColor(int itemColor) { this.itemColor = itemColor; }

    public Long getItemCreationTime() {
        return this.itemCreationTime.getTime().getTime();
    }

    public String getItemCreationTimeFormatted() {
        return sdf.format(this.itemCreationTime.getTime());
    }
}
