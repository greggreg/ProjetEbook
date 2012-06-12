package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import model.EbookChangedEvent;

import controller.EbookController;

public class ConsoleView extends EbookView implements PropertyChangeListener {
	List<String> files;
	String input;

	public ConsoleView(EbookController controller) {
		this(controller, null);
	}

	public ConsoleView(EbookController controller, List<String> files) {
		super(controller);
		
		buildView(files);
	}
	
	public void buildView(List<String> files) {
		this.files = files;
	}

	public void ebookChanged(EbookChangedEvent e) {
		System.out.println(e.getNewOutput() + " build from " + input);
	}

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
						int tmp = Integer.decode(tmps);
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
