package exercise.android.reemh.todo_items;

import android.graphics.Color;

import com.google.gson.Gson;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

public class TodoItem implements Serializable {
    public static final boolean IN_PROGRESS = false;
    public static final boolean DONE = true;
    public static final int DONE_COLOR = Color.GREEN;
    private static final String DEFAULT_DESCRIPTION = "This TodoItem Has No Description";
    private static final long MILLISECONDS_IN_AN_HOUR = 3600000L;
    private static final String MODIFIED_MINUTES_STR = "%s minutes ago";
    private static final String MODIFIED_HOUR_STR = "Today at %s";
    private static final String MODIFIED_TIME_STR = "%s at %s";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
    private final String itemId;
    private String itemDescription;
    private boolean itemStatus;
    private int itemColor;
    private static final Gson gson = new Gson();
    private final Calendar itemCreationTime = Calendar.getInstance();
    private final Calendar itemModifiedTime = Calendar.getInstance();


    TodoItem(String description) {
        super();
        this.itemId = UUID.randomUUID().toString();
        this.itemDescription = description;
        this.itemStatus = IN_PROGRESS;
        this.itemCreationTime.setTimeInMillis(System.currentTimeMillis());
        this.itemModifiedTime.setTimeInMillis(System.currentTimeMillis());
    }

    TodoItem(){
        super();
        this.itemId = UUID.randomUUID().toString();
        this.itemDescription = DEFAULT_DESCRIPTION;
        this.itemStatus = IN_PROGRESS;
        this.itemCreationTime.setTimeInMillis(System.currentTimeMillis());
        this.itemModifiedTime.setTimeInMillis(System.currentTimeMillis());
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        this.updateItemModifiedTime();
    }

    public String getItemId() {return this.itemId;}

    public boolean getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(boolean itemStatus) {
        this.itemStatus = itemStatus;
        this.updateItemModifiedTime();

    }

    public int getItemColor() { return itemColor; }

    public void setItemColor(int itemColor) {
        this.itemColor = itemColor;
        this.updateItemModifiedTime();
    }

    public Long getItemCreationTime() {
        return this.itemCreationTime.getTime().getTime();
    }

    public Long getItemModifiedTime() {return this.itemModifiedTime.getTime().getTime();}

    public String getItemCreationTimeFormatted() {
        return sdf.format(this.itemCreationTime.getTime());
    }

    public String getItemModifiedTimeFormatted() {
        long lastModified = System.currentTimeMillis() - this.itemModifiedTime.getTime().getTime();
        String modifiedTimeString;
        String modifiedMinute = sdf.format(this.itemModifiedTime.get(Calendar.MINUTE));
        String modifiedHour = sdf.format(this.itemModifiedTime.get(Calendar.HOUR));
        String modifiedDate = sdf.format(this.itemModifiedTime.get(Calendar.DATE));
        if (lastModified < MILLISECONDS_IN_AN_HOUR){
            modifiedTimeString = String.format(MODIFIED_MINUTES_STR, modifiedMinute);
        }
        else if (lastModified < MILLISECONDS_IN_AN_HOUR * 24L){
            modifiedTimeString = String.format(MODIFIED_HOUR_STR,  modifiedHour);
        }
        else {
            modifiedTimeString = String.format(MODIFIED_TIME_STR, modifiedDate, modifiedHour);
        }
        return modifiedTimeString;
    }


    public void updateItemModifiedTime(){
        this.itemModifiedTime.setTimeInMillis(System.currentTimeMillis());
    }

    @Override
    public String toString(){
        return gson.toJson(this);
    }
}
