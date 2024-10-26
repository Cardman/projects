package code.gui;

import code.gui.images.*;
import code.util.AbsMap;
import code.util.core.StringUtil;

public final class CustCellRenderGeneStrImpl<T> implements AbsCustCellRenderGene<T> {
    private final AbstractImageFactory fact;
    private boolean selected;
    private int width;
    private final AbsMap<T,String> messages;

    public CustCellRenderGeneStrImpl(AbstractImageFactory _f, AbsMap<T, String> _m) {
        this.fact = _f;
        messages = _m;
    }

    public void setWidth(int _w) {
        this.width = _w;
    }

    public AbsMap<T, String> getMessages() {
        return messages;
    }

    @Override
    public AbstractImage getListCellRendererComponent(int _index, T _info, boolean _isSelected, boolean _cellHasFocus, boolean _cellIsAnchored, MetaFont _lab, ColorsGroupList _colors) {
        selected = _isSelected;
        AbstractImage img_ = fact.newImageRgb(width, 10);
        img_.setFont(_lab);
        paintComponent(img_, StringUtil.nullToEmpty(messages.getVal(_info)),10);
        return img_;
    }

    public void paintComponent(AbstractImage _g, String _str, int _h) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, width - 1, _h - 1);
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(_str, 0, _h - 2);
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, width - 1, _h - 1);
        }
    }
}
