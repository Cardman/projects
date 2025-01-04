package aiki.gui;

import aiki.db.*;
import aiki.facade.*;
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
        assertFalse(res_.getLitterals().isEmpty());
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
        assertFalse(res_.getLitterals().isEmpty());
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
        assertFalse(res_.getLitterals().isEmpty());
    }
    @Test
    public void patchData4() {
        MockProgramInfos api_ = initForms();
        FacadeGame f_ = ConverterCommonMapUtil.facadeInit(api_);
        f_.setSexList(new MockLSexList());
        MessagesPkGame.sys(MessagesPkGame.initAppliFilesTr(api_.getTranslations()));
        DataBase db_ = DocumentReaderAikiCoreUtil.initData(api_.getGenerator(), f_);
        db_.getPokedex().addEntry("", Instances.newPokemonData());
        DataBase res_ = ConverterCommonMapUtil.patchData(api_, db_);
        assertTrue(res_.getTranslatedPokemon().getValue(0).isEmpty());
        assertTrue(res_.getTranslatedPokemon().getValue(1).isEmpty());
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
        assertFalse(res_.getTranslatedStatistics().isEmpty());
    }
}
