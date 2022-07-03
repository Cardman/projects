package code.converterimages.gui;



import code.converterimages.main.LaunchingConverter;
import code.gui.*;
import code.gui.events.QuittingEvent;
import code.gui.images.AbstractImage;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.initialize.AbstractProgramInfos;
import code.images.BaseSixtyFourUtil;
import code.stream.StreamBinaryFile;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.consts.Constants;
import code.util.core.StringUtil;

public final class WindowConverter extends GroupFrame {

    private static final String EMPTY_STRING = "";

    private static final String DOT = ".";

    private static final String TXT_EXT = "txt";

    private static final String JPEG_EXT = "jpeg";
    private static final String JPG_EXT = "jpg";
    private static final String PNG_EXT = "png";

    private static final String OK = "ok";

    private static final String READ = "read";

    private static final String READ_IMAGES = "read images";

    private static final String CONVERT_IMAGE = "convert image";

    private final AbsCustCheckBox readImages;

    private final AbsTextField path;

    private final AbsTextField pathExport;

    public WindowConverter(String _lg, AbstractProgramInfos _list) {
        super(_lg, _list);
        setTitle(CONVERT_IMAGE);
        AbsPanel content_ = _list.getCompoFactory().newPageBox();
        readImages = getCompoFactory().newCustCheckBox(READ_IMAGES);
        readImages.setSelected(true);
        content_.add(readImages);
        path = getCompoFactory().newTextField(50);
        //path.setText(Constants.getJarPath());
        content_.add(path);
        pathExport = getCompoFactory().newTextField(50);
        pathExport.setText(EMPTY_STRING);
        content_.add(pathExport);
        AbsPlainButton read_ = getCompoFactory().newPlainButton(READ);
        read_.addActionListener(new ReadEvent(this));
        content_.add(read_);
        AbsPlainButton ok_ = getCompoFactory().newPlainButton(OK);
        ok_.addActionListener(new ExportEvent(this));
        content_.add(ok_);
        setContentPane(content_);
        pack();
        setVisible(true);
        setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }

    public void read() {
        String folderPathRead_ = getFolderOpenDialogInt().input(getCommonFrame(), Constants.getDefaultLanguage(), false);
        if (folderPathRead_.isEmpty()) {
            return;
        }
        pathExport.setText(folderPathRead_);
    }

    public void export() {
        boolean read_ = readImages.isSelected();
        export(read_);
    }

