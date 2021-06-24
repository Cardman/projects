package code.gui;
import code.gui.images.AbstractImage;

import java.awt.image.BufferedImage;

public interface Iconifiable extends Packable {

    AbstractImage getImageIconFrame();

    boolean isVisible();
}
