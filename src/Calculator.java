package com.dada;

import java.io.PrintStream;
import java.util.Scanner;

public class Calculator {

	// Reference Variables

	private Scanner sc;
	private PrintStream out;

	// Primitive Variables

	private double principal;
	private double interestRate;
	private double years;

	private char[] superDigits = { 
			'\u2070', // Superscript 0
			'\u00B9', // Superscript 1
			'\u00B2', // Superscript 2
			'\u00B3', // Superscript 3
			'\u2074', // Superscript 4
			'\u2075', // Superscript 5
			'\u2076', // Superscript 6
			'\u2077', // Superscript 7
			'\u2078', // Superscript 8
			'\u2079' // Superscript 9
	};

	// Public Methods

	public Calculator(Scanner sc, PrintStream out) { // Constructor
		this.sc = sc;
		this.out = out;
	}

	public void getInput() { // Asks for input
		principal = readNumber("Please enter principal amount: ", 0.001f, 1000000f);

		interestRate = readNumber("\nPlease enter interest rate per annum: ", 0.001f, 100f);
		years = readNumber("\nPlease enter amount of years: ", 0.001f, 1000000f);
	}

	public void displayResult() { // Displays result
		out.println("\nAmount earned at " + interestRate + "% p.a. for " + years + " years from a starting amount of £"
				+ principal + ":\n");

		out.println("£" + superScript("" + compoundInterest(principal, interestRate, years)) + "\n");
	}

	// Private Methods

	private double readNumber(String prompt, float min, float max) { // Provides a prompt and reads a clamped number
		double input;
		out.print(prompt);

		while (true) {
			input = safeRead();
			if (input >= min && input <= max) break;
			
			out.print("\nPlease enter a value between " + min + " & " + max + ": ");
		}
		return input;
	}

	private double safeRead() { // Reads a double with InputMismatchException handling.
		while (true) {
			if (sc.hasNextDouble()) {
				return sc.nextDouble();
			} else {
				out.print("\nPlease enter valid input: ");
				sc.next();
			}
		}
	}

	private String superScript(String normal) { // Converts a number with an exponent to standard form
		String exact = normal; // Exact stores the exact value

		if (normal.contains("E")) {
			String[] strings = normal.split("E");
			String[] stringsExact = exact.split("E");

			strings[0] = String.format("%.2f", Double.parseDouble(strings[0]));
			for (int i = 0; i <= 9; ++i) {
				strings[1] = strings[1].replaceAll("" + i, "" + superDigits[i]);
				stringsExact[1] = stringsExact[1].replaceAll("" + i, "" + superDigits[i]);
			}
			normal = strings[0].replace('E', ' ') + ("\u00D7" + "10" + strings[1]);
			exact = stringsExact[0].replace('E', ' ') + ("\u00D7" + "10" + stringsExact[1]);

			return normal.trim() + "\n\nExact value is £" + exact;
		}

		normal = String.format("%.2f", Double.parseDouble(normal));
		return normal + "\n\nExact value is £" + exact;
	}

	private double compoundInterest(double principal, double rate, double years) { // Calculates Compound Interest
		return (double) (principal * Math.pow((1 + (rate / 100)), years)) - principal;
	}

}