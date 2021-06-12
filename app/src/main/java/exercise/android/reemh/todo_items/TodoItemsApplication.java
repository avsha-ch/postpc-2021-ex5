package exercise.android.reemh.todo_items;

import android.app.Application;

public class TodoItemsApplication extends Application {

    private TodoItemsDataBase todoItemsDataBase;
    private static TodoItemsApplication instance = null;


    public TodoItemsDataBase getDB() {
        return todoItemsDataBase;
    }

    public static TodoItemsApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        todoItemsDataBase = new TodoItemsDataBaseImpl(this);
    }
}
