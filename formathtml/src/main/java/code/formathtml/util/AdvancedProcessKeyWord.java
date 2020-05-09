package code.formathtml.util;

import code.expressionlanguage.instr.AbstractProcessKeyWord;
import code.expressionlanguage.instr.ResultAfterInstKeyWord;
import code.formathtml.Configuration;

public class AdvancedProcessKeyWord implements AbstractProcessKeyWord {
    private final Configuration configuration;

    public AdvancedProcessKeyWord(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void processInternKeyWord(String _exp, int _fr, ResultAfterInstKeyWord _out) {
        configuration.processInternKeyWord(_exp,_fr,_out);
    }
}
