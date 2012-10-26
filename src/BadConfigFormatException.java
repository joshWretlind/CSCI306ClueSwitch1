
public class BadConfigFormatException extends Exception {
	@Override
	public String toString() {
		return "Invalid file or rows/cols out of bounds";
	}
}