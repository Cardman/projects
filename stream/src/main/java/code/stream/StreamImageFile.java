package code.stream;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public final class StreamImageFile {

    private StreamImageFile() {
    }

    public static BufferedImage read(String _file) {
        try {
            return ImageIO.read(new File(_file));
        } catch (Exception e) {
            return null;
        }
    }
    public static boolean write(String _format, String _file, BufferedImage _img) {
        try {
            ImageIO.write(_img,_format,new File(_file));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
