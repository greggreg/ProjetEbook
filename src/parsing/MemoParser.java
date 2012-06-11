package parsing;

import java.io.File;
import java.io.FileOutputStream;

import org.jdom2.input.SAXBuilder;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class MemoParser {

	private static org.jdom2.Document document;
	private String note;
	private int numPage;
	private static final String OUTPUT = "res/result.pdf";

	public MemoParser(String memoFile, int numPage)
	{
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(memoFile));
		}
		catch(Exception e) {
			System.out.println("erreur blabla...");
		}
		this.numPage = numPage;
		this.note = document.getRootElement().getChild("text").getText();
	}

	private String getNote() {
		return this.note;
	}

	private int getNumPage() {
		return numPage;
	}

	public static void main(String[] args) throws Exception {
		String pdfInput = "res/ecmfa_2012_1col.pdf";
		MemoParser memo[] = {
				new MemoParser("res/1338485390326.memo", 3),
				new MemoParser("res/1338486072931.memo", 5)
		};
		PdfReader reader = new PdfReader(pdfInput);
		int n = reader.getNumberOfPages();
		Document doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(OUTPUT));
		doc.open();
		PdfContentByte cb = writer.getDirectContent();

		for (int i = 1; i <= n; i++) {
			doc.newPage();
			PdfImportedPage p = writer.getImportedPage(reader, i);
			cb.addTemplate(p, 0, 0);

			for(int j=0; j<memo.length; j++)
			{
				if (i == memo[j].getNumPage())
				{
					doc.add(new Annotation("Note", memo[j].getNote(), 0, 0, 50, 50));
					System.out.println("ajout d'une super note page"+memo[j].getNumPage());
				}
			}
		}
		doc.close();
	}
}