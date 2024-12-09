package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;

public final class CrudGeneFormEntImgUniq implements AbsCrudGeneFormTrCstOpen {


    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final AbsCommonFrame frame;
    private final IdMap<ImgFieldRetriever,ContentGeneComponentModelImg> fields = new IdMap<ImgFieldRetriever,ContentGeneComponentModelImg>();
    private final IdMap<ImgFieldRetriever,AbsButton> buttons = new IdMap<ImgFieldRetriever,AbsButton>();
    private final SubscribedTranslationList subscribedTranslationList;

    public CrudGeneFormEntImgUniq(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub, AbsCommonFrame _f) {
        api = _core;
        facadeGame = _facade;
        frame = _f;
        subscribedTranslationList = _sub;
    }


    @Override
    public void initFormAll() {
        AbsPanel content_ = api.getCompoFactory().newPageBox();
        AbsPanel page_ = api.getCompoFactory().newPageBox();
        fields.clear();
        buttons.clear();
        line(page_, subscribedTranslationList.getImgFieldRetrieverAnimAbsorb(), 0);
        line(page_, subscribedTranslationList.getImgFieldRetrieverEndGame(),1);
        line(page_, subscribedTranslationList.getImgFieldRetrieverStorage(),2);
        line(page_, subscribedTranslationList.getImgFieldRetrieverTmHm(),3);
        content_.add(api.getCompoFactory().newAbsScrollPane(page_));
        frame.setContentPane(content_);
        frame.setVisible(true);
        frame.pack();
    }

    private void line(AbsPanel _page, ImgFieldRetriever _key, int _value) {
        AbsPanel line_ = api.getCompoFactory().newLineBox();
        ContentGeneComponentModelImg cont_ = new ContentGeneComponentModelImg();
        line_.add(cont_.gene(api,null));
        ImageArrayBaseSixtyFour img_ = _key.get(facadeGame);
        cont_.updateImg(ConverterCommonMapUtil.copyImageArrayBaseSixtyFour(img_),api);
        AbsButton but_ = api.getCompoFactory().newPlainButton(Long.toString(_value));
        but_.addActionListener(new AssociateImgOtherEvent(this, _key));
        _page.add(line_);
        _page.add(but_);
        fields.addEntry(_key, cont_);
        buttons.addEntry(_key, but_);
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public IdMap<ImgFieldRetriever, ContentGeneComponentModelImg> getFields() {
        return fields;
    }

    public IdMap<ImgFieldRetriever, AbsButton> getButtons() {
        return buttons;
    }

    public void apply(ImgFieldRetriever _k) {
        _k.set(facadeGame,fields.getVal(_k).edited(api));
    }

}
