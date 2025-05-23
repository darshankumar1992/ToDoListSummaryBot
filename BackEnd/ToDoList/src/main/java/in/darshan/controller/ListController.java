package in.darshan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.darshan.entity.TodoList;

import in.darshan.request.AddTitle;
import in.darshan.request.DeleteRequest;
import in.darshan.request.StatusUpdate;
import in.darshan.service.ITodoListService;

@RestController
@RequestMapping("/api/list")
public class ListController {
	
	@Autowired
	private ITodoListService service;
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addTitle(@RequestBody AddTitle request){
		System.out.println("Add");
		TodoList list = new TodoList();
		list.setTitle(request.getTitle());
		
		list.setStatus("pending");
		
		TodoList todolist=service.addTitle(list);
		
		Map<String, String> response=new HashMap<>();
		
		response.put("status", "success");
		response.put("message", "Added to List");	
		return ResponseEntity.ok(response);
		
	}
	
	
	@GetMapping("/getTodoList")
	public ResponseEntity<List<TodoList>> getTodoList(){
		
		List<TodoList> todolist=service.getTodoList();
		return ResponseEntity.ok(todolist);
	}
	
	
	@DeleteMapping("/deleteTitle")
	public ResponseEntity<?> deleteTitle(@RequestBody DeleteRequest request){
		
		service.deleteTitleById(request.getId());
		
		Map<String, String> response=new HashMap<>();
		
		response.put("status", "success");
		response.put("message", "Deleted successfully");
		
		return ResponseEntity.ok(response);		
	}
	
	@GetMapping("/getWithStatus")
	public ResponseEntity<List<TodoList>> getTitleWithStatusPending(){
		
		List<TodoList> todolist = service.getTitleWithStatus();
		
		return ResponseEntity.ok(todolist);
	}
	
	@GetMapping("/getWithCompleted")
	public ResponseEntity<List<TodoList>> getTitleWithStatusCompleted(){
		
		List<TodoList> todolist = service.getTitleWithCompleted();
		
		return ResponseEntity.ok(todolist);
	}
	
	
	
	@PutMapping("/updateStatus")
	public ResponseEntity<?> updateStatus(@RequestBody StatusUpdate request){
		
		int no=service.updateStatus(request.getId(), request.getStatus());
		
		Map<String, String> response=new HashMap<>();
		
		response.put("status","success");
		response.put("message", "completed");
		
		return ResponseEntity.ok(response);
	}

}
