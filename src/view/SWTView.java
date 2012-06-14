package view;

import java.io.IOException;
import java.util.List;

import model.EbookChangedEvent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import controller.EbookController;

public class SWTView extends EbookView implements Listener {
	public static String pdfViewer =  "mupdf";
	org.eclipse.swt.widgets.List list, listConverted;
	private Display display;
	private Shell shell;
	List<String> files;
	Label label;

	public SWTView(EbookController controller) {
		this(controller, null);
	}

	public SWTView(EbookController controller, List<String> files) {
		super(controller);
		buildView(files);
	}

	public void buildView(List<String> files) {
		this.files = files;
		
		display = new Display();
		shell = new Shell(display);
		shell.setText("Ebook converter");
		shell.setSize(300, 400);

		list = new org.eclipse.swt.widgets.List(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		list.setBounds(40, 20, 220, 100);
		label = new Label(shell, SWT.BORDER);
		label.setBounds(60, 130, 160, 25);
		label.setText("Converted:");
		listConverted = new org.eclipse.swt.widgets.List(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		listConverted.setBounds(40, 170, 220, 100);
		updateList();

	    list.addListener(SWT.DefaultSelection, this);
	    listConverted.addListener(SWT.DefaultSelection, new Listener() {
			public void handleEvent(Event event) {
				try {
					int i = listConverted.getSelectionIndices()[0];
					System.err.println(i);
					Runtime.getRuntime().exec(pdfViewer+" "+listConverted.getItem(i));
//					System.err.println("'"+pdfViewer+" "+listConverted.getItem(i)+"' launched");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}    	
	    });
	}

	private void updateList() {
		list.removeAll();
		for (String file : files)
			list.add(file);
	}

	public void ebookChanged(EbookChangedEvent e) {
		listConverted.add(e.getNewOutput());
	}

	public void handleEvent(Event event) {			
		String input;
		
		for (int i : list.getSelectionIndices()) {
			input = list.getItem(i).split(" ")[0];
			getController().notifyEbookChanged(input);
		}
	}

	public void display() {
		shell.open();
	    while (!shell.isDisposed()) {
	        if (!display.readAndDispatch())
	          display.sleep();
	    }
	}

	public void close() {
		display.dispose();
	}
}
