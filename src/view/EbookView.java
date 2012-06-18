package view;

import controller.EbookController;
import model.EbookListener;

/**
 * Abstract class to represent a view. only the construtor is implemented
 * here.
 */
public abstract class EbookView implements EbookListener{
	private EbookController controller;
	
	public EbookView(EbookController controller) {
		this.controller = controller;
	}

	public final EbookController getController() {
		return this.controller;
	}
	
	/**
	 * how to display informations of the model.
	 */
	public abstract void display();
	/**
	 * what's happening when closing the view.
	 */
	public abstract void close();
}
