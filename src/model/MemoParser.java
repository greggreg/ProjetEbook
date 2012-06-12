package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.input.SAXBuilder;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class MemoParser {

	private static org.jdom2.Document document;
	private String note;
	private int numPage;
	private String pdfFile;
	private String txtAnnote;

	public MemoParser(String input, String pdfFile, int numPage, String txtAnnote)
	{
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(input));
		}
		catch(Exception e) {
			System.out.println("erreur blabla...");
		}
		this.numPage = numPage;
		this.pdfFile = pdfFile;
		this.txtAnnote = txtAnnote;
		this.note = document.getRootElement().getChild("text").getText();
	}

	public String getNote() {
		return this.note;
	}

	public int getNumPage() {
		return numPage;
	}

	public String getPdfFile() {
		return pdfFile;
	}

	public String getTxtAnnote() {
		return txtAnnote;
	}

	public void addNotes() throws IOException, DocumentException {
		PdfReader reader = new PdfReader(getPdfFile());
		int n = reader.getNumberOfPages();
		Document doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(getPdfFile()));
		doc.open();
		PdfContentByte cb = writer.getDirectContent();

		for (int i = 1; i <= n; i++) {
			doc.newPage();
			PdfImportedPage p = writer.getImportedPage(reader, i);
			cb.addTemplate(p, 0, 0);

			if (i == getNumPage())
			{
				doc.add(new Annotation("Note", getNote(), 0, 0, 50, 50));
				System.out.println("ajout d'une super note page"+getNumPage());
			}
		}
		doc.close();
	}
}