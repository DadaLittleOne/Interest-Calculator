package com.dada;

import java.io.PrintStream;
import java.util.Scanner;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {

	public static void main(String[] args) {
		var sc = new Scanner(System.in);
		var out = new PrintStream(System.out, true, UTF_8);
		var calculator = new Calculator(sc, out);

		out.println("*****************************\nCompound Interest Calculator:\n*****************************\n");

		calculator.getInput();
		calculator.displayResult();

	}

}