package aiki.beans.validators;

import aiki.beans.BeanPokemonCommonTs;
import code.formathtml.structs.Message;
import code.util.StringList;
import org.junit.Test;

public final class PositiveRateValidatorTest extends BeanPokemonCommonTs {
    @Test
    public void validate1() {
        assertNull(validate("1"));
    }
    @Test
    public void validate2() {
        Message message_ = validate("");
        assertEq("",message_.getContent());
        assertEq(1,message_.getArgs().size());
        assertEq("",message_.getArgs().get(0));
    }
    @Test
    public void validate3() {
        Message message_ = validate("-1");
        assertEq("",message_.getContent());
        assertEq(1,message_.getArgs().size());
        assertEq("-1",message_.getArgs().get(0));
    }
    @Test
    public void validate4() {
        assertNull(validate("0"));
    }
    @Test
    public void validate5() {
        Message message_ = validate("-");
        assertEq("",message_.getContent());
        assertEq(1,message_.getArgs().size());
        assertEq("-",message_.getArgs().get(0));
    }
    @Test
    public void validate6() {
        Message message_ = validate();
        assertEq("",message_.getContent());
        assertEq(1,message_.getArgs().size());
        assertEq("",message_.getArgs().get(0));
    }
    private Message validate(String _v) {
        return validate(new StringList(_v));
    }
    private Message validate() {
        return validate(new StringList());
    }
    private Message validate(StringList _v) {
        return new PositiveRateValidator().validate(_v);
    }
}
