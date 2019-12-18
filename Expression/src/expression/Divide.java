package expression;

public class Divide extends Operation {
    private static final int priority = 2;
    private static final char sign = '/';
    private static final boolean isComutative = false;

    public Divide(Operand left, Operand right) {
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