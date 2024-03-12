package code.gui.files;


import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.ConverterGraphicBufferedImage;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.stream.DocumentReaderGuiUtil;
import code.gui.stream.DocumentWriterGuiUtil;
import code.images.BaseSixtyFourUtil;
import code.images.IntPoint;
import code.sml.util.TranslationsAppli;
import code.sml.util.TranslationsLg;
import code.stream.*;
import code.stream.core.TechStreams;
import code.util.StringList;
import code.util.core.StringUtil;

public abstract class FileDialog {
    public static final String GUI = "gui";
    public static final String FILE_DIAL = "file_dialog";

    private static final String EMPTY_STRING = "";

    private static final int MIN_BORDER = 50;
    private static final String LANGUAGE_TXT = "langue.txt";

    private final AbsDialog absDialog;
    private final FileDialogContent fileDialogContent;

    protected FileDialog(AbstractProgramInfos _frameFact, FileDialogContent _f){
        fileDialogContent = _f;
        absDialog = _frameFact.getFrameFactory().newDialog(new FileCloseableDialog(this));
    }
    public static TranslationsAppli initAppliTr(TranslationsLg _lgs) {
        TranslationsAppli a_ = new TranslationsAppli();
        _lgs.getMapping().addEntry(GUI, a_);
        return a_;
    }

    public static TranslationsAppli getAppliTr(TranslationsLg _lgs) {
        return _lgs.getMapping().getVal(GUI);
    }

    public FileDialogContent getFileDialogContent() {
        return fileDialogContent;
    }

    public static void enTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(FILE_DIAL,MessagesFileDialog.en());
        _lgs.getMapping().addEntry(FolderOpenDialog.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.en());
        _lgs.getMapping().addEntry(FileOpenDialog.FILE_OPEN_DIAL,MessagesFileOpenDialog.en());
        _lgs.getMapping().addEntry(FileSaveDialog.FILE_SAVE_DIAL,MessagesFileSaveDialog.en());
        _lgs.getMapping().addEntry(FileTable.FILE_TAB,MessagesFileTable.en());
        _lgs.getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.en());
    }
    public static void frTr(TranslationsAppli _lgs) {
        _lgs.getMapping().addEntry(FILE_DIAL,MessagesFileDialog.fr());
        _lgs.getMapping().addEntry(FolderOpenDialog.FOLDER_OPEN_DIAL,MessagesFolderOpenDialog.fr());
        _lgs.getMapping().addEntry(FileOpenDialog.FILE_OPEN_DIAL,MessagesFileOpenDialog.fr());
        _lgs.getMapping().addEntry(FileSaveDialog.FILE_SAVE_DIAL,MessagesFileSaveDialog.fr());
        _lgs.getMapping().addEntry(FileTable.FILE_TAB,MessagesFileTable.fr());
        _lgs.getMapping().addEntry(ConfirmDialog.CONFIRM,MessagesConfirmDialog.fr());
    }
    public static AbstractImage getImage(String _icon, AbstractImageFactory _fact) {
        int[][] file_ = BaseSixtyFourUtil.getImageByString(_icon);
        return ConverterGraphicBufferedImage.decodeToImage(_fact,file_);
    }

    public static void setLocation(AbsCommonFrame _frame, TopLeftFrame _topLeft) {
        setLocation(_frame, _topLeft.getWidth(), _topLeft.getHeight());
    }

    private static void setLocation(AbsCommonFrame _frame, int _x, int _y) {
        int x_ = _x;
        int y_ = _y;
        IntPoint dims_ = getScreenSize(_frame.getFrames());
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

//    @Override
    public String getTitle() {
        return absDialog.getTitle();
    }

//    @Override
    public void setTitle(String _title) {
        absDialog.setTitle(_title);
    }

//    @Override
//    public MetaPoint getLocationOnScreen() {
//        return absDialog.getLocationOnScreen();
//    }

//    @Override
//    public AbstractImage getImageIconFrame() {
//        return absDialog.getImageIconFrame();
//    }

    public AbsDialog getAbsDialog() {
        return absDialog;
    }

//    @Override
    public boolean isVisible() {
        return absDialog.isVisible();
    }

//    @Override
    public void pack() {
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
        absDialog.setVisible(true);
    }

    protected String getSelectedPath() {
        return fileDialogContent.getSelectedPath();
    }

    protected String getSelectedAbsolutePath() {
        return fileDialogContent.getSelectedAbsolutePath();
    }

    protected AbsPanel getButtons() {
        return fileDialogContent.getButtons();
    }

    protected AbsTextField getFileName() {
        return fileDialogContent.getFileName();
    }

    protected AbsTreeGui getFolderSystem() {
        return fileDialogContent.getFolderSystem();
    }

    protected AbsTableGui getFileTable() {
        return fileDialogContent.getFileTable();
    }

    protected boolean isCurrentFolderRoot() {
        return fileDialogContent.isCurrentFolderRoot();
    }

    public void setSelectedPath(String _selectedPath) {
        fileDialogContent.setSelectedPath(_selectedPath);
    }

    protected void setSelectedAbsolutePath(String _selectedAbsolutePath) {
        fileDialogContent.setSelectedAbsolutePath(_selectedAbsolutePath);
    }

    protected void setCurrentTitle(String _currentTitle) {
        fileDialogContent.setCurrentTitle(_currentTitle);
    }

    protected String getCurrentTitle() {
        return fileDialogContent.getCurrentTitle();
    }

    public AbstractProgramInfos getProgramInfos() {
        return fileDialogContent.getProgramInfos();
    }

    public void closeWindow() {
        getAbsDialog().closeWindow();
    }
}
