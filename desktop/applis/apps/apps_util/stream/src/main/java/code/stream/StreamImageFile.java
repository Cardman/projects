package code.stream;
import code.util.core.StringUtil;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class StreamImageFile {

    private StreamImageFile() {
    }

    public static BufferedImage read(String _file) {
        try {
            return ImageIO.read(new FileInputStream(StringUtil.nullToEmpty(_file)));
        } catch (IOException e) {
            return null;
        }
    }
    public static boolean write(String _format, String _file, BufferedImage _img) {
        try {
            ImageIO.write(_img,StringUtil.nullToEmpty(_format),new FileOutputStream(StringUtil.nullToEmpty(_file)));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
