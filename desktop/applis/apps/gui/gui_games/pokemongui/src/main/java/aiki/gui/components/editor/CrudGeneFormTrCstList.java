package aiki.gui.components.editor;

import aiki.db.*;
import aiki.facade.*;
import code.gui.*;
import code.gui.initialize.*;
import code.util.*;
import code.util.core.*;

public final class CrudGeneFormTrCstList implements AbsCrudGeneFormTrCstOpen {
    private final AbstractProgramInfos api;
    private final FacadeGame facadeGame;
    private final SubscribedTranslationList subscribedTranslations;
    private final AbsCommonFrame frame;
    private final StringMap<AbsTextField> fields = new StringMap<AbsTextField>();

    public CrudGeneFormTrCstList(AbstractProgramInfos _core, FacadeGame _facade, SubscribedTranslationList _sub) {
        api = _core;
        facadeGame = _facade;
        subscribedTranslations = _sub;
        frame = _core.getFrameFactory().newCommonFrame();
    }

    @Override
    public void initFormAll() {
        AbsPanel content_ = api.getCompoFactory().newPageBox();
        AbsPanel page_ = api.getCompoFactory().newPageBox();
        fields.clear();
        for (int i = 0; i < 157; i++) {
            AbsPanel line_ = api.getCompoFactory().newLineBox();
            AbsTextField field_ = api.getCompoFactory().newTextField(16);
            String key_ = Long.toString(i);
            field_.addActionListener(new RenameCstEvent(this, key_, field_));
            line_.add(field_);
            page_.add(line_);
            fields.addEntry(key_, field_);
        }
        content_.add(api.getCompoFactory().newAbsScrollPane(page_));
        frame.setContentPane(content_);
        frame.setVisible(true);
        frame.pack();
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public StringMap<AbsTextField> getFields() {
        return fields;
    }

    public void apply(String _k, AbsTextField _f) {
        StringMap<String> bk_ = new StringMap<String>();
        for (int i = 0; i < 157; i++) {
            bk_.addEntry(Long.toString(i), facadeGame.getData().retValueOther(Long.toString(i)));
        }
        String old_ = facadeGame.getData().retValueOther(_k);
        String typed_ = _f.getText();
        facadeGame.getData().initValueOther(_k, typed_);
        facadeGame.getData().validateOtherConstants();
        String next_ = facadeGame.getData().retValueOther(_k);
        if (!StringUtil.quickEq(next_, typed_)) {
            for (EntryCust<String,String> e: bk_.entryList()) {
                facadeGame.getData().initValueOther(e.getKey(), e.getValue());
            }
            return;
        }
        if (StringUtil.quickEq(_k, DataBaseConstants.PREFIX_KEY)) {
            facadeGame.getData().changePrefInNumericExpressions(old_,next_);
            subscribedTranslations.getModifiedEntitiesRename().renameExp(facadeGame, new PrefixRenamingDataBase(old_,next_));
            subscribedTranslations.updateRenamingPref(old_,next_,new StringList());
            subscribedTranslations.update();
            return;
        }
        facadeGame.getData().changeMidInNumericExpressions(old_, next_);
        subscribedTranslations.getModifiedEntitiesRename().renameExp(facadeGame, new MidRenamingDataBase(old_, next_));
        subscribedTranslations.updateRenamingMid(old_,next_,new StringList());
        subscribedTranslations.update();
    }
}
