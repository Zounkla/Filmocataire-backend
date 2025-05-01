package filmocataire.backend.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TestController {

    private ChatClient chatClient;
    private final MistralAiChatModel mistralAiChatModel;


    public TestController(ChatClient.Builder chatClientBuilder,
                          MistralAiChatModel mistralAiChatModel) {
           this.mistralAiChatModel = mistralAiChatModel;
    }

    @GetMapping("/ai")
    public ResponseEntity<Flux<String>> test(@RequestParam String userInput) {
        this.chatClient = ChatClient.builder(mistralAiChatModel).build();
        Flux<String> result = this.chatClient.prompt()
                .user(userInput)
                .stream()
                .content();
        return ResponseEntity.ok(result);
    }
}
