package base;
//markup type=2
public class Note extends Annotation {
	

	public Note(int id, String name, int idBook, String added_date,
			String modified_date, PdfLoc mark, PdfLoc mark_end, double page,
			int total_page, String file_path, int markup_type) {
		super(id, name, idBook, added_date, modified_date, mark, mark_end, page,
				total_page, file_path, markup_type);
//		markup_type=2;
		// TODO Auto-generated constructor stub
	}

	public String getMime_type() {
		return mime_type;
	}

	

	public Note(int id, String name, int idBook, String added_date,
			String modified_date, PdfLoc mark, PdfLoc mark_end, double page,
			int total_page, String file_path, String mime_type, int markup_type) {
		super(id, name, idBook, added_date, modified_date, mark, mark_end,
				page, total_page, file_path, markup_type);
		this.mime_type = mime_type;
	}

	protected String mime_type;


	
}
