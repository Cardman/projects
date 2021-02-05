package code.gui;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;

public class LocalItemListener implements ItemListener {
	private final JComboBox<String> combo;
	private final ListSelection listener;
	LocalItemListener(JComboBox<String> _combo,ListSelection _listener){
		combo = _combo;
		listener = _listener;
	}
	public void itemStateChanged(ItemEvent _e){
		if (_e.getStateChange() != ItemEvent.SELECTED){
			return;
		}
		listener.valueChanged(new SelectionInfo(combo.getSelectedIndex(),combo.getSelectedIndex(),false));
	}
}