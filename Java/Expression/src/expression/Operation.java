package expression;

import java.util.Objects;

public abstract class Operation implements Operand {
    private final Operand left, right;
    private int priority;
    private boolean isComutative;

    Operation(Operand left, Operand right, int priority, boolean isComutative) {
        this.left = left;
        this.right = right;
        this.priority = priority;
        this.isComutative = isComutative;
    }

    abstract char getSign();

    @Override
    public String toString() {
        StringBuilder statement = new StringBuilder();
        statement.append("(").append(left.toString()).append(" ").append(this.getSign())
                    .append(" ").append(right.toString()).append(")");
        return statement.toString();
    }

    @Override
    public String toMiniString() {
        StringBuilder statement = new StringBuilder();

        int thisPriority = this.getPriority();
        int leftPriority = left.getPriority();
        int rightPriority = right.getPriority();

        if (thisPriority <= leftPriority) {
            statement.append(left.toMiniString());
        } else {
            statement.append("(").append(left.toMiniString()).append(")");
        }
        statement.append(" ").append(getSign()).append(" ");
        if (thisPriority < rightPriority || (this.getType() == right.getType()) && (isComutative)) {
            statement.append(right.toMiniString());
        } else {
            statement.append("(").append(right.toMiniString()).append(")");
        }

        return statement.toString();
    }

    @Override
    public int getPriority() {
        return this.priority;
    }

    @Override
    public int evaluate(int x) {
        int leftOperandAns = left.evaluate(x);
        int rightOperandAns = right.evaluate(x);
        return evaluateLastOperation(leftOperandAns, rightOperandAns);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        int leftOperandAns = left.evaluate(x, y, z);
        int rightOperandAns = right.evaluate(x, y, z);
        return evaluateLastOperation(leftOperandAns, rightOperandAns);
    }

    private int evaluateLastOperation(int leftValue, int rightValue) {
        switch(this.getSign()) {
            case ('+'):
                return leftValue + rightValue;
            case('-'):
                return leftValue - rightValue;
            case('*'):
                return leftValue * rightValue;
            case('/'):
                return leftValue / rightValue;
            default:
                return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj)  {
            return true;
        }
        Operation operation = (Operation) obj;
        return priority == operation.priority &&
                isComutative == operation.isComutative &&
                Objects.equals(left, operation.left) &&
                Objects.equals(right, operation.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, priority, isComutative);
    }
}
