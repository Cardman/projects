package exportwpl.gui;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import code.gui.Clock;
import code.gui.FileSaveDialog;
import code.gui.FolderOpenDialog;
import code.gui.GroupFrame;
import code.gui.LabelButton;
import code.gui.SetStyle;
import code.sml.Document;
import code.sml.DocumentBuilder;
import code.sml.Element;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.consts.ConstFiles;
import code.util.consts.Constants;

public class MainWindow extends GroupFrame {

    private static final String END_ENCODE = ";";
    private static final String BEGIN_ENCODE = "&#";
    private static final String SRC = "src";
    private static final String MEDIA = "media";
    private static final String SEQ = "seq";
    private static final String BODY = "body";
    private static final String SMIL = "smil";
    private static final String EMPTY_STRING = "";
    private static final String WRITE_TO_FILE = "write to file";
    private static final String SELECT_FILE = "select file";
    private static final String SELECT_FOLDER = "select folder";
    private JLabel folderLabel = new JLabel("songs:");
    private JTextField folder = new JTextField(40);
    private JLabel fileLabel = new JLabel("file:");
    private JTextField file = new JTextField(40);

    public MainWindow() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        getContentPane().add(folderLabel);
        getContentPane().add(folder);
        LabelButton b_;
        b_ = new LabelButton(SELECT_FOLDER);
        b_.addMouseListener(new SelectFolder(this));
        getContentPane().add(b_);
        getContentPane().add(fileLabel);
        getContentPane().add(file);
        b_ = new LabelButton(SELECT_FILE);
        b_.addMouseListener(new SelectFile(this));
        getContentPane().add(b_);
        b_ = new LabelButton(WRITE_TO_FILE);
        b_.addMouseListener(new WriteFile(this));
        getContentPane().add(b_);
        getContentPane().add(new Clock());
        pack();
        setVisible(true);
        SetStyle.setupStyle(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void selectFolder() {
        FolderOpenDialog.setFolderOpenDialog(this, Constants.getLanguage(), false);
        String folderPathRead_ = FolderOpenDialog.getStaticSelectedPath();
        if (folderPathRead_.isEmpty()) {
            return;
        }
        folder.setText(folderPathRead_);
    }

    public void selectFile() {
        FileSaveDialog.setFileSaveDialogByFrame(this, Constants.getLanguage(), false, EMPTY_STRING, ConstFiles.getHomePath());
        String file_ = FileSaveDialog.getStaticSelectedPath().trim();
        if (file_.isEmpty()) {
            return;
        }
        file.setText(file_);
    }

    public void write() {
        String txt_ = file.getText().trim();
        if (txt_.isEmpty()) {
            return;
        }
        DocumentBuilder builder_;
        try {
            builder_ = DocumentBuilder.newDocumentBuilder();
            Document doc_ = builder_.newDocument();
            Element elt_ = doc_.createElement(SMIL);
            Element body_ = doc_.createElement(BODY);
            Element seq_ = doc_.createElement(SEQ);
            for (File f: new File(folder.getText()).listFiles()) {
                Element media_ = doc_.createElement(MEDIA);
                media_.setAttribute(SRC, StringList.replaceBackSlash(f.getAbsolutePath()));
                seq_.appendChild(media_);
            }
            body_.appendChild(seq_);
            elt_.appendChild(body_);
            doc_.appendChild(elt_);
            String contenu_ = doc_.export();
            StringBuilder escapedXml_ = new StringBuilder();
            for (char c: contenu_.toCharArray()) {
                if (c >= 128) {
                    escapedXml_.append(BEGIN_ENCODE);
                    escapedXml_.append((int)c);
                    escapedXml_.append(END_ENCODE);
                } else {
                    escapedXml_.append(c);
                }
            }
            StreamTextFile.saveTextFile(txt_, escapedXml_.toString());
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
    }

    @Override
    public void quit() {
        Constants.exit();
    }

    @Override
    public boolean canChangeLanguage() {
        return false;
    }

    @Override
    public void changeLanguage(String _language) {
    }

}
