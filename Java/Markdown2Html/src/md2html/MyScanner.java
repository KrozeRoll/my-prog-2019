package md2html;

import java.io.*;

class MyScanner {
	private boolean ready = false;
	private Reader in;

	MyScanner(InputStream stream) {
		in = new BufferedReader(new InputStreamReader(stream));
		ready = true;
	}

	MyScanner(InputStream stream, String charset) throws UnsupportedEncodingException {
		in = new BufferedReader(new InputStreamReader(stream, charset));
		ready = true;
	}

	MyScanner(String input) {
		in = new BufferedReader(new StringReader(input));
		ready = true;
	}

	private int nextChar() throws IOException {
		int k = in.read();
		if (k == -1) {
			ready = false;
		}
		return k;
	}

	private boolean isEOL(int k) throws IOException {
		String lineSep = System.lineSeparator();
		if ((char)k == lineSep.charAt(0)) {
			if (lineSep.length() > 1) {
				k = nextChar();
				return (char) k == lineSep.charAt(1);
			} else {
				return true;
			}
		}
		return false;
	}

	String next() throws IOException{
		if (!ready) {
			return null;
		}

		StringBuilder curString = new StringBuilder();
		int k;
		boolean inWord = false;
		boolean eOL = false;

		while (true) {
			k = nextChar();
			if (k == -1) {
				ready = false;
				break;
			}

			if (isEOL(k)) {
				eOL = true;
				break;
			}

			if (Character.isWhitespace((char)k) && inWord) {
				break;
			} else if (!Character.isWhitespace((char)k)) {
				curString.append((char)k);
				inWord = true;
			}
		}
		return curString.toString();
	}

	String nextLine() throws IOException {
		if (!ready) {
			return null;
		}

		StringBuilder curString = new StringBuilder();
		int k = 0;
		while (true) {
			k = in.read();
			if (k == -1) {
				ready = false;
				break;
			}
			if (isEOL(k)) {
				break;
			} else {
				curString.append((char)k);
			}
		}

		//System.out.println("_" + curString + "_");
		return curString.toString();
	}

	String nextParagraph() throws IOException {
		if (!ready) {
			return null;
		}

		StringBuilder curParagraph = new StringBuilder(nextLine());
		String line;
		if (curParagraph.length() > 0) {
			while (ready && (line = nextLine()).length() > 0) {
				curParagraph.append(System.lineSeparator());
				curParagraph.append(line);
			}
		}

		if (curParagraph.length() == 0) {
			return nextParagraph();
		}

		//System.out.println("_" + curParagraph + "_");
		return curParagraph.toString();
	}

	Integer nextInt() throws IOException {
		while (ready) {
			try {
				return Integer.parseInt(next());
			} catch (NumberFormatException e) {}
		}
		return null;
	}

	void close() throws IOException {
		in.close();
	}
}

