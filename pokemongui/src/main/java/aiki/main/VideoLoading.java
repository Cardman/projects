package aiki.main;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import aiki.DataBase;
import code.images.ConverterBufferedImage;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.MonteCarloNumber;
import code.resources.ResourceFiles;
import code.util.CustList;
import code.util.StringList;
import code.util.opers.BaseSixtyFourUtil;

public final class VideoLoading {

    /**IMAGES does not contain any null BufferedImage*/
    private static final CustList<CustList<BufferedImage>> IMAGES = new CustList<CustList<BufferedImage>>();
    private static final String VIDEO = "video";
    private static final String VIDEO_DEFAULT = "resources_pk/gui/video/";
    private static final String FILE = "link_";
    private static boolean _initialized_;

    private VideoLoading() {
    }

    public static CustList<BufferedImage> getVideo() {
        if (!_initialized_) {
            String path_ = VIDEO;
            File file_ = new File(path_);
            if (file_.isDirectory()) {
                for (File folder_: file_.listFiles()) {
                    if (!folder_.isDirectory()) {
                        continue;
                    }
                    CustList<BufferedImage> imgs_ = new CustList<BufferedImage>();
                    File[] files_ = folder_.listFiles();
                    int len_;
                    len_ = files_.length;
                    for (int i = CustList.FIRST_INDEX; i < len_; i++) {
                        try {
                            imgs_.add(ImageIO.read(new File(StringList.concat(path_,DataBase.SEPARATOR_FILES,folder_.getName(),DataBase.SEPARATOR_FILES,FILE,Long.toString(i),DataBase.IMG_FILES_RES_EXT))));
                        } catch (IOException _0) {
                            //e.printStackTrace();
                        }
                    }
                    IMAGES.add(imgs_);
                }
            } else {
                CustList<BufferedImage> imgs_ = new CustList<BufferedImage>();
                int i_ = CustList.FIRST_INDEX;
                while (true) {
                    int[][] txtFile_ = BaseSixtyFourUtil.getImageByString(ResourceFiles.ressourceFichier(StringList.concat(VIDEO_DEFAULT,Long.toString(i_),DataBase.IMG_FILES_RES_EXT_TXT)));
                    if (txtFile_.length == 0) {
                        break;
                    }
                    BufferedImage image_ = ConverterBufferedImage.decodeToImage(txtFile_);
                    if (image_ == null) {
                        break;
                    }
                    imgs_.add(image_);
                    i_++;
                }
                IMAGES.add(imgs_);
            }
            _initialized_ = true;
        }
        int len_ = IMAGES.size();
        if (len_ == CustList.SIZE_EMPTY) {
            return new CustList<BufferedImage>();
        }
        MonteCarloNumber law_ = new MonteCarloNumber();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            law_.addEvent(new Rate(i), LgInt.one());
        }
        return new CustList<BufferedImage>(IMAGES.get((int) law_.editNumber().ll()));
    }
}
