package expression;

public enum Types {
    ADD(1, '+', true),
    SUBTRACT(1, '-', false),
    MULTIPLY(2, '*', true),
    DIVIDE(2, '/', false),
    VARIABLE(10);

    private int priority;
    private char sign;
    private boolean isCommutative;

    Types(int priority, char sign, boolean isCommutative) {
        this.priority = priority;
        this.sign = sign;
        this.isCommutative = isCommutative;
    }

    Types(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public char getSign() {
        return sign;
    }

    public boolean isCommutative() {
        return isCommutative;
    }
}
