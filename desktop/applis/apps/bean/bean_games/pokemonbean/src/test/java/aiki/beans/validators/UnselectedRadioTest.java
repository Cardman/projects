package aiki.beans.validators;

import aiki.beans.BeanPokemonCommonTs;
import code.formathtml.structs.Message;
import code.util.StringList;
import org.junit.Test;

public final class UnselectedRadioTest extends BeanPokemonCommonTs {
    @Test
    public void validate1() {
        assertNull(validate(new StringList("_")));
    }
    @Test
    public void validate2() {
        Message message_ = validate(new StringList());
        assertEq("",message_.getContent());
        assertEq(0,message_.getArgs().size());
    }

    private Message validate(StringList _v) {
        return new UnselectedRadio().validate(_v);
    }
}
