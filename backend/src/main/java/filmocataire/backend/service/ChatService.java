package filmocataire.backend.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatService {

    @Getter
    @Setter
    private ChatClient defaultClient;

    private final MistralAiChatModel mistralAiChatModel;

    public ChatService(MistralAiChatModel mistralAiChatModel) {
        this.mistralAiChatModel = mistralAiChatModel;
    }


    public Flux<String> ask(String message) {
        if (this.defaultClient == null) {
            this.defaultClient = ChatClient.builder(mistralAiChatModel)
                    .defaultTools("get_animes")
                    .build();
        }
        return defaultClient.prompt()
                .user(message)
                .stream()
                .content();
    }
}
