# 📝 Todo Summary Assistant

A full-stack Todo List management system built with **React**, **Spring Boot**, and **OpenAI LLM API**, integrated with **Slack** for real-time summary notifications.

##  Features

- ✅ Add, update, and delete todos.
- 📋 Toggle todo status (pending/completed).
- 📈 Generate a summary of pending todos using **OpenAI LLM API**.
- 📤 Automatically send the summary to a **Slack channel**.
- ⚙️ React Query for data fetching and mutation.
- 🔐 Backend built with Spring Boot (REST API).
- 🎨 Beautiful UI with **Tailwind CSS**.

---

 ## 📁 Project Structure

### Backend (Spring Boot)

- `TodoListController`: Handles CRUD operations for todo items.
- `SummaryController`: Generates summary of pending todos using OpenAI and sends it to Slack.
- `LLMService`: Calls LLM (e.g., OpenAI API) to generate natural language summaries.
- `SlackService`: Sends generated summaries to a configured Slack Webhook URL.
- `ITodoListService`: Service interface for todo operations.

### Frontend (React + Tailwind CSS)

- `Home.jsx`: Main todo list UI with add, delete, update status functionality.
- `Completed.jsx`: Displays completed todos.
- `Pending.jsx`: Generates and displays summary using LLM and posts it to Slack.
- `utils/Infos.js`: Contains base URL and error handlers.

### LLMService (OpenAI Integration)

* This service integrates with the OpenAI Chat Completions API to generate summaries of pending todos using GPT models like gpt-3.5-turbo.
* Function: generateSummary(List<String> pendingTodos)
* Input: List of todo task strings.
* Output: Natural language summary of the tasks.
* API: https://api.openai.com/v1/chat/completions
* Environment Variable:

###  SlackService (Slack Webhook Integration)

* This service posts generated summaries directly to a configured Slack channel using a Slack Incoming Webhook.
* Function: sendMessageToSlack(String message)
* Input: Plain text message (e.g., LLM-generated summary).
* Output: Posts message to Slack.
* Slack Setup: Generate webhook via Slack Incoming Webhooks.
* Environment Variable:

![1 to](https://github.com/user-attachments/assets/e92742da-e80b-4b5f-8601-96fa6bbed79d)

![2 to](https://github.com/user-attachments/assets/7e51a7cc-7b58-4514-9fec-0e87f0270fe6)

![3 to](https://github.com/user-attachments/assets/97bd7830-5980-4bc9-92e4-2382447dc0d2)
