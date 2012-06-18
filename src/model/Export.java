package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import base.BD;
import base.Freehand;
import base.Note;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.TextMarginFinder;

/**
 * Récupère toutes les notes associées à un PDF
 * Insert ces notes dans le PDF
 * 
 */
public class Export {

	private int id_pdf;
	private ArrayList<Freehand> freehand = new ArrayList<Freehand>();
	private ArrayList<Note> notes = new ArrayList<Note>();
	private String pathEbook, pathSortie;
	private static final String endComposedPdf = "_1col.pdf";
	private BD bd;
	private String fn;

	/**
	 * Constructeur de la classe
	 * @param id_pdf L'indentifiant du PDF
	 * @param pathEbook Le chemin vers l'ebook (par défaut /media/READER)
	 * @param pathSortie Le chemin du fichier PDF en sortie
	 * @param bd La base de données
	 * @param fn Le nom du fichier de sortie
	 * @throws DocumentException
	 * @throws IOException
	 */
	public Export(int id_pdf, String pathEbook, String pathSortie, BD bd, String fn) throws DocumentException, IOException
	{
		this.bd = bd;
		this.id_pdf = id_pdf;
		this.pathEbook = pathEbook;
		this.pathSortie = pathSortie;
		this.fn = fn;
		freehand = bd.getBookFreehands(id_pdf);
		notes = bd.getBookNotes(id_pdf);
		exporter();
	}

	/**
	 * 
	 * @return l'identifiant du PDF
	 */
	public int getId_pdf()
	{
		return id_pdf;
	}

	/**
	 * 
	 * @return le chemin de sortie 
	 */
	public String getPathSortie() {
		return pathSortie;
	}

	/**
	 * Modifier le chemin de sortie
	 * @param pathSortie
	 */
	public void setPathSortie(String pathSortie) {
		this.pathSortie = pathSortie;
	}

	/**
	 * 
	 * @return le chemin vers l'ebook
	 */
	public String getPathEbook() {
		return pathEbook;
	}

	/**
	 * 
	 * @return la liste des annotations en freehand
	 */
	public ArrayList<Freehand> getFreehand() {
		return freehand;
	}

	/**
	 * 
	 * @return la lsite des annotations au clavier
	 */
	public ArrayList<Note> getNotes() {
		return notes;
	}

	/**
	 * 
	 * @return la base de données
	 */
	public BD getBD()
	{
		return bd;
	}


	/**
	 * Construit les listes stockant les annotations
	 * Appelle addAllNotes() pour écrire dans le PDF 
	 * @throws DocumentException
	 * @throws IOException
	 */
	private void exporter() throws DocumentException, IOException
	{
		ArrayList<SVGParser> svg = new ArrayList<SVGParser>();
		ArrayList<MemoParser> memo = new ArrayList<MemoParser>();
		String pdfFile = pathEbook+getBD().getBook(id_pdf).getFile_path();

		for (int i=0; i<getFreehand().size(); i++)
		{
			String input = pathEbook+getFreehand().get(i).getFile_path();
			int numPage = (int) getFreehand().get(i).getPage();

			svg.add(new SVGParser(input, pdfFile, numPage+1));
		}

		for (int i=0; i<getNotes().size(); i++)
		{
			if (getNotes().get(i).getMarkup_type() == 11)
			{
				String input = pathEbook+getNotes().get(i).getFile_path();
				int numPage = (int) getNotes().get(i).getPage();
				String txtAnnote = getNotes().get(i).getMarked_text();
				memo.add(new MemoParser(input, pdfFile, numPage+1, txtAnnote));
			}
		}
		addAllNotes(svg, memo, pdfFile);
	}

	/**
	 * Annote le PDF
	 * @param svg La liste des annotations en freehand
	 * @param memo La liste des annotations au clavier
	 * @param pdfFile Le fichier PDF à annoter
	 * @throws IOException
	 * @throws DocumentException
	 */
	private void addAllNotes(ArrayList<SVGParser> svg, ArrayList<MemoParser> memo, String pdfFile)
			throws IOException, DocumentException
	{
		PdfReader reader = new PdfReader(pdfFile);
		Rectangle rect = reader.getPageSize(1);
		float height = rect.getHeight();
		float width = rect.getWidth();
		

		int col = 1;
		if (pdfFile.endsWith(endComposedPdf))
			col = 2;

		int n = reader.getNumberOfPages();
		Document doc = new Document(new Rectangle(width, height));
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(getPathSortie()+"/"+fn));
		doc.open();
		PdfContentByte cb = writer.getDirectContent();
		TextMarginFinder finder;
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);

		for (int i=1; i<=n; i+=col) 
		{
			doc.newPage();
			PdfImportedPage p = writer.getImportedPage(reader, i);
			finder = parser.processContent(i, new TextMarginFinder());
			cb.addTemplate(p, 0, 0);
			float rap = 1 - finder.getWidth() / width;
			
			for (int l=0; l<svg.size(); l++)
			{
				int numPage = svg.get(l).getNumPage();
				if ((i == numPage && col==1) || (i == numPage && col==2) || (i == numPage-1 && col == 2))
				{
					ArrayList<String> allPoints = svg.get(l).getPoints();
					for (int j=0; j<allPoints.size(); j++)
					{
						String points = allPoints.get(j);
						String coord[] = points.split(" ");
						for (int k=0; k<coord.length; k++)
						{	
							float x,y;
							if (numPage%col == 0 && col == 2)
							{
								x = finder.getLlx() + Integer.parseInt(coord[k].split(",")[0])*rap;
								y = height*rap + finder.getLly()*rap - Integer.parseInt(coord[k].split(",")[1])*rap;
							}
							else if (numPage%col == 1 && col == 2)
							{
								x = finder.getLlx() + Integer.parseInt(coord[k].split(",")[0])*rap;
								y = height - finder.getLly() - Integer.parseInt(coord[k].split(",")[1])*rap;
							}
							else
							{
								x = Integer.parseInt(coord[k].split(",")[0]);
								y = height - Integer.parseInt(coord[k].split(",")[1]);
							}
							if (k==0) cb.moveTo(x, y);
							else cb.lineTo(x, y);
						}
					}
					cb.stroke();
					System.out.println("ajout d'une note page" + numPage);
				}
			}

			int dx = 0;
			for(int j=0; j<memo.size(); j++)
			{
				if (i == memo.get(j).getNumPage())
				{
					doc.add(new Annotation(memo.get(j).getTxtAnnote(), memo.get(j).getNote(), dx, 0, 50, 50));
					System.out.println("ajout d'une note page" + memo.get(j).getNumPage());
					dx += 20;
				}
			}
		}
		doc.close();
	}
}