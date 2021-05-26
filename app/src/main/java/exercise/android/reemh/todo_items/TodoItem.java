package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TodoItem implements Serializable {
    public static final boolean IN_PROGRESS = false;
    public static final boolean DONE = true;
    private static final String DEFAULT_DESCRIPTION = "This TodoItem Has No Description";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
    private String itemDescription;
    private boolean itemStatus;
    private final Calendar itemCreationTime = Calendar.getInstance();


    TodoItem(String description) {
        super();
        this.itemDescription = description;
        this.itemStatus = IN_PROGRESS;
        this.itemCreationTime.setTimeInMillis(System.currentTimeMillis());
    }

    TodoItem(){
        super();
        this.itemDescription = DEFAULT_DESCRIPTION;
        this.itemStatus = IN_PROGRESS;
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

    public Long getItemCreationTime() {
        return itemCreationTime.getTimeInMillis();
    }

    public String getItemCreationTimeFormatted() {
        return sdf.format(this.itemCreationTime);
    }
}
