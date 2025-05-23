package in.darshan.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.darshan.entity.TodoList;
import in.darshan.repo.TodoListRepository;
import in.darshan.service.ITodoListService;
@Service
public class TodoListService implements ITodoListService{
	
	@Autowired
	private TodoListRepository repo;

	@Override
	public TodoList addTitle(TodoList list) {
		// TODO Auto-generated method stub
		return repo.save(list);
	}

	@Override
	public List<TodoList> getTodoList() {
		// TODO Auto-generated method stub
		return repo.getTodoList();
	}

	@Override
	@Transactional
	public void deleteTitleById(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteTitleById(id);
	}

	@Override
	public List<TodoList> getTitleWithStatus() {
		// TODO Auto-generated method stub
		return repo.getTitleWithStatus();
	}

	@Override
	@Transactional
	public int updateStatus(Integer id, String status) {
		// TODO Auto-generated method stub
		
		return repo.updateStatus(id,status);
		
	}

	@Override
	public List<TodoList> getTitleWithCompleted() {
		// TODO Auto-generated method stub
		return repo.getTitleWithCompleted();
	}

}
