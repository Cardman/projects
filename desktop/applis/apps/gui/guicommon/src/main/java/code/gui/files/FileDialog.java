package code.gui.files;


import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.stream.DocumentReaderGuiUtil;
import code.gui.stream.DocumentWriterGuiUtil;
import code.images.IntPoint;
import code.stream.*;
import code.stream.core.TechStreams;
import code.util.StringList;
import code.util.core.StringUtil;

public final class FileDialog {
    private static final String EMPTY_STRING = "";

    private static final int MIN_BORDER = 50;
    private static final String LANGUAGE_TXT = "langue.txt";

    private FileDialog() {
    }

    public static void setLocation(AbsCommonFrame _frame, TopLeftFrame _topLeft, AbstractProgramInfos _frs) {
        setLocation(_frame, _topLeft.getWidth(), _topLeft.getHeight(), _frs);
    }

    private static void setLocation(AbsCommonFrame _frame, int _x, int _y, AbstractProgramInfos _frs) {
        int x_ = _x;
        int y_ = _y;
        IntPoint dims_ = getScreenSize(_frs);
        if (x_ + MIN_BORDER > dims_.getXcoords()) {
            x_ = 0;
        }
        if (y_ + MIN_BORDER > dims_.getYcoords()) {
            y_ = 0;
        }
        if (x_ < 0) {
            x_ = 0;
        }
        if (y_ < 0) {
            y_ = 0;
        }
        _frame.setLocation(x_, y_);
    }

    private static IntPoint getScreenSize(AbstractProgramInfos _info) {
        int width_ = _info.getScreenWidth();
        int height_ = _info.getScreenHeight();
        return new IntPoint(width_, height_);
    }

    public static TopLeftFrame loadCoords(String _folder, String _file, AbstractFileCoreStream _fact, TechStreams _tech) {
//        return (TopLeftFrame) StreamTextFile.deserialiser(getFolderJarPath()+_file);
        return DocumentReaderGuiUtil.getTopLeftFrame(StreamTextFile.contentsOfFile(StringUtil.concat(_folder,StreamTextFile.SEPARATEUR,_file),_fact,_tech));
    }

    public static void saveCoords(String _folder, String _file, int _x, int _y, TechStreams _str) {
        TopLeftFrame topLeft_ = new TopLeftFrame();
        topLeft_.setWidth(_x);
        topLeft_.setHeight(_y);
//        StreamTextFile.save(getFolderJarPath()+_file, topLeft_);
        StreamTextFile.saveTextFile(StringUtil.concat(_folder,StreamTextFile.SEPARATEUR,_file), DocumentWriterGuiUtil.setTopLeftFrame(topLeft_),_str);
    }

    /**@throws LangueException*/
    public static String loadLanguage(String _dir, AbstractFileCoreStream _fact, TechStreams _tech, StringList _lgs) {
//        Node noeud_ = StreamTextFile.contenuDocumentXmlExterne(getFolderJarPath()+LANGUAGE);
        String language_ = StreamLanguageUtil.tryToGetXmlLanguage(_dir,_fact,_tech, _lgs);
        if (!language_.isEmpty()) {
            return language_;
        }
//        String content_ = StreamTextFile.contentsOfFile(ConstFiles.getFolderJarPath()+LANGUAGE_TXT);
        String content_ = StringUtil.nullToEmpty(StreamTextFile.contentsOfFile(StringUtil.concat(StreamFolderFile.getCurrentPath(_fact),LANGUAGE_TXT),_fact,_tech)).trim();
        return checkLgs(_lgs, content_);
    }

    static String checkLgs(StringList _lgs, String _content) {
        boolean valide_ = false;
        for (String l: _lgs) {
            if (StringUtil.quickEq(_content,l)) {
                valide_ = true;
            }
        }
        if(!valide_) {
            return EMPTY_STRING;
        }
        return _content;
    }

}
