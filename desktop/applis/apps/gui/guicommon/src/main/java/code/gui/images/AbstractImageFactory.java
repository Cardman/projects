package code.gui.images;
import javax.swing.*;

public interface AbstractImageFactory {
    AbstractImage newImageArgb(int _w, int _h);
    AbstractImage newImageRgb(int _w, int _h);
    Icon icon(AbstractImage _img);
}
