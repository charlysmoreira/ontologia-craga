package br.com.atlantico.processamento;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

import br.com.atlantico.model.Pessoa;

public class CustomWriter implements ItemWriter<Pessoa> {
	
	public void write(List<? extends Pessoa> items) throws Exception {
		baixarPdfPelaURL("http://www.brasilsus.com.br/images/portarias/setembro2015/dia04/res1249.pdf");
	}
	
	public static File baixarPdfPelaURL(String stringUrl) {
		try {
			//Encapsula a URL num objeto java.net.URL
			URL url = new URL(stringUrl);
			//Queremos o arquivo local com o mesmo nome descrito na URL
			//Lembrando que o URL.getPath() ira retornar a estrutura 
			//completa de diretorios e voce deve tratar esta String
			//caso nao deseje preservar esta estrutura no seu disco local.
			String nomeArquivoLocal = url.getPath();
			
			//Cria streams de leitura (este metodo ja faz a conexao)...
			InputStream is = url.openStream();
			
			//... e de escrita.
			FileOutputStream fos = new FileOutputStream("teste.pdf");

			//Le e grava byte a byte. Voce pode (e deve) usar buffers para
			//melhor performance (BufferedReader).
			int umByte = 0;
			while ((umByte = is.read()) != -1){
				fos.write(umByte);
			}

			//Nao se esqueca de sempre fechar as streams apos seu uso!
			is.close();
			fos.close();

			//apos criar o arquivo fisico, retorna referencia para o mesmo
			return new File("teste.pdf");
			
		} catch (Exception e) {
			//Lembre-se de tratar bem suas excecoes
			//Aqui so vamos mostrar o stack no stderr.
			e.printStackTrace();
		}
		   
		return null;
	}

}