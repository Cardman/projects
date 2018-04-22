package code.gui;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.stream.DocumentReaderGuiUtil;
import code.gui.stream.DocumentWriterGuiUtil;
import code.resources.ResourceFiles;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.sml.Node;
import code.stream.StreamTextFile;
import code.util.PairNumber;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstFiles;
import code.util.consts.Constants;
import code.util.opers.BaseSixtyFourUtil;

public abstract class SoftApplicationCore {

    private static final String LOCALE = "locale";

    private static final String PARAMETERS = "parameters";

    private static final int MIN_BORDER = 50;

    private static final String LANGUAGE_TXT = "langue.txt";

    private static final String LANGUAGE = "langue.xml";

    private static final String EMPTY_STRING = "";

    protected void loadLaungage(String _dir, String[] _args, Image _icon) {
//        SerializeXmlObject.setReferences(false);
//        SerializeXmlObject.setCheckReferences(false);
////        SerializeXmlObject.setUpdateFinalFields(false);
//        String language_;
//        try {
//            language_ = loadLanguage(_dir);
//        }catch(LangueException _0) {
//            _0.printStackTrace();
//            proponeLanguage(_dir, _args, _icon);
//            return;
//        }
//        Constants.setSystemLanguage(language_);
        if (!prepareLanguage(_dir, _args, _icon)) {
            return;
        }
        StringMap<Object> files_ = getFile(_args);
//        launchWithoutLanguage(language_, files_);
        launchWithoutLanguage(Constants.getLanguage(), files_);
    }

    protected boolean prepareLanguage(String _dir, String[] _args, Image _icon) {
//        SerializeXmlObject.setReferences(false);
//        SerializeXmlObject.setCheckReferences(false);
//        SerializeXmlObject.setUpdateFinalFields(false);
        String language_ = loadLanguage(_dir);
        if (language_.isEmpty()) {
            proponeLanguage(_dir, _args, _icon);
            return false;
        }
        Constants.setSystemLanguage(language_);
        return true;
    }

    StringMap<Object> getFile(String[] _args) {
        StringMap<Object> files_ = new StringMap<Object>();
        if (_args.length > 0) {
            String fileName_ = new File(_args[0]).getAbsolutePath();
            fileName_ = StringList.replaceBackSlash(fileName_);
            try {
                files_.put(fileName_, getObject(_args[0]));
            } catch (RuntimeException _0) {
                files_.put(fileName_, null);
            }
        }
        return files_;
    }

    private LanguageFrame proponeLanguage(String _dir, String[] _args, Image _icon) {
        return new LanguageFrame(_dir, _args, this, _icon);
    }

    protected static BufferedImage getImage(String _folder, String _fileTxt) {
        //, String _filePng
        BufferedImage image_ = null;
        String icon_ = ResourceFiles.ressourceFichier(StringList.concat(_folder,StreamTextFile.SEPARATEUR,_fileTxt));
        int[][] file_ = BaseSixtyFourUtil.getImageByString(icon_);
        image_ = ConverterGraphicBufferedImage.decodeToImage(file_);
//        try {
//            String file_ = StreamTextFile.ressourceFichier(_folder, _fileTxt);
//            image_ = ConverterBufferedImage.decodeToImage(file_);
//        } catch (Exception e_) {
//            e_.printStackTrace();
//            try {
//                image_ = (BufferedImage) StreamImageFile.ressourceIcon(_folder, _filePng).getImage();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        return image_;
    }

    /**@throws LangueException*/
    private static String loadLanguage(String _dir) {
//        Node noeud_ = StreamTextFile.contenuDocumentXmlExterne(getFolderJarPath()+LANGUAGE);
        String language_ = tryToGetXmlLanguage(_dir);
        if (language_ != null) {
            return language_;
        }
//        String content_ = StreamTextFile.contentsOfFile(ConstFiles.getFolderJarPath()+LANGUAGE_TXT);
        String content_ = StreamTextFile.contentsOfFile(StringList.concat(ConstFiles.getInitFolder(),LANGUAGE_TXT));
        if (content_ == null) {
            return EMPTY_STRING;
        }
        content_ = content_.trim();
        boolean valide_ = false;
        for (String l: Constants.getAvailableLanguages()) {
            if (StringList.quickEq(content_,l)) {
                valide_ = true;
            }
        }
        if(!valide_) {
            return EMPTY_STRING;
        }
        return content_;
    }

