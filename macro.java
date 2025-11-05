//Design suitable data structures and implement pass-I of a two-pass macro-processor using OOP features in Java
package pass;
import java.util.*;
import java.io.*;

public class macro {
	    public static void main(String[] args) {
	        try {
	            BufferedReader br = new BufferedReader(new FileReader("Input.txt"));
	            PrintWriter mntWriter = new PrintWriter(new File("mnt.txt"));
	            PrintWriter mdtWriter = new PrintWriter(new File("mdt.txt"));
	            PrintWriter adtWriter = new PrintWriter(new File("adt.txt"));

	            String input;
	            int mcount = 0, argCount = 0, index = 1;
	            boolean inMacro = false;
	            String[] mnt = new String[10], AR = new String[20];
	            int[] macroIndex = new int[10];

	            while ((input = br.readLine()) != null) {
	                String[] tokens = input.split(" ");
	                String token = tokens[0];

	                if (token.equals("MACRO")) {
	                    inMacro = true;
	                    mnt[mcount] = tokens[1];
	                    macroIndex[mcount] = index;
	                    mntWriter.println(mnt[mcount] + "\t" + macroIndex[mcount]);
	                    mdtWriter.println(mnt[mcount]);
	                    adtWriter.println(mnt[mcount]);

	                    String[] argsTokens = tokens[2].split(",");
	                    argCount = 0;
	                    for (String arg : argsTokens) {
	                        if (arg.startsWith("&")) {
	                            AR[argCount] = arg;
	                            adtWriter.println(arg);
	                            argCount++;
	                        }
	                    }
	                    mcount++;
	                } else if (inMacro) {
	                    if (token.equals("MEND")) {
	                        inMacro = false;
	                        mdtWriter.println("MEND");
	                    } else {
	                        for (String t : tokens) {
	                            boolean isArg = false;
	                            for (int i = 0; i < argCount; i++) {
	                                if (t.equals(AR[i])) {
	                                    mdtWriter.print("AR" + i + " ");
	                                    isArg = true;
	                                    break;
	                                }
	                            }
	                            if (!isArg) mdtWriter.print(t + " ");
	                        }
	                        mdtWriter.println();
	                    }
	                }
	                index++;
	            }

	            br.close();
	            mntWriter.close();
	            mdtWriter.close();
	            adtWriter.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


	/*
	input.txt
	MACRO MACRO1 &ARG1,&ARG2
	MOV &ARG1, &ARG2
	ADD &ARG1, &ARG3
	MEND
	MACRO MACRO2 &ARG1,&ARG2
	SUB &ARG1, &ARG2
	MEND
	*/

