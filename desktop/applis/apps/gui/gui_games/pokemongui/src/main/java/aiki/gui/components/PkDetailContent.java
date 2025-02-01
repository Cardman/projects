package aiki.gui.components;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.listeners.HidePkDetailContentEvent;
import aiki.sml.*;
import code.gui.*;
import code.gui.document.PkPlayerRender;
import code.gui.document.WrapBeanRender;
import code.gui.initialize.AbstractProgramInfos;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicBooleanCore;
import code.util.StringMap;

public final class PkDetailContent {

//    private RenderedPage session;

    private final AbsPanel content;
//    private AbsButton search;
//    private AbsTextField field;
    private AbsButton hide;
    private final WrapBeanRender pkPlayerRender;

    public PkDetailContent(AbstractProgramInfos _frameFactory) {
        content = _frameFactory.getCompoFactory().newPageBox();
        content.setVisible(false);
        pkPlayerRender = new WrapBeanRender(content);
    }

    public void group(WindowAiki _parent, FacadeGame _dataBase, Packable _p, AbstractAtomicBooleanCore _at) {
        PkPlayerRender ren_ = new PkPlayerRender();
        pkPlayerRender.getRenders().addEntry("",ren_);
        pkPlayerRender.display(ren_,_parent.getFrames(), _dataBase, _parent.getCommonFrame());
        StringMap<String> messages_ = file(_parent.getFrames().currentLg());
////        DialogHtmlData d_ = _parent.getDialogHtmlData();
//        AikiNatLgNamesNavigation res_ = _pre.attendreResultat();
//        res_.getBeanNatLgNames().setDataBase(_dataBase);
////        res_.getBeanNatLgNames().setBaseEncode(GamesPk.baseEncode(_parent.getFrames().getTranslations()));
//        RenderedPage session_ = FrameHtmlData.initializeOnlyConf(res_, _lg, res_.getBeanNatLgNames(), _parent.getFrames(), _parent.getGuardRender());
////        d_.messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _parent.getLanguageKey(), d_.absDialog.getAccessFile());
////        session = _session;
////        _session.setFrame(absDialog);
//        content.removeAll();
////        AbsPlainLabel area_ = _session.getCompoFactory().newPlainLabel(TEXT);
////        LabelButton search_ = new LabelButton(MainWindow.OK);
//        search = session_.getCompoFactory().newPlainButton(messages_.getVal(MessagesRenderPkGameDetail.SEARCH_LABEL));
//        field = session_.getCompoFactory().newTextField(20);
////        _session.setLabel(area_);
//        session_.addFinder(field,search);
////        JPanel group_ = new JPanel();
////        group_.setLayout(new BoxLayout(group_, BoxLayout.PAGE_AXIS));
//        AbsScrollPane scrollSession_ = session_.getScroll();
//        scrollSession_.setPreferredSize(new MetaDimension(400, 400));
////        group_.add(scrollSession_);
//        content.add(scrollSession_);
////        panel_.add(area_);
//        content.add(field);
//        content.add(search);
        if (_at != null) {
            hide = _parent.getFrames().getCompoFactory().newPlainButton(messages_.getVal(MessagesRenderPkGameDetail.HIDE));
            hide.addActionListener(new HidePkDetailContentEvent(content, _p, _at));
            content.add(hide);
        }
        content.setVisible(true);
//        absDialog.setContentPane(panel_);
//        timer = new Timer(200, new Chronometer(area_, _session, 0));
//        timer.start();
//        absDialog.setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
//        absDialog.pack();
//        content.setTitledBorder(messages_.getVal(MessagesRenderPkGameDetail.TITLE));
//        d_.session.setFrame(d_.absDialog);
//        d_.absDialog.setVisible(true);
    }

    public AbsPanel getContent() {
        return content;
    }

    public AbsScrollPane getScrollPane(){
        return pkPlayerRender.getCurrent().getBuilder().getScrollPane();
    }

    public AbsButton getSearch() {
        return pkPlayerRender.getSearch();
    }

    public AbsTextField getField() {
        return pkPlayerRender.getField();
    }

    public AbsButton getHide() {
        return hide;
    }

    public static StringMap<String> file(TranslationsLg _lg) {
        return MessagesPkGame.getPkGameDetailContentTr(MessagesPkGame.getAppliTr(_lg)).getMapping();
    }
}
