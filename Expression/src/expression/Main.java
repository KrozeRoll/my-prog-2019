package expression;

public class Main {
    public static void main(String[] args) {
        Operation exp = new Add(new Subtract(new Multiply(new Variable("x"), new Variable("x")),
                new Multiply(new Const(2), new Variable("x"))), new Const(1));
        System.out.println(exp);
        System.out.println(exp.toMiniString());
        System.out.println(exp.evaluate(10));
        System.out.println(new Add(
                new Const(3),
                new Add(
                        new Const(2),
                        new Variable("x")
                )
        ).toMiniString());

        System.out.println(new Multiply(new Const(2), new Variable("x"))
                .equals(new Multiply(new Variable("x"), new Const(2))));


    }
}
