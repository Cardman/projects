package code.minirts.events;

import code.minirts.WindowRts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageTask implements ActionListener {

    private static final String PNG = "png";

    private static final String EXT = ".";

    private final WindowRts window;

    private int noImg;

    public ImageTask(WindowRts _window) {
        window = _window;
    }

    @Override
    public void actionPerformed(ActionEvent _e) {
        noImg++;
//        BufferedImage b_ = window.printAll();
//        Image tmpImg_;
//        PointerInfo p_ = MouseInfo.getPointerInfo();
//        Point pt_ = p_.getLocation();
//        AbstractImage or_;
//        String img_ = window.getNoteFile();
////        if (window.getCurrentCursor().getType() == -1) {
////            img_ = ResourceFiles.ressourceFichier(MainWindow.NOTE_FILE);
////        } else {
////            img_ = ResourceFiles.ressourceFichier(MainWindow.MOUSE_ARROW_FILE);
////        }
//        or_ = ConverterGraphicBufferedImage.decodeToImage(window.getImageFactory(),BaseSixtyFourUtil.getImageByString(img_));
//        Toolkit tool_ = Toolkit.getDefaultToolkit();
//        int wCurs_ = or_.getWidth();
//        int hCurs_ = or_.getHeight();
//        int[] pixels_ = new int[wCurs_ * hCurs_];
//        for (int j = 0; j < hCurs_; j++) {
//            for (int i = 0; i < wCurs_; i++) {
//                if (or_.getRGB(i, j) == Color.WHITE.getRGB()) {
//                    continue;
//                }
//                pixels_[j * wCurs_ + i] = or_.getRGB(i, j);
//            }
//        }
//        tmpImg_ = tool_.createImage(new MemoryImageSource(wCurs_, hCurs_, pixels_, 0, wCurs_));
//        b_.getGraphics().drawImage(tmpImg_, pt_.x, pt_.y, null);
//        window.getFrames().writeImg(PNG,StringUtil.concat(MainWindow.FOLDER, StreamTextFile.SEPARATEUR,Long.toString(noImg),EXT,PNG),b_);
    }

}
