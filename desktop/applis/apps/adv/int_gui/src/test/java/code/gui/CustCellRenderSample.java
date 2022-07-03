package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;

public class CustCellRenderSample extends CustCellRender<String>{
    @Override
    public void getListCellRendererComponent(AbsPreparedLabel _currentLab, int _index, boolean _isSelected, boolean _cellHasFocus) {
        getWidth();
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        getHeight();
    }

    @Override
    public AbstractImageFactory getImageFactory() {
        return new ImageFactorySample();
    }
}
