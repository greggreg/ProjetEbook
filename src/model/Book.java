package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

public class Book {

	private int id_pdf;
	private ArrayList<Freehand> freehand = new ArrayList<Freehand>();
	private ArrayList<Note> notes = new ArrayList<Note>();
	BD bd = new BD("/home/putz/Documents/PROJETS/Ebook/EBOOK_Deantony/READER/Sony_Reader/database/books.db");
	private static String path = "/home/putz/Documents/PROJETS/Ebook/EBOOK_Deantony/READER/";
	private String pathEbook, pathSortie;
	
	public Book(int id_pdf, String pathEbook, String pathSortie) throws DocumentException, IOException
	{
		this.id_pdf = id_pdf;
		this.pathEbook = pathEbook;
		this.pathSortie = pathSortie;
		freehand = bd.getBookFreehands(id_pdf);
		notes = bd.getBookNotes(id_pdf);
		exporter();
	}

	public int getId_pdf()
	{
		return id_pdf;
	}

	public String getPathSortie() {
		return pathSortie;
	}

	public void setPathSortie(String pathSortie) {
		this.pathSortie = pathSortie;
	}

	public String getPathEbook() {
		return pathEbook;
	}

	public ArrayList<Freehand> getFreehand() {
		return freehand;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public BD getBD()
	{
		return bd;
	}


	public void exporter() throws DocumentException, IOException
	{
		Scanner in = new Scanner(System.in);
		System.out.println("Entrez un ID");
		int id_pdf = in.nextInt();
		ArrayList<SVGParser> svg = new ArrayList<SVGParser>();
		ArrayList<MemoParser> memo = new ArrayList<MemoParser>();

		for (int i=0; i<getFreehand().size(); i++)
		{
			String input = path+getFreehand().get(i).getFile_path();
			String pdfFile = path+getBD().getBook(id_pdf).getFile_path();
			int numPage = (int) getFreehand().get(i).getPage();

			svg.add(new SVGParser(input, pdfFile, numPage+1));
		}

		for (int i=0; i<getNotes().size(); i++)
		{
			if (getNotes().get(i).getMarkup_type() == 11)
			{
				String input = path+getNotes().get(i).getFile_path();
				String pdfFile = path+getBD().getBook(id_pdf).getFile_path();
				int numPage = (int) getNotes().get(i).getPage();
				String txtAnnote = getNotes().get(i).getMarked_text();

				memo.add(new MemoParser(input, pdfFile, numPage+1, txtAnnote));
			}
		}
		addAllNotes(svg, memo);
	}


	public void addAllNotes(ArrayList<SVGParser> svg, ArrayList<MemoParser> memo) throws IOException, DocumentException
	{
		PdfReader reader = new PdfReader(svg.get(0).getPdfFile());
		Rectangle rect = reader.getPageSize(1);
		float height = rect.getHeight();
		float width = rect.getWidth();

		int n = reader.getNumberOfPages();
		Document doc = new Document(new Rectangle(width, height));
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(getPathSortie() + "output.pdf"));
		doc.open();
		PdfContentByte cb = writer.getDirectContent();

		for (int i=1; i<=n; i++) 
		{
			doc.newPage();
			PdfImportedPage p = writer.getImportedPage(reader, i);
			cb.addTemplate(p, 0, 0);

			for (int l=0; l<svg.size(); l++)
			{
				if (i == svg.get(l).getNumPage())
				{
					ArrayList<String> allPoints = svg.get(l).getPoints();
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
					System.out.println("ajout d'une super note page" + svg.get(l).getNumPage());
				}
			}

			int dx = 0;
			for(int j=0; j<memo.size(); j++)
			{
				if (i == memo.get(j).getNumPage())
				{
					doc.add(new Annotation(memo.get(j).getTxtAnnote(), memo.get(j).getNote(), dx, 0, 50, 50));
					System.out.println("ajout d'une super note page"+memo.get(j).getNumPage());
					dx += 20;
				}
			}
		}
		doc.close();
	}
}