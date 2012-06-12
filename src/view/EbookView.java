package view;

import controller.EbookController;
import model.EbookListener;

public abstract class EbookView implements EbookListener{
	private EbookController controller;
	
	public EbookView(EbookController controller) {
		this.controller = controller;
	}

	public final EbookController getController() {
		return this.controller;
	}
	
	public abstract void display();
	public abstract void close();
}
