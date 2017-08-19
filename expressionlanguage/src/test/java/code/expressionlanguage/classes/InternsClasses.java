package code.expressionlanguage.classes;
import code.expressionlanguage.AccEl;

public class InternsClasses {

    @AccEl
    private String news = "";

    public class InternStandard {

    }

    public class InternStandardTwo {
        private int privateInfo;
        public int getPrivateInfoBis() {
            return privateInfo;
        }
        public int getPrivateInfo() {
            return privateInfo;
        }
        public class InternStandardOne {

        }
        public class InternStandardThree {
            private int privateInfo;
            public InternStandardThree(int _privateInfo) {
                privateInfo = _privateInfo;
            }
            public int getPrivateInfo() {
                return privateInfo;
            }
            public class InternStandardFour {
//                private int this$2;
            }
        }
    }

    public static class InternStaticStandard {
        public static class InternStaticStandardThree {

        }
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
