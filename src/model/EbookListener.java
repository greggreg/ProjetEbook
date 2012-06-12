package model;

import java.util.EventListener;

public interface EbookListener extends EventListener {
	public void ebookChanged(EbookChangedEvent e);
}