    private static String tryToGetXmlLanguage(String _dir) {
        Node noeud_ = StreamTextFile.contenuDocumentXmlExterne(StringList.concat(_dir,StreamTextFile.SEPARATEUR,LANGUAGE));
        if (noeud_ == null) {
            return null;
        }
        for(Element e: noeud_.getChildElements()){
            if(StringList.quickEq(e.getTagName(),LOCALE)){
                String code_ = e.getAttribute(LOCALE);
                boolean valide_ = false;
                for (String l: Constants.getAvailableLanguages()) {
                    if (StringList.quickEq(code_,l)) {
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

    public static void saveLanguage(String _folder, String _locale) {
        Document document_= DocumentBuilder.newXmlDocument();
        Element info_=document_.createElement(PARAMETERS);
        Element infoPart_ = document_.createElement(LOCALE);
        infoPart_.setAttribute(LOCALE, _locale);
        info_.appendChild(infoPart_);
        document_.appendChild(info_);
        boolean indent_ = DocumentBuilder.isIndentXmlWhileWriting();
        DocumentBuilder.setIndentXmlWhileWriting(false);
        StreamTextFile.saveTextFile(StringList.concat(_folder,StreamTextFile.SEPARATEUR,LANGUAGE), DocumentBuilder.toXmlDocument(document_));
        DocumentBuilder.setIndentXmlWhileWriting(indent_);
    }

    public static void setLocation(CommonFrame _frame, TopLeftFrame _topLeft) {
        setLocation(_frame, _topLeft.getWidth(), _topLeft.getHeight());
    }

    private static void setLocation(CommonFrame _frame, int _x, int _y) {
        int x_ = _x;
        int y_ = _y;
        PairNumber<Integer,Integer> dims_ = getScreenSize();
        if (x_ + MIN_BORDER > dims_.getFirst()) {
            x_ = 0;
        }
        if (y_ + MIN_BORDER > dims_.getSecond()) {
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

    private static PairNumber<Integer,Integer> getScreenSize() {
        GraphicsDevice gd_ = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width_ = gd_.getDisplayMode().getWidth();
        int height_ = gd_.getDisplayMode().getHeight();
        return new PairNumber<Integer,Integer>(width_, height_);
    }

    public void launch() {
        launchWithoutLanguage(Constants.getLanguage(), new StringMap<Object>());
    }

    public abstract Object getObject(String _fileName);

    protected abstract void launchWithoutLanguage(String _language, StringMap<Object> _obj);

    protected abstract void launch(String _language, StringMap<Object> _args);

    protected static TopLeftFrame loadCoords(String _folder, String _file) {
//        return (TopLeftFrame) StreamTextFile.deserialiser(getFolderJarPath()+_file);
        return DocumentReaderGuiUtil.getTopLeftFrame(StreamTextFile.contentsOfFile(StringList.concat(_folder,StreamTextFile.SEPARATEUR,_file)));
    }

    public static void saveCoords(String _folder, String _file, int _x, int _y) {
        TopLeftFrame topLeft_ = new TopLeftFrame();
        topLeft_.setWidth(_x);
        topLeft_.setHeight(_y);
//        StreamTextFile.save(getFolderJarPath()+_file, topLeft_);
        StreamTextFile.saveTextFile(StringList.concat(_folder,StreamTextFile.SEPARATEUR,_file), DocumentWriterGuiUtil.setTopLeftFrame(topLeft_));
    }

    protected abstract Image getImageIcon();
}
