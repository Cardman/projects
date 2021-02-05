package code.gui;
import javax.swing.event.*;
public class LocalListSelectionListener implements ListSelectionListener {
	private final ListSelection listener;
	private final AbsBasicGraphicList comp;
	public LocalListSelectionListener(AbsBasicGraphicList _comp,ListSelection _listener){
		listener = _listener;
		comp = _comp;
	}
	public void valueChanged(ListSelectionEvent _e){
		if (_e.getValueIsAdjusting()){
			return;
		}
		if (_e.getFirstIndex() == _e.getLastIndex()) {
			comp.setFirstIndex(_e.getFirstIndex());
		}
		listener.valueChanged(new SelectionInfo(_e.getFirstIndex(),_e.getLastIndex(),false));
	}
}