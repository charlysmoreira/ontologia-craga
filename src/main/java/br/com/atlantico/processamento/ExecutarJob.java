package br.com.atlantico.processamento;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.lucene.queryParser.ParseException;
import org.springframework.stereotype.Component;

import br.com.atlantico.teste.SimplePDFSearch;

@Component
public class ExecutarJob {

	public void processarDownload() {
		System.out.println("INICIANDO PROCESSO...");
		baixarPdfPelaURL("http://www.brasilsus.com.br/images/portarias/setembro2015/dia04/res1249.pdf");
		System.out.println("FINALIZANDO PROCESSO...");
	}
	
	public static File baixarPdfPelaURL(String stringUrl) {
		try {
			URL url = new URL(stringUrl);
			InputStream is = url.openStream();
			FileOutputStream fos = new FileOutputStream("teste.pdf");
			int umByte = 0;
			while ((umByte = is.read()) != -1){
				fos.write(umByte);
			}
			is.close();
			fos.close();
			return new File("teste.pdf");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		   
		return null;
	}
	
	public void processarArquivo() throws IOException, ParseException {
		System.out.println("AQUI PROCESSA O ARQUIVO");
		SimplePDFSearch.executar();
	}
}