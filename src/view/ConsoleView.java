package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import model.EbookChangedEvent;

import controller.EbookController;

/**
 * Console interface.
 */
public class ConsoleView extends EbookView implements PropertyChangeListener {
	List<String> files;
	String input;

	public ConsoleView(EbookController controller) {
		this(controller, null);
	}

	/**
	 * @param controller the controller instance
	 * @param files list of ebook
	 */
	public ConsoleView(EbookController controller, List<String> files) {
		super(controller);
		buildView(files);
	}
	
	/**
	 * @param files files of ebook
	 */
	public void buildView(List<String> files) {
		this.files = files;
	}

	public void ebookChanged(EbookChangedEvent e) {
		System.out.println(e.getNewOutput() + " build from " + input);
	}

	/**
	 * @param arg0 contains the output name of the last converted ebook
	 */
	public void propertyChange(PropertyChangeEvent arg0) {
		input = (String)arg0.getNewValue();
		getController().notifyEbookChanged(input);
	}

	public void display() {
		BufferedReader in ;
		String line;
		boolean end;
		
		in = new BufferedReader(new InputStreamReader(System.in));
		end = false;

		System.out.println("Ebook converter; type !list to list ebooks, !quit to leave");

		while (end == false) {
			try {
				line = in.readLine();
				if (line.startsWith("!quit"))
					end = true;
				else if (line.startsWith("!list")) {
					for (String file : files)
						System.out.println(file);
				}
				else if (line.startsWith("!export")) {
					try {
						String tmps = line.split(" ")[1];
						Integer.decode(tmps);
						propertyChange(new PropertyChangeEvent(this, "input", input, tmps));
					}
					catch(Exception e) {
						System.err.println("error: !export <id>");
					}
				}
			}
			catch(Exception e) {
				System.err.println(e);
			}
		}
	}

	public void close() {
		System.out.println("Done.");
	}
}
