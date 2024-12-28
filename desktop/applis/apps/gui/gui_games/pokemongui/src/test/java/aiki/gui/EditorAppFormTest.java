package aiki.gui;

import aiki.db.*;
import aiki.gui.components.editor.*;
import code.mock.*;
import code.threads.*;
import org.junit.Test;

public final class EditorAppFormTest extends InitEditorPkForm {
    @Test
    public void validateCopy() {
        DataBase dataBase_ = ConverterCommonMapUtil.validateData(initDb(), new ConcreteInteger(), new ConcreteBoolean(), new MockLSexList());
        assertFalse(dataBase_.isError());
    }
}
