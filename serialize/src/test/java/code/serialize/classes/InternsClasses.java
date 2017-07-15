package code.serialize.classes;
import code.util.annot.RwXml;

public class InternsClasses {

    public class InternStandard {

    }

    @RwXml
    public class InternStandardTwo {
        public class InternStandardOne {

        }
        public class InternStandardThree {
        }
    }

    @RwXml
    public static class InternStaticStandard {
        public static class InternStaticStandardThree {

        }
        @RwXml
        public class InternStaticStandardFour {

        }
    }

    public static class InternStaticStandardTwo {
        public class InternStandardOne {

        }
        public class InternStandardTwo {

        }
    }
}