    void export(boolean _readImages) {
        if (_readImages) {
            StringList files_ = StreamTextFile.files(pathExport.getText(),getFileCoreStream());
            for (String f: files_) {
                String f_ = StringUtil.replaceBackSlash(f);
                if (!getFileCoreStream().newFile(pathExport.getText()+f).isDirectory()) {
                    continue;
                }
                StreamFolderFile.makeParent(path.getText()+StreamTextFile.SEPARATEUR+f_,getFileCoreStream());
            }
            for (String f: files_) {
                if (getFileCoreStream().newFile(pathExport.getText()+f).isDirectory()) {
                    continue;
                }
                String f_ = StringUtil.replaceBackSlash(f);
                byte[] bytes_ = StreamBinaryFile.loadFile(pathExport.getText() + f, getStreams());
                AbstractImage img_ = getFrames().getImageFactory().newImageFromBytes(bytes_);
                if (img_ == null) {
                    continue;
                }
                String txt_ = BaseSixtyFourUtil.getStringByImage(ConverterGraphicBufferedImage.toArrays(img_));
                if (f_.endsWith(DOT+PNG_EXT)) {
                    String path_ = StringUtil.replace(f_, DOT + PNG_EXT, DOT + TXT_EXT);
                    StreamTextFile.saveTextFile(path.getText()+StreamTextFile.SEPARATEUR+ path_, txt_,getStreams());
                } else if (f_.endsWith(DOT+JPG_EXT)) {
                    String path_ = StringUtil.replace(f_, DOT + JPG_EXT, DOT + TXT_EXT);
                    StreamTextFile.saveTextFile(path.getText()+StreamTextFile.SEPARATEUR+ path_, txt_,getStreams());
                } else if (f_.endsWith(DOT+JPEG_EXT)) {
                    String path_ = StringUtil.replace(f_, DOT + JPEG_EXT, DOT + TXT_EXT);
                    StreamTextFile.saveTextFile(path.getText()+StreamTextFile.SEPARATEUR+ path_, txt_,getStreams());
                }
            }
        } else {
            StringList files_ = StreamTextFile.files(pathExport.getText(),getFileCoreStream());
            for (String f: files_) {
                String f_ = StringUtil.replaceBackSlash(f);
                if (!getFileCoreStream().newFile(pathExport.getText()+f).isDirectory()) {
                    continue;
                }
                StreamFolderFile.makeParent(path.getText()+StreamTextFile.SEPARATEUR+f_,getFileCoreStream());
            }
            for (String f: files_) {
                if (getFileCoreStream().newFile(pathExport.getText()+f).isDirectory()) {
                    continue;
                }
                String f_ = StringUtil.replaceBackSlash(f);
                String readImage_ = StreamTextFile.contentsOfFile(pathExport.getText()+f,getFileCoreStream(),getStreams());
                if (readImage_ == null) {
                    continue;
                }
                AbstractImage img_ = ConverterGraphicBufferedImage.decodeToImage(getImageFactory(),BaseSixtyFourUtil.getImageByString(readImage_));
                String file_ = path.getText() + StreamTextFile.SEPARATEUR + StringUtil.replace(f_, DOT + TXT_EXT, DOT + PNG_EXT);
                StreamBinaryFile.writeFile(file_,img_.writeImg(PNG_EXT),getStreams());
            }
        }
    }

    public void readOneImageArg(String _readPath) {
        byte[] bytes_ = StreamBinaryFile.loadFile(_readPath,getStreams());
        AbstractImage img_ = getFrames().getImageFactory().newImageFromBytes(bytes_);
        if (img_ == null) {
            return;
        }
        String txt_ = BaseSixtyFourUtil.getStringByImage(ConverterGraphicBufferedImage.toArrays(img_));
        if (_readPath.endsWith(DOT+PNG_EXT)) {
            StreamTextFile.saveTextFile(StringUtil.replace(_readPath, DOT+PNG_EXT, DOT+TXT_EXT), txt_,getStreams());
        } else  if (_readPath.endsWith(DOT+JPG_EXT)) {
            StreamTextFile.saveTextFile(StringUtil.replace(_readPath, DOT+JPG_EXT, DOT+TXT_EXT), txt_,getStreams());
        } else  if (_readPath.endsWith(DOT+JPEG_EXT)) {
            StreamTextFile.saveTextFile(StringUtil.replace(_readPath, DOT+JPEG_EXT, DOT+TXT_EXT), txt_,getStreams());
        }
    }
    public void writeOneImageArg(String _writePath) {
        String readImage_ = StreamTextFile.contentsOfFile(_writePath,getFileCoreStream(),getStreams());
        if (readImage_ == null) {
            return;
        }
        AbstractImage img_ = ConverterGraphicBufferedImage.decodeToImage(getImageFactory(),BaseSixtyFourUtil.getImageByString(readImage_));
        String file_ = StringUtil.replace(_writePath, DOT + TXT_EXT, DOT + PNG_EXT);
        StreamBinaryFile.writeFile(file_,img_.writeImg(PNG_EXT),getStreams());
    }
    @Override
    public void quit() {
        basicDispose();
    }

    public void dispose() {
        basicDispose();
    }
    @Override
    public boolean canChangeLanguage() {
        return false;
    }

    @Override
    public void changeLanguage(String _language) {
        //
    }

    @Override
    public String getApplicationName() {
        return LaunchingConverter.getMainWindowClass();
    }
}
