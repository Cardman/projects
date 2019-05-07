package converterimages.gui;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import code.gui.FolderOpenDialog;
import code.gui.GroupFrame;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.ThreadUtil;
import code.gui.events.QuittingEvent;
import code.gui.images.ConverterGraphicBufferedImage;
import code.images.BaseSixtyFourUtil;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.consts.Constants;

public class MainWindow extends GroupFrame {

    private static final String EMPTY_STRING = "";

    private static final String DOT = ".";

    private static final String TXT_EXT = "txt";

    private static final String PNG_EXT = "png";

    private static final String OK = "ok";

    private static final String READ = "read";

    private static final String READ_IMAGES = "read images";

    private static final String CONVERT_IMAGE = "convert image";

    private JCheckBox readImages;

    private JTextField path;

    private JTextField pathExport;

    public MainWindow(String _lg) {
        super(_lg);
        setTitle(CONVERT_IMAGE);
        Panel content_ = new Panel();
        content_.setLayout(new BoxLayout(content_.getComponent(), BoxLayout.PAGE_AXIS));
        readImages = new JCheckBox(READ_IMAGES);
        readImages.setSelected(true);
        content_.add(readImages);
        path = new JTextField(50);
        //path.setText(Constants.getJarPath());
        content_.add(path);
        pathExport = new JTextField(50);
        pathExport.setText(EMPTY_STRING);
        content_.add(pathExport);
        LabelButton read_ = new LabelButton(READ);
        read_.addMouseListener(new ReadEvent(this));
        content_.add(read_);
        LabelButton ok_ = new LabelButton(OK);
        ok_.addMouseListener(new ExportEvent(this));
        content_.add(ok_);
        setContentPane(content_);
        pack();
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }

    public void read() {
        FolderOpenDialog.setFolderOpenDialog(this, Constants.getDefaultLanguage(), false);
        String folderPathRead_ = FolderOpenDialog.getStaticSelectedPath();
        if (folderPathRead_.isEmpty()) {
            return;
        }
        pathExport.setText(folderPathRead_);
    }

    public void export() {
//        FolderOpenDialog folder_ = new FolderOpenDialog(this, Constants.getLanguage(), false);
//        String folderPathRead_ = folder_.getSelectedPath();
//        if (folderPathRead_.isEmpty()) {
//            return;
//        }
        if (readImages.isSelected()) {
            StringList files_ = StreamTextFile.files(pathExport.getText());
            for (String f: files_) {
                String f_ = StringList.replaceBackSlash(f);
                if (!new File(pathExport.getText()+f).isDirectory()) {
                    continue;
                }
                new File(path.getText()+StreamTextFile.SEPARATEUR+f_).getParentFile().mkdirs();
            }
            for (String f: files_) {
                if (new File(pathExport.getText()+f).isDirectory()) {
                    continue;
                }
                String f_ = StringList.replaceBackSlash(f);
                try {
                    BufferedImage img_ = ImageIO.read(new File(pathExport.getText()+f));
                    String txt_ = BaseSixtyFourUtil.getStringByImage(ConverterGraphicBufferedImage.toArrays(img_));
                    StreamTextFile.saveTextFile(path.getText()+StreamTextFile.SEPARATEUR+StringList.replace(f_, DOT+PNG_EXT, DOT+TXT_EXT), txt_);
                } catch (Exception _0) {
                }
                //ConverterBufferedImage.
            }
        } else {
            StringList files_ = StreamTextFile.files(pathExport.getText());
            for (String f: files_) {
                String f_ = StringList.replaceBackSlash(f);
                if (!new File(pathExport.getText()+f).isDirectory()) {
                    continue;
                }
                new File(path.getText()+StreamTextFile.SEPARATEUR+f_).getParentFile().mkdirs();
            }
            for (String f: files_) {
                if (new File(pathExport.getText()+f).isDirectory()) {
                    continue;
                }
                String f_ = StringList.replaceBackSlash(f);
                try {
                    String readImage_ = StreamTextFile.contentsOfFile(pathExport.getText()+f);
                    BufferedImage img_ = ConverterGraphicBufferedImage.decodeToImage(BaseSixtyFourUtil.getImageByString(readImage_));
                    ImageIO.write(img_, PNG_EXT, new File(path.getText()+StreamTextFile.SEPARATEUR+StringList.replace(f_, DOT+TXT_EXT, DOT+PNG_EXT)));
                } catch (Exception _0) {
                }
                //ConverterBufferedImage.
            }
        }
    }

    @Override
    public void quit() {
        ThreadUtil.exit();
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }

    @Override
    public void changeLanguage(String _language) {
    }

    @Override
    public String getApplicationName() {
        return "converter";
    }
}
