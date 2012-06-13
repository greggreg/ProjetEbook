package model;

import java.io.File;

import org.jdom2.input.SAXBuilder;

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
}