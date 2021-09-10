package code.vi.sys.impl.variant;

import code.gui.AbsPreparedLabel;
import code.gui.CustCellRender;
import code.gui.FrameUtil;
import code.vi.sys.impl.gui.CustComponent;
import code.util.CustList;

import javax.swing.*;
import java.awt.*;

public final class DefCustSelList<T> implements ListCellRenderer {

    private final CustCellRender<T> render;
    private final CustList<T> list;
    private final AbsPreparedLabel label;

    public DefCustSelList(CustList<T> _elts, CustCellRender<T> _render) {
        list = _elts;
        this.render = _render;
        label = FrameUtil.prep(_render.getImageFactory());
    }


    public Component getListCellRendererComponent(
            JList _ls,
            Object _value,
            int _index,
            boolean _isSelected,
            boolean _cellHasFocus)
    {
        render.setList(list);
        render.getListCellRendererComponent(label,_index,_isSelected,_cellHasFocus);
        render.paintComponent(label);
        return ((CustComponent) label).getNatComponent();
    }
}
