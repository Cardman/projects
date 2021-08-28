package aiki.gui.dialogs;
import java.awt.Dimension;

import javax.swing.WindowConstants;

import aiki.beans.PokemonStandards;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsFrameFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.util.StringMap;

public final class DialogGameProgess {
    private static final String DIALOG_ACCESS = "aiki.gui.dialogs.dialoggameprogess";

    private static final String TEXT = "0";

    private static final String SEARCH_LABEL = "searchLabel";
    private final AbsDialog absDialog;

    private RenderedPage session;

    private StringMap<String> messages;

    public DialogGameProgess(AbstractProgramInfos _frameFactory) {
        absDialog = _frameFactory.getFrameFactory().newDialog();
        absDialog.setAccessFile(DIALOG_ACCESS);
    }

    public static void setGameProgress(WindowAiki _window, String _title, FacadeGame _facade, PreparedRenderedPages _pre) {
        _window.getDialogGameProgess().init(_window, _title, _facade,_pre);
    }

    private void init(WindowAiki _window, String _title, FacadeGame _facade, PreparedRenderedPages _pre) {
        //super(_window, true);
        absDialog.setDialogIcon(_window.getImageFactory(),_window);
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _window.getLanguageKey(), absDialog.getAccessFile());
        absDialog.setModal(true);
        absDialog.setTitle(_title);
        absDialog.setLocationRelativeTo(_window);
        AbsScrollPane scrollSession_ = _window.getCompoFactory().newAbsScrollPane();
        session = new RenderedPage(scrollSession_, _window.getFrames());
        session.setFrame(absDialog);
        ((PokemonStandards)_pre.getBeanNatLgNames()).setDataBase(_facade);
        session.initializeOnlyConf(_pre, _facade.getLanguage());
        AbsPanel panel_ = _window.getCompoFactory().newPageBox();
        AbsPlainLabel area_ = _window.getCompoFactory().newPlainLabel(TEXT);
        AbsTextField field_;
//        LabelButton search_ = _window.getCompoFactory().newPlainButton(MainWindow.OK);
        AbsPlainButton search_ = _window.getCompoFactory().newPlainButton(messages.getVal(SEARCH_LABEL));
        field_ = _window.getCompoFactory().newTextField(20);
//        session.setLabel(area_);
        session.setSearchText(search_);
        session.setField(field_);
        session.addFinder();
//        JPanel group_ = new JPanel();
//        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
        scrollSession_.setPreferredSize(new MetaDimension(400, 400));
//        group_.add(scrollSession_);
//        JScrollPane scrollTextArea_ = new JScrollPane(area_);
//        group_.add(scrollTextArea_);
        panel_.add(scrollSession_);
        panel_.add(area_);
        panel_.add(field_);
        panel_.add(search_);
        absDialog.setContentPane(panel_);
        absDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        absDialog.pack();
        absDialog.setVisible(true);
    }

}
