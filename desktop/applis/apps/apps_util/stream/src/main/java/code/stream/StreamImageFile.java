package code.stream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class StreamImageFile {

    private StreamImageFile() {
    }

    public static BufferedImage read(String _file) {
        if (_file == null) {
            return null;
        }
        try {
            return ImageIO.read(new File(_file));
        } catch (IOException e) {
            return null;
        }
    }
    public static boolean write(String _format, String _file, BufferedImage _img) {
        if (_file == null || _format == null) {
            return false;
        }
        try {
            ImageIO.write(_img,_format,new File(_file));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
