package model;

import java.io.File;

import org.jdom2.input.SAXBuilder;

/**
 * Parser un fichier représentant une annotation au clavier
 */
public class MemoParser {

	private static org.jdom2.Document document;
	private String note;
	private int numPage;
	private String pdfFile;
	private String txtAnnote;

	/**
	 * Constructeur
	 * @param input Le fichier contenant l'annotation
	 * @param pdfFile Le PDF à annoter
	 * @param numPage La page où se trouve l'annotation 
	 * @param txtAnnote La portion de texte qui est annotée
	 */
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

	/**
	 * 
	 * @return Le texte de la note
	 */
	public String getNote() {
		return this.note;
	}

	/**
	 * 
	 * @return La page où se trouve l'anotation
	 */
	public int getNumPage() {
		return numPage;
	}

	/**
	 * 
	 * @return Le fichier PDF à annoter
	 */
	public String getPdfFile() {
		return pdfFile;
	}

	/**
	 * 
	 * @return la portion de texte annotée
	 */
	public String getTxtAnnote() {
		return txtAnnote;
	}
}