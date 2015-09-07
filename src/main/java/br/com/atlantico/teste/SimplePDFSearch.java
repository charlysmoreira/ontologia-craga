package br.com.atlantico.teste;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.queryParser.ParseException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

 public class SimplePDFSearch {
     // location where the index will be stored.
     private static final String INDEX_DIR = "src/main/resources/index";
     private static final int DEFAULT_RESULT_SIZE = 100;

     public static void executar() throws IOException, ParseException {

         File pdfFile = new File("teste.pdf");
         IndexItem pdfIndexItem = index(pdfFile);

         // creating an instance of the indexer class and indexing the items
         Indexer indexer = new Indexer(INDEX_DIR);
         indexer.index(pdfIndexItem);
         indexer.close();

         // creating an instance of the Searcher class to the query the index
         Searcher searcher = new Searcher(INDEX_DIR);
         int result = searcher.findByContent("vigor", DEFAULT_RESULT_SIZE);
         print(result);
         searcher.close();
     }
     
     //Extract text from PDF document
     public static IndexItem index(File file) throws IOException {
         PDDocument doc = PDDocument.load(file);
         String content = new PDFTextStripper().getText(doc);
         doc.close();
         return new IndexItem((long)file.getName().hashCode(), file.getName(), content);
     }

    //Print the results
     private static void print(int result) {
      if(result==1)
         System.out.println("Palavra encontrada.");
      else
      System.out.println("Nao encontrou a palavra.");

     }
 }