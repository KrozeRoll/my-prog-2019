package expression;

public class Add extends Operation {
    private static final int priority = 1;
    private static final char sign = '+';
    private static final boolean isComutative = true;

    public Add(Operand left, Operand right) {
        super(left, right, priority, isComutative);
    }

    @Override
    public char getSign() {
        return sign;
    }

    @Override
    public int getPriority() {
        return priority;
    }


}
