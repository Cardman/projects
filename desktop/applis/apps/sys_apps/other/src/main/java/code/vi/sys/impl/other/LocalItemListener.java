package code.vi.sys.impl.other;

import code.adv.ValueChangingSecondUtil;
import code.gui.AbstractSelectionListener;
import code.gui.ListSelection;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;

public class LocalItemListener implements ItemListener, AbstractSelectionListener {
	private final JComboBox<String> combo;
	private final ListSelection listener;
	LocalItemListener(JComboBox<String> _combo,ListSelection _listener){
		combo = _combo;
		listener = _listener;
	}
	public void itemStateChanged(ItemEvent _e){
		ValueChangingSecondUtil.act(new ValueChangingSecondImpl(combo,listener,_e),ItemEvent.SELECTED);
	}

	public ListSelection getListener() {
		return listener;
	}
}