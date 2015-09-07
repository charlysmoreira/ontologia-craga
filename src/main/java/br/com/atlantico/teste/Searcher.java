package br.com.atlantico.teste;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Searcher {
 
    private IndexSearcher searcher;
    private QueryParser contentQueryParser;

    public Searcher(String indexDir) throws IOException {
        // open the index directory to search
        searcher = new IndexSearcher(IndexReader.open(FSDirectory.open(new File(indexDir))));
        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);

        // defining the query parser to search items by content field.
        contentQueryParser = new QueryParser(Version.LUCENE_36, IndexItem.CONTENT, analyzer);
    }

    
    /**
      * This method is used to find the indexed items by the content.
      * @param queryString - the query string to search for
      */
    public int findByContent(String queryString, int numOfResults) throws ParseException, IOException {
        // create query from the incoming query string.
        Query query = contentQueryParser.parse(queryString);
         // execute the query and get the results
        ScoreDoc[] queryResults = searcher.search(query, numOfResults).scoreDocs;
        
        if(queryResults.length>0)
         return 1;
        else 
         return 0;
        
    }

    public void close() throws IOException {
        searcher.close();
    }
}