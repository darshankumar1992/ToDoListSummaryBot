package in.darshan.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import in.darshan.entity.TodoList;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Integer>{

	@Query("SELECT l FROM TodoList l")
	public  List<TodoList> getTodoList();

	@Modifying
	@Query("DELETE FROM TodoList l WHERE l.id=:id")
	public void deleteTitleById(@Param("id") Integer id);

	@Query("SELECT l FROM TodoList l WHERE l.status = 'pending'")
	public List<TodoList> getTitleWithStatus();

	@Modifying
	@Transactional
	@Query("UPDATE TodoList l SET l.status=:status WHERE l.id=:id")
	public int updateStatus(@Param("id") Integer id,@Param("status") String status);

	@Query("SELECT l FROM TodoList l WHERE l.status = 'completed'")
	public List<TodoList> getTitleWithCompleted();


}
