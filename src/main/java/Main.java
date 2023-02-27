import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Integer> splitList = getInputNumbers(args)
                .stream()
                .map(prvek -> Integer.parseInt(prvek))
                .toList();

        boolean jeSudy = splitList.size() % 2 == 0;
        List<Integer> result = getResultList(splitList, jeSudy);

        String stringResult = result.stream().map(prvek -> String.valueOf(prvek)).collect(Collectors.joining(" "));
        writeResult(stringResult, args);
    }

    private static List<String> getInputNumbers(String[] args) throws IOException {
        String prvniArgument = args[0];
        if (isNumeric(prvniArgument)) {

            return Arrays.asList(args);
        }
        ClassLoader classLoader = Main.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(prvniArgument);
        String data = readFromInputStream(inputStream);

        return Arrays.asList(data.split("\\s"));
    }

    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    public static List<Integer> getResultList(List<Integer> splitList, boolean vypisSudy) {
        List<Integer> newList = new ArrayList<>();
        for (Integer number : splitList) {
            if (number % 2 == 0 && vypisSudy) {
                newList.add(number);
            }

            if (number % 2 != 0 && !vypisSudy) {
                newList.add(number);
            }
        }
        return newList;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void writeResult(String data, String[] args) throws FileNotFoundException {
        String druhyArgument = args.length > 1 ? args[1] : null;
        if (druhyArgument ==null || isNumeric(druhyArgument)) {
            System.out.println(data);
        } else {
            try (PrintWriter out = new PrintWriter(druhyArgument)) {
                out.print(data);
            }
        }

    }


}
