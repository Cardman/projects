package code.expressionlanguage.gui.unit;

import code.expressionlanguage.utilcompo.AbstractInterceptor;
import code.expressionlanguage.utilcompo.AbstractIssuer;
import code.expressionlanguage.utilcompo.MemInputFiles;
import code.gui.AbstractAdvGraphicListGeneratorStruct;
import code.gui.initialize.AbstractAdvGraphicListGenerator;
import code.gui.initialize.AbstractLightProgramInfos;
import code.util.core.StringUtil;

public final class LightTestableFrame extends AbsTestableFrame {

    private final MemInputFiles inputFiles;

    public LightTestableFrame(AbstractLightProgramInfos _frames, AbstractIssuer _issuer, AbstractInterceptor _inter, AbstractAdvGraphicListGenerator _adv, AbstractAdvGraphicListGeneratorStruct _cr, MemInputFiles _typed, ProgTestBarInt _b) {
        super(_frames, _issuer, _inter, _adv, _cr,_b);
        inputFiles = _typed;
    }

    @Override
    public MemInputFiles getInputs() {
        return inputFiles;
    }

    @Override
    public boolean ok(String _file) {
        return true;
    }

    @Override
    public String getTxtConf() {
        return StringUtil.nullToEmpty(StringUtil.decode(inputFiles.getConf()));
    }

}
