package base;
//markup type=2
public class Note extends Annotation {
	protected String mime_type;
	protected String marked_text;

	public Note(int id, String name, int idBook, String added_date,
			String modified_date, PdfLoc mark, PdfLoc mark_end, double page,
			int total_page, String file_path, int markup_type,String marked_text) {
		super(id, name, idBook, added_date, modified_date, mark, mark_end, page,
				total_page, file_path, markup_type);
		this.marked_text=marked_text;
	}

	public String getMime_type() {
		return mime_type;
	}

	public String getMarked_text() {
		return marked_text;
	}

	public Note(int id, String name, int idBook, String added_date,
			String modified_date, PdfLoc mark, PdfLoc mark_end, double page,
			int total_page, String file_path, String mime_type, int markup_type,String marked_text) {
		super(id, name, idBook, added_date, modified_date, mark, mark_end,
				page, total_page, file_path, markup_type);
		this.mime_type = mime_type;
		this.marked_text= marked_text;
	}

}
