package code.gui;
import java.awt.Image;

public abstract class ChildFrame extends CommonFrame {

    private Image imageIconFrame;

    protected ChildFrame(String _lg) {
        super(_lg);
    }

    protected void setDialogIcon(GroupFrame _group) {
        setIconImage(_group.getImageIconFrame());
        imageIconFrame = _group.getImageIconFrame();
    }

    public abstract void closeWindow();

    @Override
    public Image getImageIconFrame() {
        return imageIconFrame;
    }
}
