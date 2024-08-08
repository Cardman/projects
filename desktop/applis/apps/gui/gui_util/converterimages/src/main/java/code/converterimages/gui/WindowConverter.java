package code.converterimages.gui;



import code.gui.*;
import code.gui.events.*;
import code.gui.files.DefButtonsOpenFolderPanelAct;
import code.gui.files.FolderOpenFrame;
import code.gui.images.AbstractImage;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.initialize.AbstractProgramInfos;
import code.images.BaseSixtyFourUtil;
import code.stream.*;
import code.util.StringList;
import code.util.core.StringUtil;

public final class WindowConverter extends GroupFrame implements AbsOpenQuit {

    public static final String APPS_CONVERTER = "converter";
    private static final String EMPTY_STRING = "";

//    private static final String DOT = ".";

//    private static final String TXT_EXT = "txt";

//    private static final String JPEG_EXT = "jpeg";
//    private static final String JPG_EXT = "jpg";
//    private static final String PNG_EXT = "png";

    private static final String OK = "ok";

    private static final String READ = "read";

//    private static final String READ_IMAGES = "read images";

    private static final String CONVERT_IMAGE = "convert image";

//    private final AbsCustCheckBox readImages;

    private final AbsTextField path;

    private final AbsTextField pathExport;
//    private final AbstractAtomicBoolean atomicBoolean;
    private final FolderOpenFrame folderOpenFrame;
    private final AbsButton readButton;
    private final AbsButton okButton;
    private final AbsButton mainButton;

