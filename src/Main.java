import model.EbookModel;
import controller.EbookController;

public class Main {
	public static void main(String[] args) {
		EbookController.viewType type;
		EbookController controller;
		EbookModel model;

		type = EbookController.viewType.SWT;
		if (args.length > 0)
			if (args[0].equals("-c"))
				type = EbookController.viewType.Console;
			else
				System.err.println("unknown modifier '"+args[0]+"'");
		try {
			model = new EbookModel("/media/READER/Sony_Reader/database/books.db", "/tmp/");
			controller = new EbookController(model, type);
			controller.displayView();
			controller.closeView();
		}
		catch(Exception e) {
			System.err.println(e);
		}

	}
}
