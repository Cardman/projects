package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.game.params.enums.*;
import aiki.util.*;
import code.gui.*;
import code.gui.initialize.*;
import code.maths.*;
import code.scripts.pages.aiki.*;
import code.util.*;

public final class CrudGeneFormTrCstLaw implements AbsCrudGeneFormTrCstOpen, CrudGeneFormTrCstParamList<DifficultyModelLaw> {
    private final AbstractProgramInfos api;
    private final AbsCommonFrame frame;
    private final FacadeGame facadeGame;
    private final IdMap<DifficultyModelLaw,CrudGeneFormMonteCarlo<Rate>> fields = new IdMap<DifficultyModelLaw,CrudGeneFormMonteCarlo<Rate>>();
    private final IdMap<DifficultyModelLaw,AbsButton> buttons = new IdMap<DifficultyModelLaw,AbsButton>();

    public CrudGeneFormTrCstLaw(AbstractProgramInfos _core, FacadeGame _f) {
        api = _core;
        frame = _core.getFrameFactory().newCommonFrame();
        facadeGame = _f;
    }

    @Override
    public void initFormAll() {
        AbsPanel content_ = api.getCompoFactory().newPageBox();
        AbsPanel page_ = api.getCompoFactory().newPageBox();
        page_.setTitledBorder(MessagesPkEditor.getMessagesEditorSelectDataMapLevTr(MessagesPkEditor.getAppliTr(api.currentLg())).getMapping().getVal(MessagesEditorSelect.CST_DIFF_LAW));
        fields.clear();
        buttons.clear();
        int i_ = 0;
        for (EntryCust<DifficultyModelLaw, LawNumber> k: facadeGame.getData().getLawsDamageRate().entryList()) {
            AbsPanel line_ = api.getCompoFactory().newLineBox();
            CrudGeneFormMonteCarlo<Rate> form_ = ConverterCommonMapUtil.buildMcRate(frame, api, MessagesPkBean.ROUND,MessagesDataRound.M_P_83_ROUND_PROCESS_MOVE_FINAL_20_1,MessagesDataRound.M_P_83_ROUND_PROCESS_MOVE_FINAL_20_2);
            form_.setupValues(new MapToEntriesListUtil<Rate, LgInt>().build(k.getValue().getLaw()));
            line_.add(form_.getGroup());
            AbsButton but_ = api.getCompoFactory().newPlainButton(Long.toString(i_));
            but_.addActionListener(new RenameCstParamEvent<DifficultyModelLaw>(this, k.getKey()));
            line_.add(but_);
            page_.add(line_);
            fields.addEntry(k.getKey(), form_);
            buttons.addEntry(k.getKey(),but_);
            i_++;
        }
        content_.add(api.getCompoFactory().newAbsScrollPane(page_));
        frame.setContentPane(content_);
        frame.setVisible(true);
        frame.pack();
    }

    public AbsCommonFrame getFrame() {
        return frame;
    }

    public IdMap<DifficultyModelLaw,CrudGeneFormMonteCarlo<Rate>> getFields() {
        return fields;
    }

    public IdMap<DifficultyModelLaw, AbsButton> getButtons() {
        return buttons;
    }

    @Override
    public void apply(DifficultyModelLaw _k) {
        Integer nb_ = facadeGame.getData().getLawsDamageRate().getVal(_k).getNumber();
        facadeGame.getData().getLawsDamageRate().put(_k, new LawNumber(ConverterCommonMapUtil.buildMonteCarloNumber(fields.getVal(_k).getList()),nb_));
    }
}
