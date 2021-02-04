package code.gui;
import code.gui.initialize.ProgramInfos;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.WindowConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import code.gui.events.QuittingEvent;
import code.util.StringList;
import code.util.Ints;
public class LocalItemListener implements ItemListener {
	private JComboBox combo;
	private ListSelection listener;
	LocalItemListener(JComboBox _combo,ListSelection _listener){
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