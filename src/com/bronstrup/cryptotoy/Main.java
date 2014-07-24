package com.bronstrup.cryptotoy;

/**
 * cryptotoy - A rolling-key XOR implementation utilizing derived-keys and an
 *             arbitrary-length keytext as a passphrase.
 *
 * This utility was written as a thought exercise and is not intended to be in
 * any way secure. Do not rely on this utility for data security unless you are
 * a cryptography expert and have audited both my algorithm and implementation
 * and have determine, yourself, that both are sound and secure. You must, then,
 * take it upon yourself to hold yourself responsible and liable for any loss or
 * damages, should any such positive assessment of this utility turn out to have
 * been made in error.
 *
 * In layman's terms:
 * This software comes with no warranty and any and all use is at your own risk.
 * I am telling you this is probably not secure; should you choose to use it in
 * the capacity of a security tool, I advise you to do so only after performing
 * your own audit of the tool's inner workings, and then, only if you find the
 * tool to be secure. You are liable for your use of this tool. I'm not sure how
 * much clearer I can make this, other than to say I am not a cryptography
 * expert, and your data is probably not safe here.
 *
 * Have fun and enjoy! :)
 */

/**
 * NOTES AND OBSERVATIONS:
 *
 * Possible attack vector: knowledge of first 4096 bits of data
 * Fix: Pad content with a 4096 bit header of random data
 * Caveat: No longer encrypts without affecting file size
 */

public class Main {

	public static void main(String[] args) {
		/**
		 * There used to be implementation details here...
		 *
		 * They've been moved to /offline/ until they can be refined and added to the readme.
		 */
	}
}