    public WindowConverter(AbstractProgramInfos _list, LanguagesButtonsPair _pair) {
        super(_list);
        mainButton = _pair.getMainButton();
//        atomicBoolean = _list.getThreadFactory().newAtomicBoolean();
        folderOpenFrame = new FolderOpenFrame(_list,_list.getThreadFactory().newAtomicBoolean());
        GuiBaseUtil.choose(this);
        setTitle(CONVERT_IMAGE);
        AbsPanel content_ = _list.getCompoFactory().newPageBox();
//        readImages = getCompoFactory().newCustCheckBox(READ_IMAGES);
//        readImages.setSelected(true);
//        content_.add(readImages);
        path = getCompoFactory().newTextField(50);
        path.setText(EMPTY_STRING);
        //path.setText(Constants.getJarPath());
        content_.add(path);
        pathExport = getCompoFactory().newTextField(50);
        pathExport.setText(EMPTY_STRING);
        content_.add(pathExport);
        readButton = getCompoFactory().newPlainButton(READ);
        AbsButton read_ = readButton;
        read_.addActionListener(new ReadEvent(this));
        content_.add(read_);
        okButton = getCompoFactory().newPlainButton(OK);
        AbsButton ok_ = okButton;
        ok_.addActionListener(new ExportEvent(this));
        content_.add(ok_);
        setContentPane(content_);
        pack();
        setVisible(true);
//        exitMode(_list);
//        setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }

    public void read() {
        FolderOpenFrame.setFolderOpenDialog(false,getFolderOpenFrame(),new DefButtonsOpenFolderPanelAct(new ConvContinueFolder(pathExport)));
//        String folderPathRead_ = StringUtil.nullToEmpty(getFolderOpenDialogInt().input(getCommonFrame(), false));
//        if (folderPathRead_.isEmpty()) {
//            return;
//        }
//        pathExport.setText(folderPathRead_);
    }

    public FolderOpenFrame getFolderOpenFrame() {
        return folderOpenFrame;
    }

    public void export() {
        getFileCoreStream().newFile(path.getText()+StreamTextFile.SEPARATEUR).mkdirs();
        StringList files_ = StreamTextFile.files(pathExport.getText(),getFileCoreStream());
        StringList reg_ = new StringList();
        for (String f: files_) {
            String f_ = StringUtil.replaceBackSlash(f);
            if (!getFileCoreStream().newFile(pathExport.getText()+f).isDirectory()) {
                reg_.add(f);
                continue;
            }
            getFileCoreStream().newFile(path.getText()+f_).mkdirs();
//            StreamFolderFile.makeParent(path.getText()+StreamTextFile.SEPARATEUR+f_,getFileCoreStream());
        }
//        if (readImages.isSelected()) {
//            StringList files_ = StreamTextFile.files(pathExport.getText(),getFileCoreStream());
//            for (String f: files_) {
//                String f_ = StringUtil.replaceBackSlash(f);
//                if (!getFileCoreStream().newFile(pathExport.getText()+f).isDirectory()) {
//                    continue;
//                }
//                StreamFolderFile.makeParent(path.getText()+StreamTextFile.SEPARATEUR+f_,getFileCoreStream());
//            }
            for (String f: reg_) {
//            for (String f: files_)
//                if (getFileCoreStream().newFile(pathExport.getText()+f).isDirectory()) {
//                    continue;
//                }
                String f_ = StringUtil.replaceBackSlash(f);
                byte[] bytes_ = StreamBinaryFile.loadFile(pathExport.getText() + f, getStreams()).getBytes();
                AbstractImage img_ = startsWithPrefixes(bytes_,new byte[][]{new byte[]{(byte)0x89,(byte)0x50,(byte)0x4E,(byte)0x47,(byte)0x0D,(byte)0x0A,(byte)0x1A,(byte)0x0A}, new byte[]{(byte)0xFF,(byte)0xD8,(byte)0xFF}, new byte[]{(byte)0xFF,(byte)0x4F,(byte)0xFF}, new byte[]{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x0C,(byte)0x6A,(byte)0x50,(byte)0x20,(byte)0x20,(byte)0x0D,(byte)0x0A,(byte)0x87,(byte)0x0A}});
                if (img_ == null) {
                    int[][] readImage_ = BaseSixtyFourUtil.getImageByString(StringUtil.nullToEmpty(StringUtil.decode(bytes_)));
                    if (readImage_.length != 0) {
                        StreamBinaryFile.writeFile(path.getText() + f_, getImageFactory().decodeToImage(readImage_), getStreams());
                    }
                    continue;
                }
                String txt_ = BaseSixtyFourUtil.getStringByImage(ConverterGraphicBufferedImage.toArrays(img_));
                StreamTextFile.saveTextFile(path.getText()+ f_, txt_,getStreams());
//                if (f_.endsWith(DOT+PNG_EXT)) {
//                    String path_ = StringUtil.replace(f_, DOT + PNG_EXT, DOT + TXT_EXT);
//                    StreamTextFile.saveTextFile(path.getText()+StreamTextFile.SEPARATEUR+ path_, txt_,getStreams());
//                } else if (f_.endsWith(DOT+JPG_EXT)) {
//                    String path_ = StringUtil.replace(f_, DOT + JPG_EXT, DOT + TXT_EXT);
//                    StreamTextFile.saveTextFile(path.getText()+StreamTextFile.SEPARATEUR+ path_, txt_,getStreams());
//                } else if (f_.endsWith(DOT+JPEG_EXT)) {
//                    String path_ = StringUtil.replace(f_, DOT + JPEG_EXT, DOT + TXT_EXT);
//                    StreamTextFile.saveTextFile(path.getText()+StreamTextFile.SEPARATEUR+ path_, txt_,getStreams());
//                }
            }
//        } else {
//            StringList files_ = StreamTextFile.files(pathExport.getText(),getFileCoreStream());
//            for (String f: files_) {
//                String f_ = StringUtil.replaceBackSlash(f);
//                if (!getFileCoreStream().newFile(pathExport.getText()+f).isDirectory()) {
//                    continue;
//                }
//                StreamFolderFile.makeParent(path.getText()+StreamTextFile.SEPARATEUR+f_,getFileCoreStream());
//            }
//            for (String f: reg_) {
//            for (String f: files_)
//                if (getFileCoreStream().newFile(pathExport.getText()+f).isDirectory()) {
//                    continue;
//                }
//                String f_ = StringUtil.replaceBackSlash(f);
//                String readImage_ = StreamTextFile.contentsOfFile(pathExport.getText()+f,getFileCoreStream(),getStreams());
//                if (readImage_ == null) {
//                    continue;
//                }
//                int[][] readImage_ = BaseSixtyFourUtil.getImageByString(StringUtil.nullToEmpty(StreamTextFile.contentsOfFile(pathExport.getText()+f,getFileCoreStream(),getStreams())));
//                if (readImage_.length == 0) {
//                    continue;
//                }
//                AbstractImage img_ = ConverterGraphicBufferedImage.decodeToImage(getImageFactory(), readImage_);
//                String file_ = path.getText() + StreamTextFile.SEPARATEUR + StringUtil.replace(f_, DOT + TXT_EXT, DOT + PNG_EXT);
//                StreamBinaryFile.writeFile(path.getText() + f_,getImageFactory().decodeToImage(readImage_),getStreams());
//                StreamBinaryFile.writeFile(path.getText() + StreamTextFile.SEPARATEUR + f_,img_.writeImg(PNG_EXT),getStreams());
//                StreamBinaryFile.writeFile(file_,img_.writeImg(PNG_EXT),getStreams());
//            }
//        }
    }
    private AbstractImage startsWithPrefixes(byte[] _file, byte[][] _pref) {
        for (byte[] p:_pref) {
            if (FileListInfo.startsWith(_file,p)) {
                return getFrames().getImageFactory().newImageFromBytes(_file);
            }
        }
        return null;
    }

    @Override
    public void quit() {
        getCommonFrame().setVisible(false);
        LanguageDialogButtons.enable(mainButton,true);
        GuiBaseUtil.trEx(this);
    }

//    public void dispose() {
//        GuiBaseUtil.trEx(this);
//    }
//    @Override
//    public boolean canChangeLanguage() {
//        return false;
//    }

    @Override
    public void changeLanguage(String _language) {
        getFrames().setLanguage(_language);
    }

    @Override
    public String getApplicationName() {
        return APPS_CONVERTER;
    }

    public AbsTextField getPathExport() {
        return pathExport;
    }

    public AbsTextField getPath() {
        return path;
    }

//    public AbsCustCheckBox getReadImages() {
//        return readImages;
//    }

    public AbsButton getOkButton() {
        return okButton;
    }

    public AbsButton getReadButton() {
        return readButton;
    }
}
