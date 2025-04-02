package org.example.archiving.model.mapper;

import org.example.archiving.model.vo.KeywordSearch;

public interface BookmarkMapper {
    int insertBookmark(KeywordSearch keywordSearch);
    KeywordSearch getOneBookmark(String uuid);
    String checkLastID();
}
