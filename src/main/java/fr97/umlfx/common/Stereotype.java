package fr97.umlfx.common;

public enum Stereotype {
    NO_STEREOTYPE {
        @Override
        public String getText() {
            return "";
        }
    },
    INTERFACE {
        @Override
        public String getText() {
            return "<<interface>>";
        }
    },
    ENUM {
        @Override
        public String getText() {
            return "<<enumeration>>";
        }
    },
    PACKAGE {
        @Override
        public String getText() {
            return "<<package>>";
        }
    },
    MODULE {
        @Override
        public String getText() {
            return "<<module>>";
        }
    },
    ;

    public abstract String getText();
}
