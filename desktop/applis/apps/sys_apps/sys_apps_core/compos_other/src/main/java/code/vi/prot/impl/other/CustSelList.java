package code.vi.prot.impl.other;
import javax.swing.ListCellRenderer;

import code.gui.AbsPreparedLabel;
import code.gui.CustCellRender;
import code.gui.FrameUtil;
import code.vi.prot.impl.gui.CustComponent;
import code.util.CustList;
import javax.swing.*;
import java.awt.Component;

public final class CustSelList<T> implements ListCellRenderer<T> {

	private final CustCellRender<T> render;
	private final CustList<T> list;
	private final AbsPreparedLabel label;

    public CustSelList(CustList<T> _elts, CustCellRender<T> _render) {
        list = _elts;
        this.render = _render;
        label = FrameUtil.prep(_render.getImageFactory());
    }


    public Component getListCellRendererComponent(
       JList<? extends T> _ls,
       T _value,
       int _index,               
       boolean _isSelected,     
       boolean _cellHasFocus) {
         render.setList(list);
         render.getListCellRendererComponent(label,_index,_isSelected,_cellHasFocus);
         render.paintComponent(label);
         return ((CustComponent) label).getNatComponent();
     }
}