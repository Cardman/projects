package code.gui;

import code.gui.images.AbstractImage;

public interface AbsCustCellRender {
   void getListCellRendererComponent(AbsPreparedLabel _currentLab,
                                                      int _index, boolean _isSelected, boolean _cellHasFocus);
    int getHeight();
    int getWidth();
    void paintComponent(AbstractImage _g);
    void paintComponent(AbsPreparedLabel _component);
    void fwd();

}
