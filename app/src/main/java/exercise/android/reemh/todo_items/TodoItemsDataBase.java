package exercise.android.reemh.todo_items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


// TODO: feel free to add/change/remove methods as you want
public interface TodoItemsDataBase extends Serializable {

  /** Get a copy of the current items list */
  List<TodoItem> getCurrentItems();

  /** Set current items list */
  public void setCurrentItems(ArrayList<TodoItem> newCurrentItems);

  /**
   * Creates a new TodoItem and adds it to the list, with the @param description and status=IN-PROGRESS
   * Subsequent calls to [getCurrentItems()] should have this new TodoItem in the list
   */
  void addNewInProgressItem(String description);

  /** mark the @param item as DONE */
  void markItemDone(TodoItem item);

  /** mark the @param item as IN-PROGRESS */
  void markItemInProgress(TodoItem item);

  /** delete the @param item */
  void deleteItem(TodoItem item);

  /** get the size of current items list */
  int getSize();

  /** returns the TodoItem at the  i'th place of todoItemList*/
  TodoItem getItemByIndex(int i);

  /** returns the TodoItem with id = itemId */
  TodoItem getItemById(String itemId);

  void setItemDescription(String itemId, String newDescription);
}
