package aiki.gui.components.editor;

import aiki.fight.moves.effects.*;
import code.gui.*;
import code.scripts.pages.aiki.*;

public final class ContentComponentModelEffectProtection {

    private AbsCustCheckBox protSingle;
    private AbsCustCheckBox protTeamAgainstMultTargets;
    private AbsCustCheckBox protTeamAgainstPrio;
    private AbsCustCheckBox protTeamAgainstStatusMoves;
    private AbsCustCheckBox protTeamAgainstDamageMoves;
    private GeneComponentModelRate protSingleAgainstKo;
    private AbsPanel form;
    AbsPanel effectForm(AbsGeneComponentModelEffect _core) {
        AbsPanel selected_ = _core.getProgramInfos().getCompoFactory().newLineBox();
        protSingle = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffprotection.M_P_55_PROT_SINGLE,protSingle));
        protTeamAgainstMultTargets = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffprotection.M_P_55_PROT_MULTI_TARGETS,protTeamAgainstMultTargets));
        protTeamAgainstPrio = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffprotection.M_P_55_PROT_PRIO,protTeamAgainstPrio));
        protTeamAgainstStatusMoves = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffprotection.M_P_55_PROT_SINGLE_STATUS,protTeamAgainstStatusMoves));
        protTeamAgainstDamageMoves = _core.getProgramInfos().getCompoFactory().newCustCheckBox();
        selected_.add(line(_core,MessagesDataEffprotection.M_P_55_PROT_SINGLE_DAMAGE,protTeamAgainstDamageMoves));
        protSingleAgainstKo = new GeneComponentModelRate(_core.getProgramInfos());
        selected_.add(line(_core,MessagesDataEffprotection.M_P_55_PROT_SINGLE_KO_INTRO,protSingleAgainstKo.geneRate()));
        form = selected_;
        selected_.setVisible(false);
        return selected_;
    }
    private AbsCustComponent line(AbsGeneComponentModelEffect _core, String _key, AbsCustComponent _input) {
        return _core.line(MessagesPkBean.EFF_PROTECTION, _key,_input);
    }
    void display(boolean _dis) {
        form.setVisible(_dis);
    }
    void buildEntity(EffectProtection _edited) {
        _edited.setProtSingle(protSingle.isSelected());
        _edited.setProtTeamAgainstMultTargets(protTeamAgainstMultTargets.isSelected());
        _edited.setProtTeamAgainstPrio(protTeamAgainstPrio.isSelected());
        _edited.setProtTeamAgainstStatusMoves(protTeamAgainstStatusMoves.isSelected());
        _edited.setProtTeamAgainstDamageMoves(protTeamAgainstDamageMoves.isSelected());
        _edited.setProtSingleAgainstKo(protSingleAgainstKo.valueRate());
    }
    void feedForm(EffectProtection _edited) {
        protSingle.setSelected(_edited.getProtSingle());
        protTeamAgainstMultTargets.setSelected(_edited.getProtTeamAgainstMultTargets());
        protTeamAgainstPrio.setSelected(_edited.getProtTeamAgainstPrio());
        protTeamAgainstStatusMoves.setSelected(_edited.isProtTeamAgainstStatusMoves());
        protTeamAgainstDamageMoves.setSelected(_edited.isProtTeamAgainstDamageMoves());
        protSingleAgainstKo.valueRate(_edited.getProtSingleAgainstKo());
    }

    public AbsCustCheckBox getProtSingle() {
        return protSingle;
    }

    public AbsCustCheckBox getProtTeamAgainstDamageMoves() {
        return protTeamAgainstDamageMoves;
    }

    public AbsCustCheckBox getProtTeamAgainstMultTargets() {
        return protTeamAgainstMultTargets;
    }

    public AbsCustCheckBox getProtTeamAgainstPrio() {
        return protTeamAgainstPrio;
    }

    public AbsCustCheckBox getProtTeamAgainstStatusMoves() {
        return protTeamAgainstStatusMoves;
    }

    public GeneComponentModelRate getProtSingleAgainstKo() {
        return protSingleAgainstKo;
    }

}
