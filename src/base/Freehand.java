package base;
//markup_type=1

public class Freehand  extends Annotation{



	public Freehand(int id, String name, int idBook, String added_date,
			String modified_date, PdfLoc mark, PdfLoc mark_end, double page,
			int total_page, String file_path) {
		super(id, name, idBook, added_date, modified_date, mark, mark_end, page,
				total_page, file_path, 0);
		this.markup_type=1;
		this.font_style = "";
		this.font_size = 0;
		this.page_style = 0;
		this.page_style_index = 0;
		this.crop_mode = 0;
		this.crop_left = 0;
		this.crop_top = 0;
		this.crop_right = 0;
		this.crop_bottom = 0;
		this.orientation = 0;
		this.text_encoding = 0;
		
	}
	@Override
	public String toString() {
		return super.toString() + "Freehand [font_style=" + font_style + ", font_size="
				+ font_size + ", page_style=" + page_style
				+ ", page_style_index=" + page_style_index + ", crop_mode="
				+ crop_mode + ", crop_left=" + crop_left + ", crop_top="
				+ crop_top + ", crop_right=" + crop_right + ", crop_bottom="
				+ crop_bottom + ", orientation=" + orientation
				+ ", text_encoding=" + text_encoding + ", svg_file=" + svg_file
				+ ", thumblnail=" + thumblnail + "]";
	}
	public Freehand(int id, String name, int idBook, String added_date,
			String modified_date, PdfLoc mark, PdfLoc mark_end, double page,
			int total_page, String file_path, String font_style, int font_size,
			int page_style, int page_style_index, int crop_mode, int crop_left,
			int crop_top, int crop_right, int crop_bottom, int orientation,
			int text_encoding, String svg_file, String thumblnail, int markup_type) {
		super(id, name, idBook, added_date, modified_date, mark, mark_end,
				page, total_page, file_path, markup_type);
		this.font_style = font_style;
		this.font_size = font_size;
		this.page_style = page_style;
		this.page_style_index = page_style_index;
		this.crop_mode = crop_mode;
		this.crop_left = crop_left;
		this.crop_top = crop_top;
		this.crop_right = crop_right;
		this.crop_bottom = crop_bottom;
		this.orientation = orientation;
		this.text_encoding = text_encoding;
		
		this.thumblnail = thumblnail;
	}
	private String font_style ;
	private int font_size; 
	
	private int page_style ;
	private int page_style_index; 
	private int crop_mode;
	private int crop_left; 
	private int crop_top;
	private int crop_right;
	private int crop_bottom; 
	private int orientation; 
	private int text_encoding;
	
	private String svg_file;
	private String thumblnail;
	
}
