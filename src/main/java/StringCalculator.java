/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 5/31/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringCalculator {

    public int result = 0;

    public int Add(String input) {
        if (input.isEmpty()) {
            return result;
        }
        else {
            String regex = "[,\n;/*]";
            String define = "(//)(\\[)(.*)(\\])(\\n)(.*)";
            if (input.matches(define)) {
                //System.out.println("match");
                regex = getDefineDelimiter(input);
                String tempString = regex;
                String[] getString = input.split("\\n");
                input = getString[1];
                String temp = "";
                for (int j = 0 ; j< tempString.length(); j++) {
                    char t = tempString.charAt(j);
                    if ((t == '*') || (t == '?') || (t == '+') || (t =='[') || (t==']') || ( t== '(') || ( t==')')) {
                        temp += "\\" + t;
                    }
                    else {
                        temp += t;
                    }
                }
                regex = temp;

                System.out.println(regex);
            }
            String negative = "-[\\d]*";
            String [] numberString = input.split(regex);
            for (String ni : numberString) {
                if (ni.matches(negative)) {
                    throw new NumberFormatException("negatives not allowed");
                }
                else if ((!ni.isEmpty()) && (!ni.equals("[")) && (!ni.equals("]"))) {
                    int number = Integer.parseInt(ni);
                    if (number <= 1000) {
                        result += number;
                    }
                }
            }
            return result;
        }
    }

    public String getDefineDelimiter(String input) {
        String result = "";
        String getPattern = "(//)(.*)(\\n)(.*)";
        if (input.matches(getPattern)) {
            //System.out.println("MATCH");
            String[] preDefine = input.split("\\n");
            String defineDelimiter = preDefine[0].substring(3,preDefine[0].length()-1);
            System.out.println(defineDelimiter);
            result = defineDelimiter;
        }
        return result;
    }
}
