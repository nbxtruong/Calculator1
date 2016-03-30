package calculator;
import java.io.StreamTokenizer;

public class SyntaxErrorException extends Exception {

	private StreamTokenizer st = null;

	private String msg;

	public SyntaxErrorException(StreamTokenizer st) {
		this(st, "");
	}

	public SyntaxErrorException(StreamTokenizer st, String msg) {
		this.st = st;
		this.msg = msg;
	}

	public StreamTokenizer tokenizer() {
		return st;
	}

	public String getMessage() {
		return "Syntax error " + st + " " + msg;
	}
}