import com.sun.org.apache.xpath.internal.objects.XString;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        Scanner firstScanner = new Scanner(System.in);
        Scanner secondScanner = new Scanner(System.in);
        String firstWordList = firstScanner.next();
        String secondWordList = secondScanner.next();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行;
        result.stream().forEach(System.out::println);

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        List<String> inputValidFirstWordList;
        List<String> inputValidSecondWordList;
        List<String> result;
        //判断输入的合理性
        inputValidFirstWordList = inputTest(firstWordList.toUpperCase());
        inputValidSecondWordList= inputTest(secondWordList.toUpperCase());
        result = inputValidFirstWordList.stream().filter(string -> inputValidSecondWordList.contains(string)).collect(Collectors.toList());

        return result;
    }

    public static List<String> inputTest(String wordInput) {
        //判断非字母
        for (int i = 0; i < wordInput.length(); ++i) {
            if(!(wordInput.charAt(i) >= 'A' && wordInput.charAt(i) <= 'Z' || wordInput.charAt(i) == ',')) {
                throw new RuntimeException("Input Invalid");
            }
        }

        String[] wordArray = wordInput.split(",");
        //重用流
        Supplier<Stream<String>> wordStreamSupplier = () -> Arrays.stream(wordArray);
        //判断连续逗号
        long countEmpty =  wordStreamSupplier.get().filter(string -> string.isEmpty()).count();
        if(countEmpty > 0) {
            throw new RuntimeException("Input Invalid");
        }

        return wordStreamSupplier.get().map(string -> string.replace(""," ").trim())
                .distinct().sorted().collect(Collectors.toList());
    }

}
