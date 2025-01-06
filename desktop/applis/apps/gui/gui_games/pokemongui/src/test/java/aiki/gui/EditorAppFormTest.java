package aiki.gui;

import aiki.db.*;
import aiki.facade.*;
import aiki.fight.pokemon.*;
import aiki.gui.components.editor.*;
import aiki.instances.Instances;
import aiki.sml.*;
import code.mock.*;
import code.threads.*;
import org.junit.Test;

public final class EditorAppFormTest extends InitEditorPkForm {
    @Test
    public void validateCopy() {
        DataBase dataBase_ = ConverterCommonMapUtil.validateData(initDb(), new ConcreteInteger(), new ConcreteBoolean(), new MockLSexList());
        assertFalse(dataBase_.isError());
    }
    @Test
    public void saveLoad() {
        FacadeGame f_ = new FacadeGame();
        f_.setSexList(new MockLSexList());
        f_.setData(initDb());
        MockProgramInfos api_ = initForms();
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        ConverterCommonMapUtil.saveData(api_,"/__/_", f_);
        assertFalse(ConverterCommonMapUtil.endValidate(new ConcreteInteger(), new ConcreteBoolean(),f_.getSexList(),ConverterCommonMapUtil.loadData(api_,"/__/_",f_)).isError());
    }
    @Test
    public void patchData1() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.getLitterals().clear();
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getLitterals().size());
        assertEq(DataBaseConstants.MAX_EXCLUSIVE-1,res_.getLitterals().getValue(0).size());
        assertEq(DataBaseConstants.MAX_EXCLUSIVE-1,res_.getLitterals().getValue(1).size());
    }
    @Test
    public void patchData2() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.getLitterals().getValue(0).clear();
        db_.getLitterals().getValue(1).clear();
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getLitterals().size());
        assertEq(DataBaseConstants.MAX_EXCLUSIVE-1,res_.getLitterals().getValue(0).size());
        assertEq(DataBaseConstants.MAX_EXCLUSIVE-1,res_.getLitterals().getValue(1).size());
    }
    @Test
    public void patchData3() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.defValues();
        db_.getLitterals().getValue(0).clear();
        db_.getLitterals().getValue(1).clear();
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getLitterals().size());
        assertEq(DataBaseConstants.MAX_EXCLUSIVE-1,res_.getLitterals().getValue(0).size());
        assertEq(DataBaseConstants.MAX_EXCLUSIVE-1,res_.getLitterals().getValue(1).size());
    }
    @Test
    public void patchData4() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.getAbilities().addEntry(DataBase.EMPTY_STRING, Instances.newAbilityData());
        db_.getItems().addEntry(DataBase.EMPTY_STRING, Instances.newSellingItem());
        db_.getMoves().addEntry(DataBase.EMPTY_STRING, Instances.newDamagingMoveData());
        db_.getMoves().addEntry(M_1, Instances.newDamagingMoveData());
        db_.getMoves().addEntry(M_2, Instances.newStatusMoveData());
        db_.getPokedex().addEntry(DataBase.EMPTY_STRING, Instances.newPokemonData());
        PokemonData pk_ = Instances.newPokemonData();
        pk_.getTypes().add(DataBase.EMPTY_STRING);
        pk_.getTypes().add(T_1);
        db_.getPokedex().addEntry(P_1, pk_);
        db_.getStatus().addEntry(DataBase.EMPTY_STRING, Instances.newStatusSimple());
        db_.getMiniPk().addEntry(DataBase.EMPTY_STRING,instance(new int[0][0]));
        db_.getTranslatedCategories().getValue(0).addEntry(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        db_.getTranslatedCategories().getValue(1).addEntry(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        db_.getTypesColors().addEntry(DataBase.EMPTY_STRING,DataBase.EMPTY_STRING);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedPokemon().size());
        assertEq(1,res_.getTranslatedPokemon().getValue(0).size());
        assertEq(1,res_.getTranslatedPokemon().getValue(1).size());
        assertEq(2,res_.getTranslatedTypes().size());
        assertEq(1,res_.getTranslatedTypes().getValue(0).size());
        assertEq(T_1,res_.getTranslatedTypes().getValue(0).getKey(0));
        assertEq(1,res_.getTranslatedTypes().getValue(1).size());
        assertEq(T_1,res_.getTranslatedTypes().getValue(1).getKey(0));
    }
    @Test
    public void patchData5() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.getTranslatedStatistics().clear();
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedStatistics().size());
        assertEq(10,res_.getTranslatedStatistics().getValue(0).size());
        assertEq(10,res_.getTranslatedStatistics().getValue(1).size());
    }
    @Test
    public void patchData6() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.getTranslatedPokemon().getValue(0).addEntry(P_1,P_1);
        db_.getTranslatedPokemon().getValue(0).addEntry(P_2,P_1);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedPokemon().size());
        assertEq(2,res_.getTranslatedPokemon().getValue(0).size());
        assertEq(2,res_.getTranslatedPokemon().getValue(1).size());
    }
    @Test
    public void patchData7() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        PokemonData p_ = Instances.newPokemonData();
        p_.getEvolutions().addEntry(P_2,Instances.newEvolutionHappiness());
        db_.getPokedex().addEntry(P_1, p_);
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertEq(2,res_.getTranslatedPokemon().size());
        assertEq(2,res_.getTranslatedPokemon().getValue(0).size());
        assertEq(2,res_.getTranslatedPokemon().getValue(1).size());
    }
}
