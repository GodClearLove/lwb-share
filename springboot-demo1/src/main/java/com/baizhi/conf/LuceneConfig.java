package com.baizhi.conf;

import com.baizhi.luceneDao.LuceneDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//将luceneDao对象交给工厂管理
@Configuration
public class LuceneConfig {
    @Bean
    public LuceneDao getLuceneDao() {
        return new LuceneDao();
    }

}
