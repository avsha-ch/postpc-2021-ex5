package exercise.android.reemh.todo_items;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class TodoItemsDataBaseImpl implements TodoItemsDataBase {

  private ArrayList<TodoItem> todoItemList;
  private final Context context;
  private final SharedPreferences sp;
  private static final Gson gson = new Gson();

  TodoItemsDataBaseImpl(Context context){
    this.todoItemList = new ArrayList<TodoItem>();
    this.context = context;
    this.sp = context.getSharedPreferences("local_db_todoitems", Context.MODE_PRIVATE);
    initializeFromSp();
  }

  private void initializeFromSp(){
    Set<String> keys = this.sp.getAll().keySet();
    for (String key : keys){
      String todoItemSavedAsString = sp.getString(key, null);
      TodoItem todoItem = gson.fromJson(todoItemSavedAsString, TodoItem.class);
      if (todoItem != null){
        todoItemList.add(todoItem);
      }
    }
  }

  @Override
  public ArrayList<TodoItem> getCurrentItems() { return todoItemList; }

  public void setCurrentItems(ArrayList<TodoItem> newCurrentItems){
    this.todoItemList = newCurrentItems;
  }

  @Override
  public void addNewInProgressItem(String description) {
    TodoItem newItem = new TodoItem(description);
    todoItemList.add(newItem);
    SharedPreferences.Editor editor = sp.edit();
    editor.putString(newItem.getItemId(), newItem.toString());
    editor.apply();
    sendBroadcastDbChanged();
  }

  @Override
  public void markItemDone(TodoItem item) {
    item.setItemStatus(TodoItem.DONE);
    item.setItemColor(TodoItem.DONE_COLOR);
    Collections.sort(this.todoItemList, new sortTodoList());
    SharedPreferences.Editor editor = sp.edit();
    editor.putString(item.getItemId(), item.toString());
    editor.apply();
    sendBroadcastDbChanged();
  }

  @Override
  public void markItemInProgress(TodoItem item) {
    item.setItemStatus(TodoItem.IN_PROGRESS);
    item.setItemColor(Color.WHITE);
    Collections.sort(this.todoItemList, new sortTodoList());
    SharedPreferences.Editor editor = sp.edit();
    editor.putString(item.getItemId(), item.toString());
    editor.apply();
    sendBroadcastDbChanged();
  }

  @Override
  public void deleteItem(TodoItem item) {
    todoItemList.remove(item);
    SharedPreferences.Editor editor = sp.edit();
    editor.remove(item.getItemId());
    editor.apply();
    sendBroadcastDbChanged();
  }

  @Override
  public int getSize() {
    return getCurrentItems().size();
  }

  @Override
  public TodoItem getItemByIndex(int i){
    return this.todoItemList.get(i);
  }

  @Override
  public @Nullable TodoItem getItemById(String itemId){
    for (TodoItem item : todoItemList){
      if (item.getItemId().equals(itemId)){
        return item;
      }
    }
    return null;
  }

  @Override
  public void setItemDescription(String itemId, String newDescription){
    for (TodoItem item : todoItemList){
      if (item.getItemId().equals(itemId)){
        item.setItemDescription(newDescription);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(item.getItemId(), item.toString());
        editor.apply();
        sendBroadcastDbChanged();
        return;
      }
    }
  }

  private void sendBroadcastDbChanged(){

    Intent broadcast = new Intent("db_changed");
    broadcast.putExtra("new_list", getCurrentItems());
    context.sendBroadcast(broadcast);
  }
}
