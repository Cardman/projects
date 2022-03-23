package code.vi.prot.impl.other;

import code.gui.AbstractSelectionListener;
import code.gui.FrameUtil;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class LocalItemListener implements ItemListener, AbstractSelectionListener {
	private final JComboBox<String> combo;
	private final ListSelection listener;
	LocalItemListener(JComboBox<String> _combo,ListSelection _listener){
		combo = _combo;
		listener = _listener;
	}
	public void itemStateChanged(ItemEvent _e){
		FrameUtil.act(listener,new SelectionInfo(combo.getSelectedIndex(),combo.getSelectedIndex(),false),_e.getStateChange(),ItemEvent.SELECTED);
	}

	public ListSelection getListener() {
		return listener;
	}
}