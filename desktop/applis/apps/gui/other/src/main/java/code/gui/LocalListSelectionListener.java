package code.gui;
import javax.swing.event.*;
public class LocalListSelectionListener implements ListSelectionListener {
	private final ListSelection listener;

	public LocalListSelectionListener(ListSelection _listener){
		listener = _listener;
	}
	public void valueChanged(ListSelectionEvent _e){
		if (_e.getValueIsAdjusting()){
			return;
		}
		listener.valueChanged(new SelectionInfo(_e.getFirstIndex(),_e.getLastIndex(),false));
	}
}