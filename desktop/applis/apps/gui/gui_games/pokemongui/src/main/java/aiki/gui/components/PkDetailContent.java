package aiki.gui.components;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.listeners.HidePkDetailContentEvent;
import aiki.gui.dialogs.FrameHtmlData;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.sml.GamesPk;
import aiki.sml.MessagesRenderPkGameDetail;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.util.StringMap;

public final class PkDetailContent {

//    private RenderedPage session;

    private StringMap<String> messages;

    private final AbsPanel content;

    public PkDetailContent(AbstractProgramInfos _frameFactory) {
        content = _frameFactory.getCompoFactory().newPageBox();
        content.setVisible(false);
    }

    public void group(WindowAiki _parent, FacadeGame _dataBase, AikiNatLgNamesNavigation _pre, String _lg, Packable _p) {
        messages = file(_parent.getFrames().currentLg());
//        DialogHtmlData d_ = _parent.getDialogHtmlData();
        _pre.getBeanNatLgNames().setDataBase(_dataBase);
        RenderedPage session_ = FrameHtmlData.initializeOnlyConf(_pre, _lg, _pre.getBeanNatLgNames(), _parent.getFrames());
//        d_.messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), d_.absDialog.getAccessFile());
        initSession(session_, _p);
        content.setTitledBorder(messages.getVal(MessagesRenderPkGameDetail.TITLE));
//        d_.session.setFrame(d_.absDialog);
//        d_.absDialog.setVisible(true);
    }

    private void initSession(RenderedPage _session, Packable _p) {
//        session = _session;
//        _session.setFrame(absDialog);
        content.removeAll();
//        AbsPlainLabel area_ = _session.getCompoFactory().newPlainLabel(TEXT);
        AbsTextField field_;
//        LabelButton search_ = new LabelButton(MainWindow.OK);
        AbsButton search_ = _session.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderPkGameDetail.SEARCH_LABEL));
        field_ = _session.getCompoFactory().newTextField(20);
//        _session.setLabel(area_);
        _session.addFinder(field_,search_);
//        JPanel group_ = new JPanel();
//        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
        AbsScrollPane scrollSession_ = _session.getScroll();
        scrollSession_.setPreferredSize(new MetaDimension(400, 400));
//        group_.add(scrollSession_);
        content.add(scrollSession_);
//        panel_.add(area_);
        content.add(field_);
        content.add(search_);
        AbsButton hide_ = _session.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderPkGameDetail.HIDE));
        hide_.addActionListener(new HidePkDetailContentEvent(content, _p));
        content.add(hide_);
        content.setVisible(true);
//        absDialog.setContentPane(panel_);
//        timer = new Timer(200, new Chronometer(area_, _session, 0));
//        timer.start();
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
//        absDialog.pack();
    }

    public AbsPanel getContent() {
        return content;
    }

    public static StringMap<String> file(TranslationsLg _lg) {
        return GamesPk.getPkGameDetailContentTr(GamesPk.getAppliTr(_lg)).getMapping();
    }
}
