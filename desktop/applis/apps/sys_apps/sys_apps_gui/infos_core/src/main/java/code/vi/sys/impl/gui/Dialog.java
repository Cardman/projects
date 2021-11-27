package code.vi.sys.impl.gui;

import code.gui.*;
import code.gui.events.CrossClosingDialogEvent;
import code.vi.prot.impl.gui.Panel;
import code.vi.sys.impl.gui.events.WrWindowListener;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaPoint;
import code.vi.prot.impl.DefImage;
import code.vi.prot.impl.DefImageFactory;

import javax.swing.*;
import java.awt.*;

public final class Dialog implements AbsDialog {

    private String accessFile;

    private AbstractImage imageIconFrame;

    private AbsPanel contentPane  = Panel.newLineBox();

    private final JDialog dial = new JDialog();
    private Ownable owner;
    public Dialog() {
        dial.setModal(true);
        dial.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        dial.addWindowListener(new WrWindowListener(new CrossClosingDialogEvent(this)));
    }
    public Dialog(AbsCloseableDialog _clos) {
        dial.setModal(true);
        dial.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        dial.addWindowListener(new WrWindowListener(new CrossClosingDialogEvent(_clos)));
    }


    public void setResizable(boolean _resizable) {
        dial.setResizable(_resizable);
    }

    public void setDefaultCloseOperation(int _operation) {
        dial.setDefaultCloseOperation(_operation);
    }

    @Override
    public void setLocationRelativeTo(AbsGroupFrame _onwer) {
        dial.setLocationRelativeTo(((CommonFrame)_onwer.getCommonFrame()).getFrame());
    }

    public void setLocationRelativeTo(AbsDialog _onwer) {
        dial.setLocationRelativeTo(((Dialog)_onwer).dial);
    }

    public void setLocationRelativeToWindow(Iconifiable _i) {
        FrameUtil.setLocationRelativeToWin(_i,this);
    }

    public String getAccessFile() {
        return accessFile;
    }

    public void setAccessFile(String _accessFile) {
        accessFile = _accessFile;
    }

    public void closeWindow() {
        setVisible(false);
        contentPane.removeAll();
    }

    public void setDialogIcon(AbstractImageFactory _fact, Iconifiable _group) {
        dial.setIconImage(DefImageFactory.icon((DefImage)_group.getImageIconFrame()).getImage());
        imageIconFrame = _group.getImageIconFrame();
    }

    @Override
    public AbstractImage getImageIconFrame() {
        return imageIconFrame;
    }

    public void setContentPane(AbsPanel _contentPane) {
        dial.setContentPane(((Panel)_contentPane).getNatComponent());
        contentPane = _contentPane;
    }

    public void setContentPane(AbsScrollPane _contentPane) {
        Panel p_ = Panel.newLineBox();
        p_.add(_contentPane);
        setContentPane(p_);
    }
    public AbsPanel getPane() {
        return contentPane;
    }
    public void setModal(boolean _modal) {
        dial.setModal(_modal);
    }
    @Override
    public boolean isVisible() {
        return dial.isVisible();
    }
    @Override
    public void pack() {
        dial.pack();
    }
    @Override
    public String getTitle() {
        return dial.getTitle();
    }
    @Override
    public void setTitle(String _str) {
        dial.setTitle(_str);
    }
    public void setVisible(boolean _b) {
        dial.setVisible(_b);
    }

    @Override
    public Ownable getOwner() {
        return owner;
    }

    @Override
    public void setOwner(Ownable _owner) {
        owner = _owner;
    }

    public MetaPoint getLocationOnScreen() {
        Point pt_ = dial.getLocationOnScreen();
        return new MetaPoint(pt_.x, pt_.y);
    }
}
