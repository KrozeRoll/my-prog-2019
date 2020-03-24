package expression;

public class Add extends Operation {
    private static final Types type = Types.ADD;

    public Add(Operand left, Operand right) {
        super(left, right, type.getPriority(), type.isCommutative());
    }

    @Override
    public char getSign() {
        return type.getSign();
    }

    @Override
    public int getPriority() {
        return type.getPriority();
    }

    @Override
    public Types getType() {
        return type;
    }
}
