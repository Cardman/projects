package aiki.main;

import aiki.db.DataBase;
import code.gui.images.AbstractImage;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.initialize.AbstractProgramInfos;
import code.images.BaseSixtyFourUtil;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.montecarlo.AbstractGenerator;
import code.maths.montecarlo.MonteCarloNumber;
import code.scripts.messages.gui.MessPkVideoGr;
import code.stream.*;
import code.util.CustList;
import code.util.EntryCust;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class VideoLoading {

    /**IMAGES does not contain any null BufferedImage*/
    private static final String VIDEO = "video";
    private static final String VIDEO_DEFAULT = "resources_pk/gui/video/";
    private static final String FILE = "link_";
    private final CustList<CustList<AbstractImage>> images = new CustList<CustList<AbstractImage>>();
    private boolean initialized;
    private final LgInt maxRd = LgInt.getMaxLongPlusOne();

    public CustList<AbstractImage> getVideo(AbstractGenerator _abs, AbstractFileCoreStream _list, AbstractProgramInfos _abInfo) {
        if (!initialized) {
            String path_ = VIDEO;
            AbstractFile file_ = _list.newFile(path_);
            FileListInfo filesLists_ = PathsUtil.abs(file_,_list);
            if (!filesLists_.isNul()) {
                for (AbstractFile folder_: filesLists_.getNames()) {
                    CustList<AbstractImage> imgs_ = new CustList<AbstractImage>();
                    FileListInfo files_ = PathsUtil.abs(folder_,_list);
                    if (files_.isNul()) {
                        continue;
                    }
                    int len_;
                    len_ = files_.getNames().length;
                    for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
                        String fi_ = StringUtil.concat(path_, DataBase.SEPARATOR_FILES, folder_.getName(),
                                DataBase.SEPARATOR_FILES, FILE, Long.toString(i), DataBase.IMG_FILES_RES_EXT);
                        AbstractImage img_ = _abInfo.getImageFactory().newImageFromBytes(
                                StreamBinaryFile.loadFile(fi_,_abInfo.getStreams()));
                        if (img_ != null) {
                            imgs_.add(img_);
                        }
                    }
                    images.add(imgs_);
                }
            } else {
                CustList<AbstractImage> imgs_ = new CustList<AbstractImage>();
                for (EntryCust<String, String> e: MessPkVideoGr.ms().entryList()) {
                    if (!e.getKey().startsWith(VIDEO_DEFAULT)) {
                        continue;
                    }
                    int[][] txtFile_ = BaseSixtyFourUtil.getImageByString(
                            e.getValue());
                    AbstractImage image_ = ConverterGraphicBufferedImage.decodeToImage(_abInfo.getImageFactory(),txtFile_);
                    imgs_.add(image_);
                }
                images.add(imgs_);
            }
            initialized = true;
        }
        int len_ = images.size();
        if (len_ == IndexConstants.SIZE_EMPTY) {
            return new CustList<AbstractImage>();
        }
        MonteCarloNumber law_ = new MonteCarloNumber();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            law_.addQuickEvent(new Rate(i), LgInt.one());
        }
        return new CustList<AbstractImage>(images.get((int) law_.editNumber(maxRd, _abs).ll()));
    }
    public CustList<CustList<AbstractImage>> getImages() {
        return images;
    }
}
