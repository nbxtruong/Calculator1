package calculator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class Calculator {
	private final Map<String, Operator> operators = new HashMap<String, Operator>();

	public Calculator(Operator[] ops) {
		for (Operator op : ops) {
			add(op);
		}
	}

	private double expression(StreamTokenizer st) throws IOException,
			SyntaxErrorException {
		switch (st.ttype) {
		case StreamTokenizer.TT_NUMBER:
			return st.nval;
		case StreamTokenizer.TT_WORD:
			Operator op = (Operator) operators.get(st.sval);
			if (op == null) {
				throw new SyntaxErrorException(st, "Unknown operator");
			}
			double[] operands = new double[op.arity()];
			for (int i = 0; i < op.arity(); ++i) {
				st.nextToken();
				operands[i] = expression(st);
			}
			return op.evaluate(operands);
		default:
			throw new SyntaxErrorException(st);
		}
	}

	public void evaluate(Reader input, PrintWriter output, boolean endOfLine)
			throws SyntaxErrorException, IOException {
		StreamTokenizer st = new StreamTokenizer(input);
		st.wordChars('!', '~');
		st.eolIsSignificant(endOfLine);
		st.nextToken();
		while (st.ttype != StreamTokenizer.TT_EOF) {
			double value = expression(st);
			st.nextToken();
			if (st.ttype != (endOfLine ? StreamTokenizer.TT_EOL
					: StreamTokenizer.TT_EOF)) {
				throw new SyntaxErrorException(st);
			}
			output.println(value);
			if (endOfLine) {
				st.nextToken();
			}
		}
	}

	private static void usage() {
		System.err
				.println("java Calculator <Input file> <Output file>");
		System.exit(1);
	}

	public void add(Operator op) {
		operators.put(op.symbol(), op);
	}

	public static void main(String[] args) throws FileNotFoundException,
			IOException, SyntaxErrorException {

		if (args.length > 2) {
			usage();
		}

		Calculator calculator = new Calculator(Operators.FOUR_OPERATIONS);

		PrintWriter ouput = (args.length == 2 ? new PrintWriter(
				new FileWriter(args[1])) : new PrintWriter(System.out, true)); // autoflush
		BufferedReader input = null;
		if (args.length >= 1) {
			input = new BufferedReader(new FileReader(args[0]));
			calculator.evaluate(input, ouput, true);
		} else {
			input = new BufferedReader(new InputStreamReader(System.in));
			for (String s = input.readLine(); s != null; s = input.readLine()) {
				calculator.evaluate(new StringReader(s), ouput, false);
			}
		}
		input.close();
		ouput.close();
	}
}