package aiki.gui;

import aiki.db.*;
import aiki.facade.*;
import aiki.facade.enums.*;
import aiki.fight.enums.*;
import aiki.fight.items.*;
import aiki.fight.moves.enums.*;
import aiki.fight.pokemon.*;
import aiki.fight.pokemon.enums.*;
import aiki.game.params.enums.*;
import aiki.gui.components.editor.*;
import aiki.map.levels.enums.*;
import aiki.map.pokemon.enums.*;
import aiki.util.LawNumber;
import code.gui.*;
import code.gui.events.*;
import code.gui.files.*;
import code.maths.*;
import code.maths.litteral.*;
import code.maths.montecarlo.*;
import code.mock.*;
import code.util.*;
import org.junit.Test;

public final class EditorStatFormTest extends InitEditorPkForm {
    @Test
    public void statForm1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCst<Statistic> cTr_ = crudTrStat(sub_);
        cTr_.getFields().getVal(Statistic.SPEED).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedStatistics().getVal(pr_.getLanguage()).getVal(Statistic.SPEED));
    }
    @Test
    public void statForm2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCst<TargetChoice> cTr_ = crudTrTarget(sub_);
        cTr_.getFields().getVal(TargetChoice.ANY_FOE).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedTargets().getVal(pr_.getLanguage()).getVal(TargetChoice.ANY_FOE));
    }
    @Test
    public void statForm3() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCst<Gender> cTr_ = crudTrGender(sub_);
        cTr_.getFields().getVal(Gender.NO_GENDER).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedGenders().getVal(pr_.getLanguage()).getVal(Gender.NO_GENDER));
    }
    @Test
    public void statForm4() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCst<EnvironmentType> cTr_ = crudTrEnvironmentType(sub_);
        cTr_.getFields().getVal(EnvironmentType.NOTHING).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedEnvironment().getVal(pr_.getLanguage()).getVal(EnvironmentType.NOTHING));
    }
    @Test
    public void statForm5() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrItemType cTr_ = crudTrClType(sub_);
        cTr_.getFields().getVal(Item.BALL).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedClassesDescriptions().getVal(pr_.getLanguage()).getVal(Item.BALL));
    }
    @Test
    public void statForm6() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCst<DifficultyModelLaw> cTr_ = crudTrDifficultyModelLaw(sub_);
        cTr_.getFields().getVal(DifficultyModelLaw.UNIFORME).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedDiffModelLaw().getVal(pr_.getLanguage()).getVal(DifficultyModelLaw.UNIFORME));
    }
    @Test
    public void statForm7() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCst<DifficultyWinPointsFight> cTr_ = crudTrDifficultyWinPointsFight(sub_);
        cTr_.getFields().getVal(DifficultyWinPointsFight.DIFFICILE).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedDiffWinPts().getVal(pr_.getLanguage()).getVal(DifficultyWinPointsFight.DIFFICILE));
    }
    @Test
    public void statForm8() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCst<SelectedBoolean> cTr_ = crudTrSelectedBoolean(sub_);
        cTr_.getFields().getVal(SelectedBoolean.YES_AND_NO).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedBooleans().getVal(pr_.getLanguage()).getVal(SelectedBoolean.YES_AND_NO));
    }
    @Test
    public void statForm9() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrItemType cTr_ = crudTrFctType(sub_);
        cTr_.getFields().getVal(MbOperationNode.PUIS).getVal(pr_.getLanguage()).setText("vit");
        tryClick(cTr_.getChangeValues());
        assertEq("vit",facade_.getData().getTranslatedFctMath().getVal(pr_.getLanguage()).getVal(MbOperationNode.PUIS));
    }
    @Test
    public void statForm10() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().defValues();
        facade_.getData().validateOtherConstants();
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCstList cTr_ = crudConstLitt(sub_);
        cTr_.getFieldsLitt().getVal(DataBaseConstants.KEY_NIVEAU).getVal(pr_.getLanguage()).get(0).setText("vit");
        cTr_.getFieldsLitt().getVal(DataBaseConstants.KEY_NIVEAU).getVal(pr_.getLanguage()).get(1).setText("v");
        cTr_.getFieldsLitt().getVal(DataBaseConstants.KEY_NIVEAU).getVal(pr_.getLanguage()).get(2).setText("desc");
        enterTextField(cTr_.getFields().getVal(DataBaseConstants.KEY_NIVEAU));
        assertEq("vit\tv\tdesc",facade_.getData().getLitterals().getVal(pr_.getLanguage()).getVal(MessagesDataBaseConstants.DEF_NIVEAU));
    }
    @Test
    public void statForm11() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getExpGrowth().addEntry(ExpType.M,"");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCstParamListString<ExpType> cTr_ = crudTrExpTypeType(sub_);
        cTr_.getFields().getVal(ExpType.M).valueString("vit");
        tryClick(cTr_.getButtons().getVal(ExpType.M));
        assertEq("vit",facade_.getData().getExpGrowth().getVal(ExpType.M));
    }
    @Test
    public void statForm12() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getLawsDamageRate().addEntry(DifficultyModelLaw.UNIFORME,new LawNumber(new MonteCarloNumber(),0));
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCstLaw cTr_ = crudTrDifficultyModelLawType(sub_);
        tryClick(cTr_.getFields().getVal(DifficultyModelLaw.UNIFORME).getAdd());
        ((GeneComponentModelEventRate)cTr_.getFields().getVal(DifficultyModelLaw.UNIFORME).getGene()).getEvent().valueRate(new Rate(2));
        ((GeneComponentModelEventRate)cTr_.getFields().getVal(DifficultyModelLaw.UNIFORME).getGene()).getProba().valueLgInt(LgInt.one());
        tryClick(cTr_.getFields().getVal(DifficultyModelLaw.UNIFORME).getValidAddEdit());
        tryClick(cTr_.getButtons().getVal(DifficultyModelLaw.UNIFORME));
        assertEq(1,facade_.getData().getLawsDamageRate().getVal(DifficultyModelLaw.UNIFORME).getLaw().size());
        assertEq(new Rate(2),facade_.getData().getLawsDamageRate().getVal(DifficultyModelLaw.UNIFORME).getLaw().getKey(0));
        assertEq(LgInt.one(),facade_.getData().getLawsDamageRate().getVal(DifficultyModelLaw.UNIFORME).getLaw().getFreq(0));
    }
    @Test
    public void statForm13() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        facade_.getData().getRates().addEntry(DifficultyWinPointsFight.DIFFICILE,"");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCstParamListString<DifficultyWinPointsFight> cTr_ = crudTrDifficultyWinPointsFightType(sub_);
        cTr_.getFields().getVal(DifficultyWinPointsFight.DIFFICILE).valueString("vit");
        tryClick(cTr_.getButtons().getVal(DifficultyWinPointsFight.DIFFICILE));
        assertEq("vit",facade_.getData().getRates().getVal(DifficultyWinPointsFight.DIFFICILE));
    }
    @Test
    public void strList1() {
        GeneComponentModelSubscribeStringList g_ = gene();
        g_.geneEnum(-1,0);
        tryClick(g_.crud().getAdd());
        g_.crud().getGenePair().getKey().setupValue(M_1);
        tryClick(g_.crud().getValidAddEdit());
        assertEq(1,g_.crud().getList().size());
        assertEq(M_1,g_.crud().getList().get(0));
    }
    @Test
    public void strList2() {
        GeneComponentModelSubscribeStringList g_ = gene();
        g_.geneEnum(-1,0);
        tryClick(g_.crud().getAdd());
        g_.crud().getGenePair().getKey().setupValue(M_1);
        tryClick(g_.crud().getValidAddEdit());
        tryClick(g_.crud().getAllButtons().get(0));
        g_.crud().getGenePair().getKey().setupValue(M_2);
        tryClick(g_.crud().getValidAddEdit());
        assertEq(1,g_.crud().getList().size());
        assertEq(M_2,g_.crud().getList().get(0));
    }
    @Test
    public void strList3() {
        GeneComponentModelSubscribeStringList g_ = gene();
        g_.geneEnum(-1,0);
        tryClick(g_.crud().getAdd());
        g_.crud().getGenePair().getKey().setupValue(M_1);
        tryClick(g_.crud().getValidAddEdit());
        tryClick(g_.crud().getAllButtons().get(0));
        tryClick(g_.crud().getValidRemove());
        assertEq(0,g_.crud().getList().size());
    }
    @Test
    public void strList4() {
        GeneComponentModelSubscribeStringList g_ = gene();
        g_.geneEnum(-1,0);
        g_.setupValue(new StringList(M_1));
        assertEq(1,g_.crud().getList().size());
        assertEq(M_1,g_.crud().getList().get(0));
    }

    @Test
    public void strList5() {
        GeneComponentModelSubscribeStringList g_ = gene();
        g_.geneEnum(-1,0);
        g_.setupValue(new StringList(M_1));
        StringList ls_ = g_.tryRet();
        assertEq(1,ls_.size());
        assertEq(M_1,ls_.get(0));
    }
    @Test
    public void strList6() {
        GeneComponentModelSubscribeStringList g_ = gene();
        g_.geneEnum(0,0);
        g_.setupValue(new StringList(M_1));
        StringList ls_ = g_.tryRet();
        assertEq(1,ls_.size());
        assertEq(M_1,ls_.get(0));
        assertFalse(g_.getSubs().isEmpty());
    }
    @Test
    public void otherCst() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().defValues();
        facade_.getData().validateOtherConstants();
        facade_.getData().prefixVar("VAR");
        facade_.getData().nbTour("NB_TOUR");
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrOtherCstList c_ = crudConst(sub_);
        c_.getFields().getVal(DataBase.DEF_CAT).setText("category");
        tryClick(c_.getButtons().getVal(DataBase.DEF_CAT));
        assertEq("category",facade_.getData().getDefCategory());
    }
    @Test
    public void numCst() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getConstNum().put(DataBase.DEF_BASE_MOVE,new Rate(2));
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCstNumList c_ = crudNumConst(sub_);
        c_.getFields().getVal(DataBase.DEF_BASE_MOVE).valueRate(new Rate(3));
        enterTextField(c_.getFields().getVal(DataBase.DEF_BASE_MOVE).getTextRate());
        assertEq(new Rate(3),facade_.getData().getDefBaseMove());
    }
    @Test
    public void numMoves1() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getConstNum().put(DataBase.DEF_MAX_ATT,new Rate(4));
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCstNumList c_ = crudNumConst(sub_);
        c_.getFields().getVal(DataBase.DEF_MAX_ATT).valueRate(new Rate(8));
        enterTextField(c_.getFields().getVal(DataBase.DEF_MAX_ATT).getTextRate());
        assertEq(8, facade_.getData().getNbMaxMoves());
    }
    @Test
    public void numMoves2() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facade(pr_);
        facade_.getData().getConstNum().put(DataBase.DEF_MAX_ATT,new Rate(4));
        WindowPkEditor sub_ = window(pr_, facade_);
        CrudGeneFormTrCstNumList c_ = crudNumConst(sub_);
        enterTextField(c_.getFields().getVal(DataBase.DEF_MAX_ATT).getTextRate());
        assertEq(4,facade_.getData().getNbMaxMoves());
    }
    @Test
    public void noKey() {
        MockProgramInfos pr_ = initForms();
        WindowPkEditor w_ = new WindowPkEditor(pr_, new LanguagesButtonsPair(null,new MockPlainButton(),new LanguageComponentButtons(pr_,new AlwaysActionListenerAct())));
        tryClick(w_.getPkMenu());
        CrudGeneFormEnt<PokemonData> c_ = w_.getCrudGeneFormPk();
        tryClick(c_.getAdd());
        tryClick(c_.getValidAddEdit());
        assertEq(0,w_.getFacade().getData().getPokedex().size());
    }
    @Test
    public void createData() {
        MockProgramInfos pr_ = initForms();
        MessagesGuiFct.enTr(MessagesGuiFct.initAppliTr(pr_.getTranslations().getMapping().getVal(EN)));
        MessagesGuiFct.frTr(MessagesGuiFct.initAppliTr(pr_.getTranslations().getMapping().getVal(FR)));
        WindowPkEditor w_ = new WindowPkEditor(pr_, new LanguagesButtonsPair(null,new MockPlainButton(),new LanguageComponentButtons(pr_,new AlwaysActionListenerAct())));
        crudNumConst(w_);
        tryClick(w_.getNewDataSet());
        assertEq(0,w_.getFacade().getData().getPokedex().size());
    }
    private GeneComponentModelSubscribeStringList gene() {
        MockProgramInfos pr_ = initForms();
        FacadeGame facade_ = facadeAdd(pr_);
        SubscribedTranslationList sub_ = new SubscribedTranslationList(pr_,facade_);
        AbsCommonFrame fr_ = pr_.getFrameFactory().newCommonFrame();
        sub_.getSubscribedTranslations().addEntry(fr_,new IdList<SubscribedTranslation>());
        return new GeneComponentModelSubscribeStringList(pr_, facade_, sub_, fr_, sub_.getFactoryMv());
    }

    private FacadeGame facadeAdd(MockProgramInfos _m) {
        FacadeGame f_ = facade(_m);
        f_.getData().setVarParamsMove(new StringMap<StringList>());
        return f_;
    }
    private CrudGeneFormTrCst<Statistic> crudTrStat(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCstStatMenu());
        return _crud.getCrudGeneFormCstStat();
    }
    private CrudGeneFormTrCst<TargetChoice> crudTrTarget(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCstTargetMenu());
        return _crud.getCrudGeneFormCstTarget();
    }
    private CrudGeneFormTrCst<Gender> crudTrGender(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCstGenderMenu());
        return _crud.getCrudGeneFormCstGender();
    }
    private CrudGeneFormTrCst<EnvironmentType> crudTrEnvironmentType(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCstEnvironmentTypeMenu());
        return _crud.getCrudGeneFormCstEnvironmentType();
    }
    private CrudGeneFormTrCst<DifficultyModelLaw> crudTrDifficultyModelLaw(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCstDifficultyModelLawMenu());
        return _crud.getCrudGeneFormCstDifficultyModelLaw();
    }
    private CrudGeneFormTrCst<DifficultyWinPointsFight> crudTrDifficultyWinPointsFight(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCstDifficultyWinPointsFightMenu());
        return _crud.getCrudGeneFormCstDifficultyWinPointsFight();
    }
    private CrudGeneFormTrCst<SelectedBoolean> crudTrSelectedBoolean(WindowPkEditor _crud) {
        tryClick(_crud.getTrsCstSelectedBooleanMenu());
        return _crud.getCrudGeneFormCstSelectedBoolean();
    }
    private CrudGeneFormTrItemType crudTrClType(WindowPkEditor _crud) {
        tryClick(_crud.getTrsClMenu());
        return _crud.getCrudGeneFormClTr();
    }
    private CrudGeneFormTrItemType crudTrFctType(WindowPkEditor _crud) {
        tryClick(_crud.getTrsFctMenu());
        return _crud.getCrudGeneFormFctTr();
    }

    private CrudGeneFormTrCstList crudConstLitt(WindowPkEditor _crud) {
        tryClick(_crud.getTrsConstMenu());
        return _crud.getCrudGeneFormTrCstList();
    }
    private CrudGeneFormTrCstParamListString<ExpType> crudTrExpTypeType(WindowPkEditor _crud) {
        tryClick(_crud.getTrsExpTypeMenu());
        return _crud.getCrudGeneFormExpTypeTr();
    }

    private CrudGeneFormTrCstLaw crudTrDifficultyModelLawType(WindowPkEditor _crud) {
        tryClick(_crud.getTrsDifficultyModelLawMenu());
        return _crud.getCrudGeneFormDifficultyModelLawTr();
    }

    private CrudGeneFormTrCstParamListString<DifficultyWinPointsFight> crudTrDifficultyWinPointsFightType(WindowPkEditor _crud) {
        tryClick(_crud.getTrsDifficultyWinPointsFightMenu());
        return _crud.getCrudGeneFormDifficultyWinPointsFightTr();
    }

    private CrudGeneFormTrOtherCstList crudConst(WindowPkEditor _crud) {
        tryClick(_crud.getTrsOtherConstMenu());
        return _crud.getCrudGeneFormTrOtherCstList();
    }

    private CrudGeneFormTrCstNumList crudNumConst(WindowPkEditor _crud) {
        tryClick(_crud.getTrsNumConstMenu());
        return _crud.getCrudGeneFormTrCstNumList();
    }
}
