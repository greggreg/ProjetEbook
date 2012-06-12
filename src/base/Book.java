package base;

public class Book {

	private int id;
	private String title;
	private String author;
	private String kana_title;
	private String kana_author;
	private String title_key;
	private String author_key;
	private int source_id;
	private int added_date;
	private int modified_date;
	private int reading_time;
	private int purchased_date; 
	private String file_path;
	private String file_name;
	private int file_size;
	private String thumbnail;
	private String mime_type;
	private int corrupted;
	private int expiration_date;
	private int prevent_delete; 
	private String sony_id;
	private String periodical_name; 
	private String kana_periodical_name;
	private String periodical_name_key;
	private String publication_date;
	private String conforms_to;
	private String description;
	private String logos;



	public Book(int id, String title, String author, String kana_title,
			String kana_author, String title_key, String author_key,
			int source_id, int added_date, int modified_date, int reading_time,
			int purchased_date, String file_path, String file_name,
			int file_size, String thumbnail, String mime_type, int corrupted,
			int expiration_date, int prevent_delete, String sony_id,
			String periodical_name, String kana_periodical_name,
			String periodical_name_key, String publication_date,
			String conforms_to, String description, String logos) {

		this.id = id;
		this.title = title;
		this.author = author;
		this.kana_title = kana_title;
		this.kana_author = kana_author;
		this.title_key = title_key;
		this.author_key = author_key;
		this.source_id = source_id;
		this.added_date = added_date;
		this.modified_date = modified_date;
		this.reading_time = reading_time;
		this.purchased_date = purchased_date;
		this.file_path = file_path;
		this.file_name = file_name;
		this.file_size = file_size;
		this.thumbnail = thumbnail;
		this.mime_type = mime_type;
		this.corrupted = corrupted;
		this.expiration_date = expiration_date;
		this.prevent_delete = prevent_delete;
		this.sony_id = sony_id;
		this.periodical_name = periodical_name;
		this.kana_periodical_name = kana_periodical_name;
		this.periodical_name_key = periodical_name_key;
		this.publication_date = publication_date;
		this.conforms_to = conforms_to;
		this.description = description;
		this.logos = logos;
	}


	public Book(int id, String title, String author, String file_path, String file_name){
		this(id,title,author,"","","","",-1,-1,-1,-1,-1,file_path,file_name,-1,"","",-1,-1,-1,"","","","","","","","");
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getKana_title() {
		return kana_title;
	}


	public void setKana_title(String kana_title) {
		this.kana_title = kana_title;
	}


	public String getKana_author() {
		return kana_author;
	}


	public void setKana_author(String kana_author) {
		this.kana_author = kana_author;
	}


	public String getTitle_key() {
		return title_key;
	}


	public void setTitle_key(String title_key) {
		this.title_key = title_key;
	}


	public String getAuthor_key() {
		return author_key;
	}


	public void setAuthor_key(String author_key) {
		this.author_key = author_key;
	}


	public int getSource_id() {
		return source_id;
	}


	public void setSource_id(int source_id) {
		this.source_id = source_id;
	}


	public int getAdded_date() {
		return added_date;
	}


	public void setAdded_date(int added_date) {
		this.added_date = added_date;
	}


	public int getModified_date() {
		return modified_date;
	}


	public void setModified_date(int modified_date) {
		this.modified_date = modified_date;
	}


	public int getReading_time() {
		return reading_time;
	}


	public void setReading_time(int reading_time) {
		this.reading_time = reading_time;
	}


	public int getPurchased_date() {
		return purchased_date;
	}


	public void setPurchased_date(int purchased_date) {
		this.purchased_date = purchased_date;
	}


	public String getFile_path() {
		return file_path;
	}


	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}


	public String getFile_name() {
		return file_name;
	}


	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}


	public int getFile_size() {
		return file_size;
	}


	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}


	public String getThumbnail() {
		return thumbnail;
	}


	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}


	public String getMime_type() {
		return mime_type;
	}


	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}


	public int getCorrupted() {
		return corrupted;
	}


	public void setCorrupted(int corrupted) {
		this.corrupted = corrupted;
	}


	public int getExpiration_date() {
		return expiration_date;
	}


	public void setExpiration_date(int expiration_date) {
		this.expiration_date = expiration_date;
	}


	public int getPrevent_delete() {
		return prevent_delete;
	}


	public void setPrevent_delete(int prevent_delete) {
		this.prevent_delete = prevent_delete;
	}


	public String getSony_id() {
		return sony_id;
	}


	public void setSony_id(String sony_id) {
		this.sony_id = sony_id;
	}


	public String getPeriodical_name() {
		return periodical_name;
	}


	public void setPeriodical_name(String periodical_name) {
		this.periodical_name = periodical_name;
	}


	public String getKana_periodical_name() {
		return kana_periodical_name;
	}


	public void setKana_periodical_name(String kana_periodical_name) {
		this.kana_periodical_name = kana_periodical_name;
	}


	public String getPeriodical_name_key() {
		return periodical_name_key;
	}


	public void setPeriodical_name_key(String periodical_name_key) {
		this.periodical_name_key = periodical_name_key;
	}


	public String getPublication_date() {
		return publication_date;
	}


	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}


	public String getConforms_to() {
		return conforms_to;
	}


	public void setConforms_to(String conforms_to) {
		this.conforms_to = conforms_to;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getLogos() {
		return logos;
	}


	public void setLogos(String logos) {
		this.logos = logos;
	}





	@Override
	public String toString() {


		return "Book [id=" + id + ", title=" + title + ", author=" + author
				+ "\n kana_title=" + kana_title + "\n kana_author=" + kana_author
				+ ", title_key=" + title_key + "\n author_key=" + author_key
				+ "\n source_id=" + source_id + "\n added_date=" + added_date
				+ "\n modified_date=" + modified_date + "\n reading_time="
				+ reading_time + "\n purchased_date=" + purchased_date
				+ "\n file_path=" + file_path + "\n file_name=" + file_name
				+ "\n file_size=" + file_size + "\n thumbnail=" + thumbnail
				+ "\n mime_type=" + mime_type + "\n corrupted=" + corrupted
				+ "\n expiration_date=" + expiration_date + "\n prevent_delete="
				+ prevent_delete + "\n sony_id=" + sony_id
				+ "\n periodical_name=" + periodical_name
				+ "\n kana_periodical_name=" + kana_periodical_name
				+ "\n periodical_name_key=" + periodical_name_key
				+ "\n publication_date=" + publication_date + "\n conforms_to="
				+ conforms_to + "\n description=" + description + "\n logos=";
	}


}
