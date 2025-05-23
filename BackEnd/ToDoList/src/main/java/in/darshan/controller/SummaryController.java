package in.darshan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.darshan.entity.TodoList;
import in.darshan.service.ITodoListService;
import in.darshan.serviceImpl.LLMService;
import in.darshan.serviceImpl.SlackService;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {
	
	@Autowired
	private ITodoListService todoService;
	@Autowired
	private SlackService slackService;
	@Autowired
	private LLMService llmService;
	
	@PostMapping
	public ResponseEntity<?> summarizeAndSendToSlack() {
	    try {
	        List<TodoList> todos = todoService.getTodoList();

	        
	        List<TodoList> pendingTodos = todos.stream()
	            .filter(todo -> "pending".equalsIgnoreCase(todo.getStatus()))
	            .toList();

	        if (pendingTodos.isEmpty()) {
	            return ResponseEntity.ok(Map.of(
	                "status", "success",
	                "message", "No pending todos to summarize",
	                "summary", ""
	            ));
	        }

	        String summary = llmService.generateSummary(pendingTodos);

	       
	        System.out.println("Todo Summary: " + summary);

	        slackService.sendToSlack(summary);

	        
	        return ResponseEntity.ok(Map.of(
	            "status", "success",
	            "message", "Summary sent to Slack",
	            "summary", summary
	        ));
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body(Map.of(
	            "status", "error",
	            "message", "Failed to summarize and send to Slack: " + e.getMessage()
	        ));
	    }
	}

}
