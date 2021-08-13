package code.gui;
import code.adv.ValueChangingUtil;

import javax.swing.event.*;
public class LocalListSelectionListener implements ListSelectionListener {
	private final ListSelection listener;

	public LocalListSelectionListener(ListSelection _listener){
		listener = _listener;
	}
	public void valueChanged(ListSelectionEvent _e){
		ValueChangingUtil.act(new ValueChangingImpl(listener,_e));
	}

	public ListSelection getListener() {
		return listener;
	}
}