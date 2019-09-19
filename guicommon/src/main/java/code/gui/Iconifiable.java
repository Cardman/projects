package code.gui;
import java.awt.image.BufferedImage;

public interface Iconifiable extends Packable {

    BufferedImage getImageIconFrame();

    boolean isVisible();
}
