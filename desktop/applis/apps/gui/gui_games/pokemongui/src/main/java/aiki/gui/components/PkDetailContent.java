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
import code.threads.AbstractAtomicBooleanCore;
import code.util.StringMap;

public final class PkDetailContent {

//    private RenderedPage session;

    private final AbsPanel content;
    private AbsButton search;
    private AbsTextField field;
    private AbsButton hide;

    public PkDetailContent(AbstractProgramInfos _frameFactory) {
        content = _frameFactory.getCompoFactory().newPageBox();
        content.setVisible(false);
    }

    public void group(WindowAiki _parent, FacadeGame _dataBase, AikiNatLgNamesNavigation _pre, String _lg, Packable _p, AbstractAtomicBooleanCore _at) {
        StringMap<String> messages_ = file(_parent.getFrames().currentLg());
//        DialogHtmlData d_ = _parent.getDialogHtmlData();
        _pre.getBeanNatLgNames().setDataBase(_dataBase);
        RenderedPage session_ = FrameHtmlData.initializeOnlyConf(_pre, _lg, _pre.getBeanNatLgNames(), _parent.getFrames());
//        d_.messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), d_.absDialog.getAccessFile());
//        session = _session;
//        _session.setFrame(absDialog);
        content.removeAll();
//        AbsPlainLabel area_ = _session.getCompoFactory().newPlainLabel(TEXT);
//        LabelButton search_ = new LabelButton(MainWindow.OK);
        search = session_.getCompoFactory().newPlainButton(messages_.getVal(MessagesRenderPkGameDetail.SEARCH_LABEL));
        field = session_.getCompoFactory().newTextField(20);
//        _session.setLabel(area_);
        session_.addFinder(field,search);
//        JPanel group_ = new JPanel();
//        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
        AbsScrollPane scrollSession_ = session_.getScroll();
        scrollSession_.setPreferredSize(new MetaDimension(400, 400));
//        group_.add(scrollSession_);
        content.add(scrollSession_);
//        panel_.add(area_);
        content.add(field);
        content.add(search);
        if (_at != null) {
            hide = session_.getCompoFactory().newPlainButton(messages_.getVal(MessagesRenderPkGameDetail.HIDE));
            hide.addActionListener(new HidePkDetailContentEvent(content, _p, _at));
            content.add(hide);
        }
        content.setVisible(true);
//        absDialog.setContentPane(panel_);
//        timer = new Timer(200, new Chronometer(area_, _session, 0));
//        timer.start();
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
//        absDialog.pack();
        content.setTitledBorder(messages_.getVal(MessagesRenderPkGameDetail.TITLE));
//        d_.session.setFrame(d_.absDialog);
//        d_.absDialog.setVisible(true);
    }

    public AbsPanel getContent() {
        return content;
    }

    public AbsButton getSearch() {
        return search;
    }

    public AbsTextField getField() {
        return field;
    }

    public AbsButton getHide() {
        return hide;
    }

    public static StringMap<String> file(TranslationsLg _lg) {
        return GamesPk.getPkGameDetailContentTr(GamesPk.getAppliTr(_lg)).getMapping();
    }
}
