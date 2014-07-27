package com.bronstrup.cryptotoy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Created by Keith Bronstrup on 7/24/14.
 */

/**
 *
 */

public class cryptotoy {
	public    int statusCode; // Our "return" code
	public String statusText; // Our "return" reason

	private            int contentType;   // 0 = file (by name), 1 = string, >1 = stdin (as contentLength)
	private            int keytextLength; // Length of keytext, in bits
	private            int contentLength; // Length of content, in bits
	private            int contentBlocks; // ceil(contentLength / 4096)
	private            int contentPos;    // Position within content, in blocks
	private            int keySegments;   // (contentLength % keytextLength) + 1
	private        boolean decrypt;       // false = encrypt, true = decrypt
	private     ByteBuffer keyText;       // ==
	private     ByteBuffer rootKey;       // Concatenation of MD5 sums of keySegments segments of keytext
	private     ByteBuffer cipherKey;     // 4096-bit concatenation of MD5 sums of 16 segments of rootkey
	private     ByteBuffer rollingKey;    // Current 4096-bit XOR key
	private     ByteBuffer nextKey;       // Next 4096-bit XOR key, derived from current value of decoded
	private     ByteBuffer decoded;       // Next 4096-bit block of content to encode (if encrypting) or last-decoded block (if decrypting)
	private     ByteBuffer encoded;       // Next 4096-bit block of content to decode (if decrypting) or last-encoded block (if encrypting)
	private     ByteBuffer contentBuffer; // Content string from commandline (contentType 1);
	private    InputStream contentStream; // getResourceAsStream(contentBuffer) (contentType 0);
	private BufferedReader contentStdin;  // Buffer for stdin (contentType >1);

	public static class Builder {
		private         int contentType;   // 0 = file (by name), 1 = string, >1 = stdin (as contentLength)
		private  ByteBuffer contentBuffer; // Content string from commandline (contentType 1);
		private InputStream contentStream; // getResourceAsStream(contentBuffer) (contentType 0);
		private     boolean decrypt;       // false = encrypt, true = decrypt
		private  ByteBuffer keyText;       // ==

		public Builder(int contentType) {
			this.contentType = contentType;
		}

		public Builder contentBuffer(ByteBuffer contentBuffer) {
			//   File: getResourceAsStream(content);
			// String: http://www.snippetit.com/2010/01/java-use-bytebuffer-as-inputstream/
			//  stdin: leave buffer empty, read from stdin until out of contentType(Length) bits are read
			return this;
		}

		public Builder keytext(ByteBuffer keytext) {
			this.keyText = keytext;
			return this;
		}

		public Builder decrypt(boolean decrypt) {
			this.decrypt = decrypt;
			return this;
		}

		public cryptotoy build() {
			return new cryptotoy(this);
		}
	}

	public cryptotoy(Builder b) {
		this.statusCode = 0;
		this.statusText = "OK";

		this.contentType    = b.contentType;
		this.contentBuffer  = b.contentBuffer;
		this.contentStream  = b.contentStream;
		this.keyText        = b.keyText;
		this.decrypt        = b.decrypt;

		if (this.decrypt) {
			this.contentPos = 0;
		} else {
			this.contentPos = -1;
		}

		if (this.makeRootKey()
		 && this.makeCipherKey()
		 && this.makeRollingKey()) {
			// getNextContent, makeNextKey, (encode/decode), output, makeRollingKey
			getNextContent(); // placeholder!
		}
	}

	private byte[] makeHeader() {
		byte[] header = new byte[512];
		new Random().nextBytes(header);
		return header;
	}

	private boolean makeRootKey() {
		// Always from keytext

		return true;
	}

	private boolean makeCipherKey() {
		// Always from rootkey

		return true;
	}

	private boolean makeRollingKey() {
		// If rollingKey is empty, grab cipherKey; else, grab nextKey

		return true;
	}

	private boolean makeNextkey() {
		// Always from decoded

		return true;
	}

	private boolean getNextContent() {
		// From content, to encoded if decrypt, else to decoded; increment contentPos
		if (this.decrypt) {
		} else {
			if (this.contentPos == -1) {
				this.decoded = ByteBuffer.wrap(this.makeHeader());
			} else {
				// set this.decoded based on this.contentType
				switch (this.contentType) {
					case 0:
						// File (by name) this.contentStream
						break;

					case 1:
						// String this.contentBuffer
						break;

					default:
						// stdin: Buffer as in http://stackoverflow.com/questions/7049011/whats-the-fastest-way-to-read-from-system-in-in-java (but in 512 byte chunks, rather than lines)
						break;
				}
			}
		}

		return true;
	}

	private boolean encode() {
		// Always from decoded, to encoded, then clear decoded

		return true;
	}

	private boolean decode() {
		// Always from encoded, to decoded, then clear encoded

		return true;
	}

	private boolean output() {
		// From decoded if decrypt, else from encoded, then clear

		return true;
	}
}