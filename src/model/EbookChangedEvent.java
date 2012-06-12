package model;

import java.util.EventObject;

public class EbookChangedEvent extends EventObject {
	private static final long serialVersionUID = 0;
	private String newOutput;
	
	public EbookChangedEvent(Object src, String newOutput) {
		super(src);
		this.newOutput = newOutput;
	}
	
	public String getNewOutput() {
		return this.newOutput;
	}
}
