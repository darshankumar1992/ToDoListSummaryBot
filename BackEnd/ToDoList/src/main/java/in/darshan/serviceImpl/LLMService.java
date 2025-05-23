package in.darshan.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.darshan.entity.TodoList;

@Service
public class LLMService {
	
	@Value("${openrouter.api.key}")
    private String apiKey;

    @Value("${openrouter.model}")
    private String model;

    private final RestTemplate restTemplate = new RestTemplate();

	public String generateSummary(List<TodoList> todos) {
		// TODO Auto-generated method stub
		if (todos.isEmpty()) {
            return "No pending todos to summarize!";
        }

		String todoList = todos.stream()
			    .map(todo -> "- " + todo.getTitle())
			    .collect(Collectors.joining("\n"));


		String prompt = "Please provide a brief, upbeat summary of the following pending tasks. " +
                "Group related tasks and emphasize anything that looks important or urgent. " +
                "Mention any health-related tasks (like exercise or rest). " +
                "Keep the summary under 200 characters:\n\n" + todoList;



        String url = "https://openrouter.ai/api/v1/chat/completions";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("HTTP-Referer", "https://yourdomain.com"); 
        headers.set("X-Title", "Todo Summary Assistant");

        Map<String, Object> requestBody = Map.of(
                "model", model,
                "messages", List.of(Map.of("role", "user", "content", prompt)),
                "max_tokens", 200,
                "temperature", 0.7
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
            Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
            return (String) message.get("content");
        } else {
            return "Failed to get summary: " + response.getStatusCode();
        }
	}

}
