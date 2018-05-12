package com.lombok.learn.annotation.cleanup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;
import java.util.Scanner;

import lombok.Cleanup;

public class CleanupAnnotation {

	public static void main(String[] args) throws IOException {
		Scanner scan= new Scanner(System.in);
		String nextLine = scan.nextLine();
		InputStream in = new StringBufferInputStream(nextLine);
	    @Cleanup OutputStream out = new FileOutputStream("/tmp/xx.txt");
	    byte[] b = new byte[10000];
	    while (true) {
	      int r = in.read(b);
	      if (r == -1) break;
	      out.write(b, 0, r);
	    }
	}

}
