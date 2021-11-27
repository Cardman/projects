package code.vi.sys.impl.gui;

import code.gui.*;
import code.gui.events.AbsWindowListener;
import code.vi.sys.impl.gui.events.WrWindowListener;
import code.gui.images.AbstractImage;
import code.gui.images.MetaPoint;
import code.util.CustList;
import code.util.IdMap;

import javax.swing.*;
import java.awt.*;

public final class OtherDialog implements AbsOtherDialog, ChangeableTitle {
    private final JDialog dialog = new JDialog();
    private Ownable owner;
    private AbstractImage image;
    private final IdMap<AbsWindowListener, WrWindowListener> mapWindow = new IdMap<AbsWindowListener, WrWindowListener>();
    private AbsPanel contentPane = Panel.newGrid(0,1);

    @Override
    public String getTitle() {
        return dialog.getTitle();
    }

    @Override
    public void setTitle(String _title) {
        dialog.setTitle(_title);
    }

    @Override
    public MetaPoint getLocationOnScreen() {
        Point pt_ = dialog.getLocationOnScreen();
        return new MetaPoint(pt_.x, pt_.y);
    }

    Window getComponent() {
        return dialog;
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return image;
    }

    @Override
    public void setLocationRelativeTo(AbsCustComponent _c) {
        dialog.setLocationRelativeTo(((CustComponent)_c).getNatComponent());
    }

    @Override
    public void setLocationRelativeTo(AbsOtherFrame _c) {
        dialog.setLocationRelativeTo(((OtherFrame)_c).getComponent());
    }

    @Override
    public void setLocationRelativeTo(AbsOtherDialog _c) {
        dialog.setLocationRelativeTo(((OtherDialog)_c).getComponent());
    }

    @Override
    public void setLocationRelativeToNull() {
        dialog.setLocationRelativeTo(null);
    }

    @Override
    public boolean isVisible() {
        return dialog.isVisible();
    }

    @Override
    public void dispose() {
        dialog.dispose();
    }

    public void setVisible(boolean _b) {
        dialog.setVisible(_b);
    }
    public boolean isModal() {
        return dialog.isModal();
    }
    public void setModal(boolean _modal) {
        dialog.setModal(_modal);
    }
    @Override
    public void pack() {
        dialog.pack();
    }

    @Override
    public void addWindowListener(AbsWindowListener _l) {
        WrWindowListener wr_ = new WrWindowListener(_l);
        mapWindow.addEntry(_l,wr_);
        dialog.addWindowListener(wr_);
    }

    @Override
    public void removeWindowListener(AbsWindowListener _l) {
        WrWindowListener wr_ = mapWindow.getVal(_l);
        dialog.removeWindowListener(wr_);
        mapWindow.removeKey(_l);
    }

    public void setContentPane(AbsPanel _contentPane) {
        dialog.setContentPane(((Panel)_contentPane).getNatComponent());
        contentPane = _contentPane;
    }

    @Override
    public AbsPanel getContentPane() {
        return contentPane;
    }

    @Override
    public CustList<AbsWindowListener> getWindowListeners() {
        return mapWindow.getKeys();
    }
    public void setDefaultCloseOperation(int _operation) {
        dialog.setDefaultCloseOperation(_operation);
    }

    @Override
    public Ownable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Ownable _owner) {
        owner = _owner;
    }

    public void setJMenuBar(AbsMenuBar _menuBar) {
        dialog.setJMenuBar(((MenuBar) _menuBar).getMeBar());
    }
}
