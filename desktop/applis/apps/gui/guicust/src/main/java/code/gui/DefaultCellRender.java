package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbsCompoFactory;
import code.util.core.NumberUtil;


public final class DefaultCellRender extends CustCellRender<String> {

    private int maxWidth;

    private AbsPreparedLabel label;
    private String text;
    private boolean selected;
    private final AbstractImageFactory fact;
    private final AbsCompoFactory compo;
    private final AbsPanel panel;

    public DefaultCellRender(AbstractImageFactory _fact, AbsCompoFactory _compoFactory, AbsPanel _panel) {
        fact = _fact;
        compo = _compoFactory;
        panel = _panel;
    }

    @Override
    public AbsCustCellRender fwd() {
        setList(getListGr().getList());
        return this;
    }

    public AbsPanel getPanel() {
        return panel;
    }

    @Override
    public void getListCellRendererComponent(AbsPreparedLabel _currentLab,
                                             int _index, boolean _isSelected, boolean _cellHasFocus) {
        text = get(_index);
        label = _currentLab;
        maxWidth = NumberUtil.max(maxWidth,compo.stringWidth(label.getMetaFont(),text));
        selected = _isSelected;
    }

    public AbsCompoFactory getCompo() {
        return compo;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int _maxWidth) {
        maxWidth = _maxWidth;
    }

    @Override
    public AbstractImageFactory getImageFactory() {
        return fact;
    }
    @Override
    public int getWidth() {
        return maxWidth;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        FrameUtil.paintLabel(getMaxWidth(),_g, label, text, selected,compo);
    }

    @Override
    public int getHeight() {
        return label.heightFont();
    }
}