package aiki.main;

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
import code.util.StringList;
import code.util.core.IndexConstants;

public final class VideoLoading {

//    public static final String IMG_FILES_RES_EXT = ".png";
    /**IMAGES does not contain any null BufferedImage*/
    private static final String VIDEO = "video";
//    private static final String VIDEO_DEFAULT = "resources_pk/gui/video/";
//    private static final String FILE = "link_";
    private final CustList<CustList<AbstractImage>> images = new CustList<CustList<AbstractImage>>();
    private boolean initialized;
    private final LgInt maxRd = LgInt.getMaxLongPlusOne();

    public CustList<AbstractImage> getVideo(AbstractGenerator _abs, AbstractFileCoreStream _list, AbstractProgramInfos _abInfo) {
        if (initialized) {
            return images(_abs);
        }
        AbstractFile file_ = _list.newFile(VIDEO);
        FileListInfo filesLists_ = PathsUtil.abs(file_,_list);
        if (!filesLists_.isNul()) {
            for (AbstractFile folder_: filesLists_.getNames()) {
                FileListInfo files_ = PathsUtil.abs(folder_,_list);
                if (files_.isNul()) {
                    continue;
                }
                CustList<AbstractImage> imgs_ = imgsByFile(_abInfo, files_);
                images.add(imgs_);
            }
        } else {
            CustList<AbstractImage> imgs_ = imgsByRes(_abInfo);
            images.add(imgs_);
        }
        initialized = true;
        return images(_abs);
    }

    private CustList<AbstractImage> imgsByRes(AbstractProgramInfos _abInfo) {
        CustList<AbstractImage> imgs_ = new CustList<AbstractImage>();
        for (EntryCust<String, String> e: MessPkVideoGr.ms().entryList()) {
//            if (!e.getKey().startsWith(VIDEO_DEFAULT)) {
//                continue;
//            }
            int[][] txtFile_ = BaseSixtyFourUtil.getImageByString(
                    e.getValue());
            AbstractImage image_ = ConverterGraphicBufferedImage.decodeToImage(_abInfo.getImageFactory(),txtFile_);
            imgs_.add(image_);
        }
        return imgs_;
    }

    private CustList<AbstractImage> imgsByFile(AbstractProgramInfos _abInfo, FileListInfo _files) {
        CustList<AbstractImage> imgs_ = new CustList<AbstractImage>();
        StringList paths_ = new StringList();
        for (AbstractFile f: _files.getNames()) {
            paths_.add(f.getAbsolutePath());
        }
        paths_.sort();
        for (String s: paths_) {
            AbstractImage img_ = _abInfo.getImageFactory().newImageFromBytes(
                    StreamBinaryFile.loadFile(s, _abInfo.getStreams()).getBytes());
            if (img_ != null) {
                imgs_.add(img_);
            }
        }
        return imgs_;
    }

    private CustList<AbstractImage> images(AbstractGenerator _abs) {
        int len_ = images.size();
        if (len_ == IndexConstants.SIZE_EMPTY) {
            return new CustList<AbstractImage>();
        }
        MonteCarloNumber law_ = new MonteCarloNumber();
        for (int i = IndexConstants.FIRST_INDEX; i < len_; i++) {
            law_.addQuickEvent(new Rate(i), LgInt.one());
        }
        return new CustList<AbstractImage>(getImages().get((int) law_.editNumber(maxRd, _abs).ll()));
    }
    public CustList<CustList<AbstractImage>> getImages() {
        return images;
    }
}
