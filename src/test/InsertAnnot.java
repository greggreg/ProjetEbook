package test;

import java.io.FileOutputStream;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class InsertAnnot {
	public static final String OUTPUT = "res/result.pdf";
	public static final String INPUT = "res/input.pdf";

	public static void main(String[] args) throws Exception {
		PdfReader reader = new PdfReader(INPUT);
		int n = reader.getNumberOfPages();
		Document doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(OUTPUT));
		doc.open();
		PdfContentByte cb = writer.getDirectContent();

		for (int i = 1; i <= n; i++) {
			doc.newPage();
			PdfImportedPage p = writer.getImportedPage(reader, i);
			cb.addTemplate(p, 0, 0);

			if (i == 1)
				doc.add(new Annotation("hello", "hello, world", 0, 0, 50, 50));
		}
		doc.close();
	}
}
