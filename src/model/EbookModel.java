package model;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.ArrayList;
import com.itextpdf.text.Annotation;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.BaseColor;

import base.*;

public class EbookModel {
	/* input -> output */
	private List<String> files;
	private List<Book> books;
	private EbookListener listener;
	private String inDir, outDir;
	BD bd;

	public EbookModel() throws Exception {
		this("/media/hd/Sony_Reader/database/books.db", "/tmp/");
	}
	
	public EbookModel(String inDir, String outDir) throws Exception {
		this.inDir = inDir;
		this.outDir = outDir;
		files = new ArrayList<String>();
		bd = new BD(inDir);
		
		books = new ArrayList<Book>(bd.getAllBooks());
		
		for (Book b : books) {
			files.add(b.getId()+"|"+b.getTitle());
		}
	}

	public void createOutput(String input, String output) throws Exception {
		PdfReader reader;
		PdfWriter writer;
		Document doc;
		PdfContentByte cb;
		int n;

		reader = new PdfReader(input);
		doc = new Document();
		writer = PdfWriter.getInstance(doc, new FileOutputStream(output));

		n  = reader.getNumberOfPages();
		doc.open();
		cb = writer.getDirectContent();
		
		for (int i = 0; i < n; i++) {
			doc.newPage();

			/* first page is 1, not 0, so i+1 */
			PdfImportedPage p = writer.getImportedPage(reader, i+1);
			cb.addTemplate(p, 0, 0);
			
			if (i == 0) {
				cb.saveState();
				cb.setColorStroke(BaseColor.GREEN);
				cb.moveTo(0,0);
				cb.lineTo(50,50);
				cb.lineTo(75,200);
				cb.stroke();
				cb.restoreState();
				doc.add(new Annotation("hello", "hello, world", 0, 0, 50, 50));
			}
		}
		doc.close();

		fireEbookChanged(output);
	}
	
	public void buildOutput(String input) {
		Export ex;
		int id;

		id = Integer.decode(input);
		/* XXX check if id exists */

		try {
			ex = new Export(id, "/media/hd/", outDir, bd);
//			ex.exporter();
		}
		catch(Exception e) {
			System.err.println("buildOutput: "+e);
		}
		
		/*
		try {
			if (files.contains(input))
				createOutput(input, outDir+(new File(input)).getName());
		}
		catch(Exception e) {
			System.err.println("buildOutput: " +e);
		}*/
	}
	
	public List<String> getFiles() {
		return files;
	}
	
	public void addEbookListener(EbookListener listener) {
		this.listener = listener;
	}
	
	public void removeEbookListener(EbookListener listener) {
		listener = null;
	}
	
	void fireEbookChanged(String output) {
		listener.ebookChanged(new EbookChangedEvent(this, output));
	}
}
