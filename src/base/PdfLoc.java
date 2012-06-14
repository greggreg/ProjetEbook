package base;

public class PdfLoc {

	private String ref;
	private int page;
	private int line;
	private int unknown1 ;
	private int unknown2;
	private int unknown3;
	private int unknown4;
	private int unknown5;
	
	public String getRef() {
		return ref;
	}

	public int getPage() {
		return page;
	}

	public int getLine() {
		return line;
	}

	public int getUnknown1() {
		return unknown1;
	}

	public int getUnknown2() {
		return unknown2;
	}

	public int getUnknown3() {
		return unknown3;
	}

	public int getUnknown4() {
		return unknown4;
	}

	public int getUnknown5() {
		return unknown5;
	}

	public PdfLoc(String chain){	
		
	String tmp=chain.substring(8);
	String tmp2=tmp.substring(0, tmp.length()-1);
	String[] tbl=tmp2.split(",");

	ref=tbl[0];
	page=Integer.decode(tbl[1]);
	
	if(tbl.length==2){
		line=0;
		unknown1=0;
		unknown2=0;
		unknown3=0;
		unknown4=0;
		unknown5=0;
	}
	else{
	line=Integer.decode(tbl[2]);
	unknown1=Integer.decode(tbl[3]);
	unknown2=Integer.decode(tbl[4]);
	unknown3=Integer.decode(tbl[5]);
	unknown4=Integer.decode(tbl[6]);
	unknown5=Integer.decode(tbl[7]);
	}
	
	}

	@Override
	public String toString() {
		return "PdfLoc [ref=" + ref + ", page=" + page + ", line=" + line
				+ ", unknown1=" + unknown1 + ", unknown2=" + unknown2
				+ ", unknown3=" + unknown3 + ", unknown4=" + unknown4
				+ ", unknown5=" + unknown5 + "]";
	}
}
