package com.baizhi.luceneDao;

import com.baizhi.entity.Banner;
import com.baizhi.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LuceneDao {
    //创建索引
    public void createIndex(Banner banner){
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromBann = getDocFromBann(banner);
        try {
            indexWriter.addDocument(docFromBann);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }

    //删除索引
    public void deleteIndex(Integer id){
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        try {
            indexWriter.deleteDocuments(new Term(id+""));
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }
    //修改索引
    public void updateIndex(Banner banner){
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document docFromBann = getDocFromBann(banner);
        try {
            indexWriter.updateDocument(new Term("id",banner.getId()+""),docFromBann);
            LuceneUtil.commit(indexWriter);
        } catch (IOException e) {
            e.printStackTrace();
            LuceneUtil.rollback(indexWriter);
        }
    }
    //查询
    public List<Banner> searcherIndex(String params){
        Query query = new TermQuery(new Term("description", params));

        //高亮
        Formatter formatter = new SimpleHTMLFormatter("<font color:'red'>", "</font>");
        Highlighter highlighter = new Highlighter(formatter,new QueryScorer(query));

        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        List<Banner> list = null;
        try {
            TopDocs topDocs = indexSearcher.search(query, 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            list = new ArrayList<>();
            for (int i = 0; i < scoreDocs.length; i++) {
                ScoreDoc scoreDoc = scoreDocs[i];
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                Banner bannFromDoc = getBannFromDoc(document);
                list.add(bannFromDoc);

                try {
                    String bestFragment = highlighter.getBestFragment(new IKAnalyzer(), "description", document.get("description"));
                    System.out.println("=-=-=-=-="+bestFragment);
                } catch (InvalidTokenOffsetsException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    //将实体类转换为document对象
    public Document getDocFromBann(Banner banner){
        Document document = new Document();
        document.add(new IntField("id",banner.getId(), Field.Store.YES));
        document.add(new TextField("title", banner.getTitle(), Field.Store.YES));
        document.add(new TextField("imgPath", banner.getImgPath(), Field.Store.YES));
        String pubDate = banner.getPubDate()+"";
        document.add(new TextField("pubDate", pubDate, Field.Store.YES));
        document.add(new IntField("status",banner.getStatus(), Field.Store.YES));
        document.add(new TextField("description", banner.getDescription(), Field.Store.YES));
        return document;
    }

    //将document对象转换为实体类
    public Banner getBannFromDoc(Document  document){
        Banner banner = new Banner();
        banner.setId(Integer.valueOf(document.get("id")));
        banner.setTitle(document.get("title"));
        banner.setImgPath(document.get("imgPath"));
        banner.setStatus(Integer.valueOf(document.get("status")));
        banner.setDescription(document.get("description"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(document.get("pubDate"));
            banner.setPubDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return banner;
    }


}
