"use strict";

function println() {
    console.log(Array.prototype.map.call(arguments, String).join(' '));
}

const abstractOperation = operation => (...operands) => (x, y, z) => {
    let operandsOfOperation = [];
    for (let operand of operands) {
        operandsOfOperation.push(operand(x, y, z));
    }
    return operation(...operandsOfOperation);
};

const cnst = value => () => value;
const variable = input => (x, y, z) => input === "x" ? x : (input === "y" ? y : z);

const add = abstractOperation((a, b) => a + b);
const subtract = abstractOperation((a, b) => a - b);
const multiply = abstractOperation((a, b) => a * b);
const divide = abstractOperation((a, b) => a / b);
const negate = abstractOperation((a) => -a);
const sin = abstractOperation((a) => Math.sin(a));
const cos = abstractOperation((a) => Math.cos(a));
const cube = abstractOperation((a) => Math.pow(a, 3));
const cuberoot = abstractOperation((a) => Math.cbrt(a));

const pi = cnst(Math.PI);
const e = cnst(Math.E);

let operator = {
    "-" : [subtract, 2],
    "+" : [add, 2],
    "*" : [multiply, 2],
    "/" : [divide, 2],
    "negate" : [negate, 1],
    "sin" : [sin, 1],
    "cos" : [cos, 1],
    "cube" : [cube, 1],
    "cuberoot" : [cuberoot, 1]
};

let operand = {
    "x" : variable("x"),
    "y" : variable("y"),
    "z" : variable("z"),
    "pi" : pi,
    "e" : e
};

const parse = function(expr) {
    let stack = [];
    for (let i of expr.split(/\s/)) {
        if (i.length > 0) {
            if (i in operator) {
                let operands = [];
                let arity = operator[i][1];
                for (let j = 0; j < arity; j++) {
                    operands.push(stack.pop());
                }
                operands.reverse();
                stack.push(operator[i][0](...operands));
            } else if (i in operand) {
                stack.push(operand[i]);
            } else {
                stack.push(cnst(parseInt(i)));
            }
        }
    }
    return stack.pop();
};

//let expr = add(variable('x'), variable('y'));
//println(expr(228, 1, 0));

//let expr = "3 2 +"
//println(parse(expr)(2, 2, 3));