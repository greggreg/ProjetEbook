package base;

public abstract class  Annotation {
	
	public Annotation(int id, String name, int idBook, String added_date,
			String modified_date, PdfLoc mark, PdfLoc mark_end, double page,
			int total_page, String file_path, int markup_type) {
		super();
		this.id = id;
		this.name = name;
		this.idBook = idBook;
		this.added_date = added_date;
		this.modified_date = modified_date;
		this.mark = mark;
		this.mark_end = mark_end;
		this.page = page;
		this.total_page = total_page;
		this.file_path = file_path;
		this.markup_type = markup_type;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getIdBook() {
		return idBook;
	}
	public String getAdded_date() {
		return added_date;
	}
	public String getModified_date() {
		return modified_date;
	}
	public PdfLoc getMark() {
		return mark;
	}
	public PdfLoc getMark_end() {
		return mark_end;
	}
	public double getPage() {
		return page;
	}
	public int getTotal_page() {
		return total_page;
	}
	public String getFile_path() {
		return file_path;
	}
	public int getMarkup_type() {
		return markup_type;
	}
	@Override
	public String toString() {
		return "Annotation [id=" + id + ", name=" + name + ", idBook=" + idBook
				+ ", added_date=" + added_date + ", modified_date="
				+ modified_date + ", mark=" + mark + ", mark_end=" + mark_end
				+ ", page=" + page + ", total_page=" + total_page
				+ ", file_path=" + file_path + ", markup_type=" + markup_type
				+ "]";
	}
	protected int id;
	protected String name;
	protected int idBook;
	protected String added_date;
	protected String modified_date;
	protected PdfLoc mark ;
	protected PdfLoc mark_end;
	protected double page;
	protected int total_page;
	protected String file_path;
	protected int markup_type;

}
