package aiki.main;
import java.awt.image.BufferedImage;

import aiki.db.DataBase;
import code.gui.images.ConverterGraphicBufferedImage;
import code.images.BaseSixtyFourUtil;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloNumber;
import code.scripts.messages.gui.MessPkVideoGr;
import code.stream.AbstractFile;
import code.stream.AbstractFileCoreStream;
import code.stream.FileListInfo;
import code.stream.StreamImageFile;
import code.util.CustList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class VideoLoading {

    /**IMAGES does not contain any null BufferedImage*/
    private static final String VIDEO = "video";
    private static final String VIDEO_DEFAULT = "resources_pk/gui/video/";
    private static final String FILE = "link_";
    private final CustList<CustList<BufferedImage>> images = new CustList<CustList<BufferedImage>>();
    private boolean initialized;
    private final LgInt maxRd = LgInt.getMaxLongPlusOne();

    public CustList<BufferedImage> getVideo(AbstractGenerator _abs, AbstractFileCoreStream _list) {
        if (!initialized) {
            String path_ = VIDEO;
            AbstractFile file_ = _list.newFile(path_);
            FileListInfo filesLists_ = file_.listAbsolute(_list);
            if (!filesLists_.isNul()) {
                for (AbstractFile folder_: filesLists_.getNames()) {
                    CustList<BufferedImage> imgs_ = new CustList<BufferedImage>();
                    FileListInfo files_ = folder_.listAbsolute(_list);
                    if (files_.isNul()) {
                        continue;
                    }
                    int len_;
                    len_ = files_.getNames().length;
                    for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
                        BufferedImage img_ = StreamImageFile.read(
                                StringUtil.concat(path_, DataBase.SEPARATOR_FILES, folder_.getName(),
                                DataBase.SEPARATOR_FILES, FILE, Long.toString(i), DataBase.IMG_FILES_RES_EXT));
                        if (img_ != null) {
                            imgs_.add(img_);
                        }
                    }
                    images.add(imgs_);
                }
            } else {
                CustList<BufferedImage> imgs_ = new CustList<BufferedImage>();
                int i_ = IndexConstants.FIRST_INDEX;
                while (true) {
                    int[][] txtFile_ = BaseSixtyFourUtil.getImageByString(
                            MessPkVideoGr.ms().getVal(StringUtil.concat(VIDEO_DEFAULT,Long.toString(i_),
                                    DataBase.IMG_FILES_RES_EXT_TXT)));
                    if (txtFile_.length == 0) {
                        break;
                    }
                    BufferedImage image_ = ConverterGraphicBufferedImage.decodeToImage(txtFile_);
                    if (image_ == null) {
                        break;
                    }
                    imgs_.add(image_);
                    i_++;
                }
                images.add(imgs_);
            }
            initialized = true;
        }
        int len_ = images.size();
        if (len_ == IndexConstants.SIZE_EMPTY) {
            return new CustList<BufferedImage>();
        }
        MonteCarloNumber law_ = new MonteCarloNumber();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            law_.addQuickEvent(new Rate(i), LgInt.one());
        }
        return new CustList<BufferedImage>(images.get((int) law_.editNumber(maxRd, _abs).ll()));
    }
    public CustList<CustList<BufferedImage>> getImages() {
        return images;
    }
}
