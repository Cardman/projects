package code.expressionlanguage.exec.stacks;
import code.expressionlanguage.exec.blocks.ExecBracedBlock;
import code.expressionlanguage.exec.blocks.ExecListLastBkSw;
import code.expressionlanguage.structs.Struct;

public final class SwitchBlockStack extends AbstractStask implements ConditionBlockStack {

    private final ExecBracedBlock execBlock;
    private final ExecListLastBkSw infos;

    private ExecBracedBlock execCurrentVisitedBlock;

    private Struct value;
    private String instanceTest;
    private final boolean atMostOne;
    private boolean entered;

    public SwitchBlockStack(ExecBracedBlock _execBlock, boolean _atMost) {
        infos = new ExecListLastBkSw(_execBlock);
        execBlock = _execBlock;
        atMostOne = _atMost;

    }
    public ExecBracedBlock getBlock() {
        return execBlock;
    }

    public ExecListLastBkSw getInfos() {
        return infos;
    }

    public ExecBracedBlock next(ExecBracedBlock _current) {
        return getInfos().next(_current);
    }
    public boolean isAtMostOne() {
        return atMostOne;
    }

    @Override
    public void setCurrentVisitedBlock(ExecBracedBlock _execCurrentVisitedBlock) {
        this.execCurrentVisitedBlock = _execCurrentVisitedBlock;
    }

    @Override
    public ExecBracedBlock getCurrentVisitedBlock() {
        return execCurrentVisitedBlock;
    }

    public boolean isEntered() {
        return entered;
    }

    public void enter() {
        entered = true;
    }

    public Struct getValue() {
        return value;
    }

    public void setValue(Struct _v) {
        this.value = _v;
    }

    public String getInstanceTest() {
        return instanceTest;
    }

    public void setInstanceTest(String _i) {
        this.instanceTest = _i;
    }
}
