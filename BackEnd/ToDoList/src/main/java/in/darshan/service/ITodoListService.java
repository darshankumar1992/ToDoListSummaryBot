package in.darshan.service;

import java.util.List;

import in.darshan.entity.TodoList;

public interface ITodoListService {

	public TodoList addTitle(TodoList list);

	public List<TodoList> getTodoList();

	public void deleteTitleById(Integer id);

	public List<TodoList> getTitleWithStatus();

	public int updateStatus(Integer id, String status);

	public List<TodoList> getTitleWithCompleted();

}
