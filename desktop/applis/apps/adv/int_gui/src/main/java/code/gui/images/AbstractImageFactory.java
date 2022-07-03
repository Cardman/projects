package code.gui.images;

public interface AbstractImageFactory {
    AbstractImage newImageArgb(int _w, int _h);
    AbstractImage newImageRgb(int _w, int _h);
    AbstractImage newImageFromBytes(byte[] _bytes);
}
