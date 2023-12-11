package net.etfbl.terminali;
import java.io.*;

public class AppendableObjectOutputStream extends ObjectOutputStream {
	public AppendableObjectOutputStream(OutputStream os) throws IOException {
		super(os);
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		reset();
	}
}
