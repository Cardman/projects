package code.gui;

import java.awt.Color;

public final class DefaultCellRender extends CustCellRender<String> {

    private int maxWidth;

    private PreparedLabel label;
    private String text;
    private boolean selected;

    @Override
    public void getListCellRendererComponent(PreparedLabel _currentLab,
                                             int _index, boolean _isSelected, boolean _cellHasFocus) {
        text = getList().get(_index);
        label = _currentLab;
        maxWidth = Math.max(maxWidth,label.stringWidth(text));
        selected = _isSelected;
    }
    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int _maxWidth) {
        maxWidth = _maxWidth;
    }

    @Override
    public int getWidth() {
        return maxWidth;
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        int h_ = label.heightFont();
        int w_ = label.stringWidth(text);
        if (selected) {
            LabelButtonUtil.paintDefaultLabel(_g, text, w_, getMaxWidth(), h_, Color.WHITE, Color.BLUE);
        } else {
            LabelButtonUtil.paintDefaultLabel(_g, text, w_, getMaxWidth(), h_, Color.BLACK, Color.WHITE);
        }
    }
    @Override
    public int getHeight() {
        return label.heightFont();
    }
}
