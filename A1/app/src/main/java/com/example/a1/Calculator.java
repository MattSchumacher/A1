package com.example.a1;

import java.util.ArrayList;

public class Calculator {
    private final ArrayList<String> currentCalculation = new ArrayList<>();


    public void push(String value) {
        currentCalculation.add(value);
    }

    public double calculate() throws Exception {
        int first = getNumber(currentCalculation.remove(0));

        String operand = currentCalculation.remove(0);
        if (!isOperand(operand)) {
            throw new Exception(String.format("'%s' Is Not An Operator", operand));
        }

        int second = getNumber(currentCalculation.remove(0));

        double total = calculate(first, second, operand);

        while (!currentCalculation.isEmpty()) {
            String currentOperand = currentCalculation.remove(0);
            if (!isOperand(currentOperand)) {
                throw new Exception(String.format("'%s' Is Not An Operator", currentOperand));
            }

            int currentNumber = getNumber(currentCalculation.remove(0));

            total = calculate(total, currentNumber, currentOperand);
        }

        return total;
    }

    public void clear() {
        currentCalculation.clear();
    }

    private int getNumber(String number) throws Exception {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new Exception(String.format("'%s' Is Not A Number", number));
        }
    }

    private double calculate(double first, double second, String operand) {

        if (operand.equals("+")) {
            return first + second;
        } else if (operand.equals("-")) {
            return first - second;
        } else if (operand.equals("/")) {
            return first / second;
        } else if (operand.equals("max")) {
            return Math.max(first, second);
        } else if (operand.equals("min")) {
            return Math.min(first,second);
        } else if (operand.equals("pow")) {
            return Math.pow(first,second);
        } else if (operand.equals("%")) {
            return first % second;
        } else {
            return first * second;
        }
    }

    private boolean isOperand(String operand) {
        return operand.equals("+") || operand.equals("-") ||
                operand.equals("/") || operand.equals("*") ||
                operand.equals("min") || operand.equals("max") ||
                operand.equals("pow") || operand.equals("%");
    }
}
