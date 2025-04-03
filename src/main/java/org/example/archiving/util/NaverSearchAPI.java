package org.example.archiving.util;

import org.example.archiving.model.vo.KeywordSearch;
import org.example.archiving.model.vo.NaverSearchParam;
import org.example.archiving.model.vo.NaverSearchResult;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Component
public class NaverSearchAPI implements ObjectMapperMixin {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public List<KeywordSearch> callAPI(NaverSearchParam param) throws Exception {
        String url = "https://openapi.naver.com/v1/search/blog.json";
        String query = URLEncoder.encode(param.query(), StandardCharsets.UTF_8);
        String clientId = System.getenv("NAVER_CLIENT_ID");
        String clientSecret = System.getenv("NAVER_CLIENT_SECRET");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("%s?query=%s".formatted(url, query)))
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        NaverSearchResult naverSearchResult = objectMapper.readValue(responseBody, NaverSearchResult.class);
        return naverSearchResult.items()
                .stream().map(item -> new KeywordSearch(
                        UUID.randomUUID().toString(),
                        item.title(),
                        item.link(),
                        item.description(),
                        item.postdate(),
                        ""
                ))
                .toList();
     }
}
