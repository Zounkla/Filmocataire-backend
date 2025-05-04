package filmocataire.backend.service.tools.anime;

import filmocataire.backend.service.EndpointCallerService;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class AnimeService {

    private final EndpointCallerService endpointCallerService;

    public record GetAnimesRequest() { }

    public record GetAnimesResponse(String animeNames) { }

    @Bean("get_animes")
    @Description("Avoir la liste de tous les noms d'anime existants")
    public Function<GetAnimesRequest, GetAnimesResponse>
    getAnimes() {
        return this::apply;
    }

    public GetAnimesResponse apply(GetAnimesRequest request) {
        String searchUrl = "https://api.jikan.moe/v4/anime";
        String response = endpointCallerService.callGetEndpoint(searchUrl);
        return new GetAnimesResponse(response);
    }


}
