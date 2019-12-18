package expression;

import java.util.Objects;

public class Variable implements Operand {
    private String variableName;
    private static final int priority = 10;

    public Variable(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public String toString() {
        return variableName;
    }

    @Override
    public String toMiniString() {
        return variableName;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public int evaluate(int x) {
        if ("x".equals(variableName)) {
            return x;
        }
        throw new IllegalArgumentException("Variable 'x' expected");
    }

    @Override
    public int evaluate(int x, int y) {
        switch (variableName) {
            case "x":
                return x;
            case "y":
                return y;
            default:
                throw new IllegalArgumentException("Variable 'x' or 'y' expected");
        }
    }

    @Override
    public int evaluate(int x, int y, int z) {
        switch (variableName) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
            default:
                throw new IllegalArgumentException("Variable 'x', 'y' or 'z' expected");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Variable variable = (Variable) obj;
        return Objects.equals(variableName, variable.variableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variableName);
    }
}
