package org.example.archiving.service;

import org.apache.ibatis.session.SqlSession;
import org.example.archiving.config.MyBatisConfig;
import org.example.archiving.model.mapper.BookmarkMapper;
import org.example.archiving.model.vo.KeywordSearch;
import org.example.archiving.util.MyLogger;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {
    final public MyLogger logger = new MyLogger(this.getClass().getName());

    public String createBookmark(KeywordSearch keywordSearch) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession()) {
            BookmarkMapper mapper = session.getMapper(BookmarkMapper.class);
            int count = mapper.insertBookmark(keywordSearch);
            logger.info(count + " bookmark inserted");
            String uuid = mapper.checkLastID();
            session.commit();
            return uuid;
        }
    }

    public KeywordSearch readOneBookmark(String uuid) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession()) {
            BookmarkMapper mapper = session.getMapper(BookmarkMapper.class);
            return mapper.getOneBookmark(uuid);
        }
    }
}
