package aiki.gui;

import aiki.db.*;
import aiki.facade.*;
import aiki.gui.components.editor.*;
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
}
