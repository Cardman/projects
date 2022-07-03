package code.gui;
import code.gui.images.AbstractImage;

public interface Iconifiable extends Packable {

    AbstractImage getImageIconFrame();

    boolean isVisible();
}
