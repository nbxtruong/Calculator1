package calculator;

public class Operators {

	public static final Operator PLUS = new Operator() {
		public String symbol() {
			return "+";
		}

		public int arity() {
			return 2;
		}

		public double evaluate(double[] operands) {
			return operands[0] + operands[1];
		}
	};

	public static final Operator MINUS = new Operator() {
		public String symbol() {
			return "minus";
		}

		public int arity() {
			return 2;
		}

		public double evaluate(double[] operands) {
			return operands[0] - operands[1];
		}
	};

	public static final Operator TIMES = new Operator() {
		public String symbol() {
			return "*";
		}

		public int arity() {
			return 2;
		}

		public double evaluate(double[] operands) {
			return operands[0] * operands[1];
		}
	};

	public static final Operator DIV = new Operator() {
		public String symbol() {
			return "/";
		}

		public int arity() {
			return 2;
		}

		public double evaluate(double[] operands) {
			return operands[0] / operands[1];
		}
	};

	public static final Operator COS = new Operator() {

		public String symbol() {
			return "cos";
		}

		public int arity() {
			return 1;
		}

		public double evaluate(double[] operands) {
			return Math.cos(operands[0]);
		}
	};

	public static final Operator[] FOUR_OPERATIONS = { PLUS, MINUS, TIMES,
			DIV };
}