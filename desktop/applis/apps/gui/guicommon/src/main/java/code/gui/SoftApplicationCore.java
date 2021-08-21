package code.gui;

import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.stream.DocumentReaderGuiUtil;
import code.gui.stream.DocumentWriterGuiUtil;
import code.images.BaseSixtyFourUtil;
import code.images.IntPoint;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.Node;
import code.stream.AbstractFileCoreStream;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.TechStreams;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.StringUtil;

import java.awt.*;

public abstract class SoftApplicationCore {

    private static final String LOCALE = "locale";

    private static final String PARAMETERS = "parameters";

    private static final int MIN_BORDER = 50;

    private static final String LANGUAGE_TXT = "langue.txt";

    private static final String LANGUAGE = "langue.xml";

    private static final String EMPTY_STRING = "";

    private final AbstractProgramInfos frames;

    protected SoftApplicationCore(AbstractProgramInfos _frames) {
        frames = _frames;
    }
    protected void loadLaungage(String _dir, String[] _args, AbstractImage _icon) {
        String lg_ = prepareLanguage(_dir, _args, _icon);
        if (lg_.isEmpty()) {
            return;
        }
        StringMap<Object> files_ = getFile(_args);
        launch(lg_, files_);
    }

    protected final String prepareLanguage(String _dir, String[] _args, AbstractImage _icon) {
        String language_ = loadLanguage(_dir,getFrames().getFileCoreStream(), getFrames().getStreams());
        if (language_.isEmpty()) {
            proponeLanguage(_dir, _args, _icon);
        }
        return language_;
    }

    StringMap<Object> getFile(String[] _args) {
        StringMap<Object> files_ = new StringMap<Object>();
        if (_args.length > 0) {
            String fileName_ = getFrames().getFileCoreStream().newFile(_args[0]).getAbsolutePath();
            fileName_ = StringUtil.replaceBackSlash(fileName_);
            files_.put(fileName_, getObject(_args[0]));
        }
        return files_;
    }

    private LanguageFrame proponeLanguage(String _dir, String[] _args, AbstractImage _icon) {
        return new LanguageFrame(_dir, _args, this, _icon);
    }

    protected static AbstractImage getImage(String _icon, AbstractImageFactory _fact) {
        int[][] file_ = BaseSixtyFourUtil.getImageByString(_icon);
        return ConverterGraphicBufferedImage.decodeToImage(_fact,file_);
    }

    /**@throws LangueException*/
    private static String loadLanguage(String _dir, AbstractFileCoreStream _fact, TechStreams _tech) {
//        Node noeud_ = StreamTextFile.contenuDocumentXmlExterne(getFolderJarPath()+LANGUAGE);
        String language_ = tryToGetXmlLanguage(_dir,_fact,_tech);
        if (language_ != null) {
            return language_;
        }
//        String content_ = StreamTextFile.contentsOfFile(ConstFiles.getFolderJarPath()+LANGUAGE_TXT);
        String content_ = StreamTextFile.contentsOfFile(StringUtil.concat(StreamFolderFile.getCurrentPath(_fact),LANGUAGE_TXT),_fact,_tech);
        if (content_ == null) {
            return EMPTY_STRING;
        }
        content_ = content_.trim();
        boolean valide_ = false;
        for (String l: Constants.getAvailableLanguages()) {
            if (StringUtil.quickEq(content_,l)) {
                valide_ = true;
            }
        }
        if(!valide_) {
            return EMPTY_STRING;
        }
        return content_;
    }

    private static String tryToGetXmlLanguage(String _dir,AbstractFileCoreStream _fact, TechStreams _tech) {
        Node noeud_ = StreamTextFile.contenuDocumentXmlExterne(StringUtil.concat(_dir,StreamTextFile.SEPARATEUR,LANGUAGE),_fact,_tech);
        if (noeud_ == null) {
            return null;
        }
        for(Element e: noeud_.getChildElements()){
            if(StringUtil.quickEq(e.getTagName(),LOCALE)){
                String code_ = e.getAttribute(LOCALE);
                boolean valide_ = false;
                for (String l: Constants.getAvailableLanguages()) {
                    if (StringUtil.quickEq(code_,l)) {
                        valide_ = true;
                    }
                }
                if(!valide_) {
                    return null;
                }
                return code_;
            }
        }
        return null;
    }

    public static void saveLanguage(String _folder, String _locale,TechStreams _str) {
        Document document_= DocumentBuilder.newXmlDocument();
        Element info_=document_.createElement(PARAMETERS);
        Element infoPart_ = document_.createElement(LOCALE);
        infoPart_.setAttribute(LOCALE, _locale);
        info_.appendChild(infoPart_);
        document_.appendChild(info_);
        StreamTextFile.saveTextFile(StringUtil.concat(_folder,StreamTextFile.SEPARATEUR,LANGUAGE), document_.export(),_str);
    }

    public static void setLocation(AbsCommonFrame _frame, TopLeftFrame _topLeft) {
        setLocation(_frame, _topLeft.getWidth(), _topLeft.getHeight());
    }

    private static void setLocation(AbsCommonFrame _frame, int _x, int _y) {
        int x_ = _x;
        int y_ = _y;
        IntPoint dims_ = getScreenSize();
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

    private static IntPoint getScreenSize() {
        GraphicsDevice gd_ = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width_ = gd_.getDisplayMode().getWidth();
        int height_ = gd_.getDisplayMode().getHeight();
        return new IntPoint(width_, height_);
    }



    public abstract Object getObject(String _fileName);

    protected abstract void launch(String _language, StringMap<Object> _args);

    protected static TopLeftFrame loadCoords(String _folder, String _file,AbstractFileCoreStream _fact, TechStreams _tech) {
//        return (TopLeftFrame) StreamTextFile.deserialiser(getFolderJarPath()+_file);
        return DocumentReaderGuiUtil.getTopLeftFrame(StreamTextFile.contentsOfFile(StringUtil.concat(_folder,StreamTextFile.SEPARATEUR,_file),_fact,_tech));
    }

    public static void saveCoords(String _folder, String _file, int _x, int _y,TechStreams _str) {
        TopLeftFrame topLeft_ = new TopLeftFrame();
        topLeft_.setWidth(_x);
        topLeft_.setHeight(_y);
//        StreamTextFile.save(getFolderJarPath()+_file, topLeft_);
        StreamTextFile.saveTextFile(StringUtil.concat(_folder,StreamTextFile.SEPARATEUR,_file), DocumentWriterGuiUtil.setTopLeftFrame(topLeft_),_str);
    }

    public AbstractProgramInfos getFrames() {
        return frames;
    }


    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl, String _folder) {
        StreamFolderFile.makeParent(StringUtil.concat(_tmpUserFolderSl.getTmpUserFolder(),_folder)+"/", _tmpUserFolderSl.getFileCoreStream());
        return StringUtil.concat(_tmpUserFolderSl.getTmpUserFolder(),_folder);
    }

}
