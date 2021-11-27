package code.vi.prot.impl.other;
import code.adv.ValueChangingUtil;
import code.gui.AbstractSelectionListener;
import code.gui.ListSelection;

import javax.swing.event.*;
public class LocalListSelectionListener implements ListSelectionListener, AbstractSelectionListener {
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