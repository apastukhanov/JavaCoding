package com.company;



public class CmdArgs {

    public static class CmdParams {
        public String filename;
        public String outfile;

        CmdParams() {
            filename="";
            outfile="";
        }
        CmdParams(String i, String o) {
            filename=i;
            outfile=o;
        }
    }

    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();
        int counterArgs = 0;
        for (String arg : args) {
            if (arg.equals("-i") || arg.equals("--input")) {
                params.filename = args[counterArgs+1];
            } else if (arg.equals("-o") || arg.equals("--output")) {
                params.outfile = args[counterArgs+1];
            }
            counterArgs++;
        }
        if (params.filename == "") params.filename = args[0];
        if (params.outfile == "") params.outfile = args[1];

        return params;
    }
}
