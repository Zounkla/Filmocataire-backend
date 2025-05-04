package filmocataire.backend.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EndpointCallerService {

    private final RestTemplate restTemplate;

    public EndpointCallerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String callGetEndpoint(String url) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.GET, entity, String.class);
        response = handleRedirection(response, entity);

        return response.getBody();
    }

    private ResponseEntity<String> handleRedirection(ResponseEntity<String> response,
                                                     HttpEntity<String> entity) {
        if (response.getStatusCode().is3xxRedirection()
                && response.getHeaders().getLocation() != null) {
            String redirectUrl = response.getHeaders().getLocation().toString();
            return restTemplate.exchange(redirectUrl, HttpMethod.GET, entity, String.class);
        }
        return response;
    }
}
