package controller;

import view.EbookView;
import view.SWTView;
import view.ConsoleView;
import model.EbookModel;

/**
 * Controller that makes communicates EbookView and EbookModel
 */
public class EbookController {
	public enum viewType {
		Console,
		SWT
	};
	private EbookView view;
	private EbookModel model;
	
	/**
	 * @param model the model instance to use
	 * @param type select the view (Console or Graphical).
	 */
	public EbookController(EbookModel model, viewType type) {
		this.model = model;
		
		if (type == viewType.Console)
			view = new ConsoleView(this, model.getFiles());
		else
			view = new SWTView(this, model.getFiles());
		
		addListeners();
	}
	
	public void addListeners() {
		model.addEbookListener(view);
	}
	
	public void displayView() {
		view.display();
	}
	
	public void closeView() {
		view.close();
	}
	
	public void notifyEbookChanged(String input) {
		model.buildOutput(input);
	}
}
