package model;

import java.util.ArrayList;
import java.util.List;

import base.BD;
import base.Book;

public class EbookModel {
	/* input -> output */
	private List<String> files;
	private List<Book> books;
	private EbookListener listener;
	private String inDir, outDir;
	private BD bd;

	public EbookModel() throws Exception {
		this("/media/READER/", "/tmp/");
	}
	
	public EbookModel(String inDir, String outDir) throws Exception {
		this.inDir = inDir;
		this.outDir = outDir;
		files = new ArrayList<String>();
		bd = new BD(this.inDir);
		
		books = new ArrayList<Book>(bd.getAllBooks());
		
		for (Book b : books) {
			files.add(b.getId()+"|"+b.getTitle());
		}
	}

	
	public void buildOutput(String input) {
		int id;

		id = Integer.decode(input);
		/* XXX check if id exists */

		try {
			new Export(id, inDir, outDir, bd);
		}
		catch(Exception e) {
			System.err.println("buildOutput: "+e);
		}
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
	
	public void fireEbookChanged(String output) {
		listener.ebookChanged(new EbookChangedEvent(this, output));
	}
	
	protected void finalize() throws Throwable {
		bd.closeBd();
		System.err.println("db closed");
	}
}
