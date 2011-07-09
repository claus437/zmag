package dk.clausreimer.zmag.wordrank;

import java.io.IOException;
import java.io.InputStream;

class InputStreamMock extends InputStream {
    private InputStream stream;
    private boolean closed;
    private boolean raiseReadException;
    private boolean raiseCloseException;

    public InputStreamMock(InputStream stream) {
        this.stream = stream;
    }

    @Override
    public int read() throws IOException {
        if (raiseReadException) {
            throw new IOException("read error");
        }
        return stream.read();
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (raiseReadException) {
            throw new IOException("read error");
        }

        return stream.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return stream.skip(n);
    }

    @Override
    public int available() throws IOException {
        return stream.available();
    }

    @Override
    public boolean markSupported() {
        return stream.markSupported();
    }

    @Override
    public void mark(int readAheadLimit) {
        stream.mark(readAheadLimit);
    }

    @Override
    public void reset() throws IOException {
        stream.reset();
    }

    @Override
    public void close() throws IOException {
        if (raiseCloseException) {
            throw new IOException("close error");
        }

        closed = true;
        stream.close();
    }

    public boolean isClosed() {
        return closed;
    }

    public void setRaiseReadException(boolean raiseReadException ) {
        this.raiseReadException = raiseReadException ;
    }

    public void setRaiseCloseException(boolean raiseCloseException) {
        this.raiseCloseException = raiseCloseException;
    }
}
