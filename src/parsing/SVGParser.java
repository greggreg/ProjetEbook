package parsing;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class SVGParser {

	private org.jdom2.Document document;
	private int numPage;
	private Element arbre[] = new Element[4];
	private static Namespace n[] = {
		Namespace.getNamespace("", "http://www.sony.com/notepad"),
		Namespace.getNamespace("", "http://www.w3.org/2000/svg")};
	private static final String OUTPUT = "res/result.pdf";


	public SVGParser(String filename, int numPage)
	{
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			document = sxb.build(new File(filename));
		}
		catch(Exception e)
		{
			System.out.println("Le fichier XML n'a pas pu être ouvert");
		}
		this.numPage = numPage;

		//on parcourt l'arborescence pour obtenir les points
		arbre[0] = document.getRootElement();
		arbre[1] = arbre[0].getChild("drawing", n[0]);
		arbre[2] = arbre[1].getChild("page", n[0]);
		arbre[3] = arbre[2].getChild("svg", n[1]);

		getPoints();
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

//	public int getHeight()
//	{
//		return Integer.parseInt(arbre[1].getAttribute("height").getValue());
//	}
//
//	public int getWidth()
//	{
//		return Integer.parseInt(arbre[1].getAttribute("width").getValue());
//	}

	public int getNumPage() {
		return numPage;
	}


	public static void main(String[] args) throws Exception
	{
		String pdfInput = "res/tigli.pdf";
		SVGParser svg[] = {
				new SVGParser("res/1339415145122.svg", 1),
				new SVGParser("res/1339415171387.svg", 2),
				new SVGParser("res/1339415193084.svg", 3),
				new SVGParser("res/1339415217433.svg", 4)
		};

		PdfReader reader = new PdfReader(pdfInput);
		Rectangle rect = reader.getPageSize(1);
		float height = rect.getHeight();
		
		int n = reader.getNumberOfPages();
		Document doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(OUTPUT));
		doc.open();
		PdfContentByte cb = writer.getDirectContent();

		for (int i=1; i<=n; i++) 
		{
			doc.newPage();
			PdfImportedPage p = writer.getImportedPage(reader, i);
			cb.addTemplate(p, 0, 0);

			for (int l=0; l<svg.length; l++)
			{
				if (i == svg[l].getNumPage())
				{
					ArrayList<String> allPoints = svg[l].getPoints();
					for (int j=0; j<allPoints.size(); j++)
					{
						String points = allPoints.get(j);
						String coord[] = points.split(" ");
						for (int k=0; k<coord.length; k++)
						{		
							float x = Integer.parseInt(coord[k].split(",")[0]);
							float y = height - Integer.parseInt(coord[k].split(",")[1]);
							if (k==0) cb.moveTo(x, y);
							else cb.lineTo(x, y);
						}
					}
					cb.stroke();
					System.out.println("ajout d'une super note page" + svg[l].getNumPage());
				}
			}
		}
		doc.close();
	}

}
