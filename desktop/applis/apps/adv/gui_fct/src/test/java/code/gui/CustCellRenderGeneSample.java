package code.gui;

import code.gui.images.AbstractImage;
import code.mock.MockImage;

public final class CustCellRenderGeneSample implements AbsCustCellRenderGene<String> {
    @Override
    public AbstractImage getListCellRendererComponent(int _index, String _info, boolean _isSelected, boolean _cellHasFocus, AbsPreparedLabel _lab) {
        return new MockImage(new int[10][10]);
    }
}
