package code.gui;
import java.awt.Image;

public abstract class ChildFrame extends CommonFrame implements ChangeableTitle {

    private Image imageIconFrame;

    protected ChildFrame() {
        WindowUtils.addInArray(this);
    }

    protected void setDialogIcon(GroupFrame _group) {
        setIconImage(_group.getImageIconFrame());
        imageIconFrame = _group.getImageIconFrame();
    }

    public abstract void closeWindow();

    @Override
    public void setVisible(boolean _b) {
        super.setVisible(_b);
        if (!_b) {
            WindowUtils.removeWindow(this);
        }
    }

    @Override
    public Image getImageIconFrame() {
        return imageIconFrame;
    }
}
