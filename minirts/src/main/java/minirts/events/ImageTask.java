package minirts.events;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import minirts.MainWindow;
import code.gui.images.ConverterGraphicBufferedImage;
import code.resources.ResourceFiles;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.opers.BaseSixtyFourUtil;

public class ImageTask implements ActionListener {

    private static final String PNG = "png";

    private static final String EXT = ".";

    private MainWindow window;

    private int noImg;

    public ImageTask(MainWindow _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        noImg++;
        int w_ = window.getWidth();
        int h_ = window.getHeight();
        BufferedImage b_ = new BufferedImage(w_, h_, BufferedImage.TYPE_INT_ARGB);
        Graphics g_ = b_.createGraphics();
        window.printAll(g_);
        try {
            Image tmpImg_;
            PointerInfo p_ = MouseInfo.getPointerInfo();
            Point pt_ = p_.getLocation();
            BufferedImage or_;
            String img_;
            if (MainWindow._currentCursor_.getType() == -1) {
                img_ = ResourceFiles.ressourceFichier(MainWindow.NOTE_FILE);
            } else {
                img_ = ResourceFiles.ressourceFichier(MainWindow.MOUSE_ARROW_FILE);
            }
            or_ = ConverterGraphicBufferedImage.decodeToImage(BaseSixtyFourUtil.getImageByString(img_));
            Toolkit tool_ = Toolkit.getDefaultToolkit();
            int wCurs_ = or_.getWidth();
            int hCurs_ = or_.getHeight();
            int[] pixels_ = new int[wCurs_ * hCurs_];
            for (int j = 0; j < hCurs_; j++) {
                for (int i = 0; i < wCurs_; i++) {
                    if (or_.getRGB(i, j) == Color.WHITE.getRGB()) {
                        continue;
                    }
                    pixels_[j * wCurs_ + i] = or_.getRGB(i, j);
                }
            }
            tmpImg_ = tool_.createImage(new MemoryImageSource(wCurs_, hCurs_, pixels_, 0, wCurs_));
            g_.drawImage(tmpImg_, pt_.x, pt_.y, null);
            ImageIO.write(b_, PNG, new File(StringList.concat(MainWindow.FOLDER, StreamTextFile.SEPARATEUR,Long.toString(noImg),EXT,PNG)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
