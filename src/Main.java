import model.EbookModel;
import controller.EbookController;

public class Main {
	public static void main(String[] args) {
		EbookController.viewType type;
		EbookController controller;
		EbookModel model;
		String in = "/media/READER/", out = "/tmp/";

		type = EbookController.viewType.SWT;
		for (int i = 0; i < args.length; i++) 
			if (args[i].equals("-c"))
				type = EbookController.viewType.Console;
			else if (args[i].equals("-i") && i+1 < args.length)
				in = args[i+1];
			else if (args[i].equals("-o") && i+1 < args.length)
				out = args[i+1];
			else {
				System.err.println("eexporter [-c] [-i <input directory>] [-o <output directory]");
				System.exit(0);
			}
		try {
			model = new EbookModel(in, out);
			controller = new EbookController(model, type);
			controller.displayView();
			controller.closeView();
		}
		catch(Exception e) {
			System.err.println(e);
		}
	}
}
