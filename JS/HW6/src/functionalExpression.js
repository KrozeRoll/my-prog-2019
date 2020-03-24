"use strict";

function println() {
    console.log(Array.prototype.map.call(arguments, String).join(' '));
}

const binaryOperation = operation => (op1, op2) => (x, y, z) => operation(op1(x, y, z), op2(x, y, z));
const unaryOperation = operation => (op) => (x, y, z) => operation(op(x, y, z));

const cnst = value => (x, y, z) => value;
const variable = input => (x, y, z) => input === "x" ? x : (input === "y" ? y : z);

const add = binaryOperation((a, b) => a + b);
const subtract = binaryOperation((a, b) => a - b);
const multiply = binaryOperation((a, b) => a * b);
const divide = binaryOperation((a, b) => a / b);
const negate = unaryOperation((a) => -a);
const sin = unaryOperation((a) => Math.sin(a));
const cos = unaryOperation((a) => Math.cos(a));
const cube = unaryOperation((a) => Math.pow(a, 3));
const cuberoot = unaryOperation((a) => Math.cbrt(a));

const pi = cnst(Math.PI);
const e = cnst(Math.E);

let binaryOperator = {
    "-" : subtract,
    "+" : add,
    "*" : multiply,
    "/" : divide
};

let unaryOperator = {
    "negate" : negate,
    "sin" : sin,
    "cos" : cos,
    "cube" : cube,
    "cuberoot" : cuberoot
};

let operand = {
    "x" : variable("x"),
    "y" : variable("y"),
    "z" : variable("z"),
    "pi" : pi,
    "e" : e
}

const parse = function(expr) {
    let stack = [];
    for (let i of expr.split(/\s/)) {
        if (i.length > 0) {
            if (i in binaryOperator) {
                let secondOperand = stack.pop();
                stack.push(binaryOperator[i](stack.pop(), secondOperand));
            } else if (i in unaryOperator) {
                stack.push(unaryOperator[i](stack.pop()));
            } else if (i in operand) {
                stack.push(operand[i]);
            } else {
                stack.push(cnst(parseInt(i)));
            }
        }
    }
    return stack.pop();
};

//let expr = cuberoot(add(variable('x'), variable('y')));
//println(expr(228, 1, 0));