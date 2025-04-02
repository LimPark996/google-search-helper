package org.example.archiving.service;

import org.example.archiving.model.vo.KeywordSearch;

import java.util.List;

//@Service // Spring Container 에 Component 로 등록
public interface SearchService {
    List<KeywordSearch> searchByKeyword(String keyword) throws Exception;
}
