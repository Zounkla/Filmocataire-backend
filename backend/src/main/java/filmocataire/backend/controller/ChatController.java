package filmocataire.backend.controller;

import filmocataire.backend.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/ask")
    public ResponseEntity<Flux<String>> ask(@RequestParam String userInput) {
        return ResponseEntity.ok(chatService.ask(userInput));
    }
}
