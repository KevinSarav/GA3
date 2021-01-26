import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
public class Main {

    public static void main(String[] args) {
        int dotCount = 0;
        String[] parts = null;
        String[] secondParts = null;
        String word = null;
        List<String> list = new ArrayList<String>();
        int emailCount =0;
        PrintWriter outputStream;

        Scanner scanner;
        Scanner userInput= new Scanner(System.in);

        try {
            outputStream = new PrintWriter("out.txt");
            scanner = new Scanner(new FileInputStream("input.txt"));

            while (scanner.hasNext()) {
                word = scanner.next();
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == '@') {
                        emailCount++;
                        list.add(word);
                    }
                }
            }

            Email[] emailArray = new Email[emailCount];
            String[] stringArr = list.toArray(new String[0]);
            String[] username = new String[emailCount];
            String[] domain = new String[emailCount];
            String[] subdomainArray = new String[emailCount];
            String[] realdomainArray = new String[emailCount];
            String[] extensionArray = new String[emailCount];

            for (int i = 0; i < stringArr.length; i++) {
                parts = stringArr[i].split("@|,");
                username[i] = parts[0];
                domain[i] = parts[1];
            }
            for (int d = 0; d < domain.length; d++) {
                dotCount = 0;
                for (char c : domain[d].toCharArray()) {
                    if (c == '.') {
                        dotCount++;
                        secondParts = domain[d].split(Pattern.quote("."));

                        if (dotCount == 2) {
                            subdomainArray[d] = (secondParts[0]);
                            realdomainArray[d] = (secondParts[1]);
                            extensionArray[d] = (secondParts[2]);

                            emailArray[d] = new UniversityEmail(username[d], realdomainArray[d], extensionArray[d], subdomainArray[d]);

                            dotCount = 0;

                        } else {
                            realdomainArray[d] = secondParts[0];
                            extensionArray[d] = secondParts[1];
                            emailArray[d] = new Email(username[d], realdomainArray[d], extensionArray[d]);

                        }
                    }
                }

            }
            System.out.println("Please enter a number from 0-8");
            int choice = userInput.nextInt();

            if(choice == 8) {
                for (int i = 0; i < emailCount; i++) {
                    if (emailArray[i].getType().equals("UniversityEmail"))
                        outputStream.println(emailArray[i]);
                }
            }
            for (int i = 0; i < emailCount; i++) {
                if (emailArray[i].getType().equals("Email")) {
                    if(emailArray[i].returnCode()==choice)
                        outputStream.println(emailArray[i]);

                }

                if(emailArray[i].getType().equals("UniversityEmail"))
                {

                    if(emailArray[i].returnCode()==choice)
                    {
                        outputStream.println(emailArray[i]);
                    }

                }
            }
            scanner.close();
            userInput.close();
            outputStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File cannot be found");
            System.exit(0);
        }
    }
}