package org.eleicoesabertas.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ArquivoUtil {
	public static void baixaArquivo(String arquivoURL, String arquivoDestino)
			throws MalformedURLException, IOException {
		java.io.BufferedInputStream in = new java.io.BufferedInputStream(
				new URL(arquivoURL).openStream());
		java.io.FileOutputStream fos = new FileOutputStream(
				arquivoDestino);
		java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
		byte[] data = new byte[1024];
		int x = 0;
		while ((x = in.read(data, 0, 1024)) >= 0) {
			bout.write(data, 0, x);
		}
		bout.close();
		in.close();

	}
}
