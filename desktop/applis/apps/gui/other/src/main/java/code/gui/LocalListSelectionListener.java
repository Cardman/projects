package code.gui;
import code.gui.initialize.ProgramInfos;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.*;
import javax.swing.WindowConstants;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import code.gui.events.QuittingEvent;
import code.util.StringList;
import code.util.Ints;
public class LocalListSelectionListener implements ListSelectionListener {
	private ListSelection listener;
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