package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

public class SVGParser {

	private org.jdom2.Document document;
	private int numPage;
	private Element arbre[] = new Element[4];
	private static Namespace n[] = {
		Namespace.getNamespace("", "http://www.sony.com/notepad"),
		Namespace.getNamespace("", "http://www.w3.org/2000/svg")};
	private String pdfFile;

		
	public SVGParser(String input, String pdfFile, int numPage)
	{
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			document = sxb.build(new File(input));
		}
		catch(Exception e)
		{
			System.out.println("Le fichier XML n'a pas pu être ouvert");
		}
		this.numPage = numPage;
		this.pdfFile = pdfFile;
		
		//on parcourt l'arborescence pour obtenir les points
		arbre[0] = document.getRootElement();
		arbre[1] = arbre[0].getChild("drawing", n[0]);
		arbre[2] = arbre[1].getChild("page", n[0]);
		arbre[3] = arbre[2].getChild("svg", n[1]);
	}

	public String getPdfFile() {
		return pdfFile;
	}

	public ArrayList<String> getPoints()
	{
		Iterator<?> i = arbre[3].getChildren("polyline", n[1]).iterator();
		ArrayList<String> allPoints = new ArrayList<String>();
		while (i.hasNext())
		{
			Element courant = (Element)i.next();
			String points = courant.getAttribute("points").getValue();
			allPoints.add(points);
		}
		return allPoints;
	}

	public int getNumPage() {
		return numPage;
	}
}
