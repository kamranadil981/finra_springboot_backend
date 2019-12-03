package com.finra.finratest.helper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class GenerateCombinations {

    public static void findCombinations(String inputString, List<String> result, List<List<Character>> keypadList,
                                        int[] numbersArr, String resultString, int index) {
        if (index == -1) {
            result.add(resultString);
            return;
        }
        int digit = numbersArr[index];
        int len = keypadList.get(digit).size();
        String part = inputString;
        String x = part.replace((part.substring(part.length() - resultString.length())), resultString);
        result.add(x);
        for (int i = 0; i < len; i++) {
            findCombinations(inputString, result, keypadList, numbersArr, keypadList.get(digit).get(i) + resultString,
                    index - 1);
        }
    }

    public ResponseEntity<Object> getCombinations(String inputString) throws JSONException {
        List<String> result = new ArrayList<String>();
        List<List<Character>> keypadList = Arrays.asList(
                Arrays.asList('0'),
                Arrays.asList('1'),
                Arrays.asList('A', 'B', 'C'),
                Arrays.asList('D', 'E', 'F'),
                Arrays.asList('G', 'H', 'I'),
                Arrays.asList('J', 'K', 'L'),
                Arrays.asList('M', 'N', 'O'),
                Arrays.asList('P', 'Q', 'R', 'S'),
                Arrays.asList('T', 'U', 'V'),
                Arrays.asList('W', 'X', 'Y', 'Z')
        );

        String[] numbers = inputString.split("");
        int[] numbersArr = new int[numbers.length];
        for (int i = 0; i < numbersArr.length; i++) {
            numbersArr[i] = Integer.parseInt(numbers[i]);
        }
        findCombinations(inputString, result, keypadList, numbersArr, "", numbersArr.length - 1);
        LinkedHashSet<String> finalResult = new LinkedHashSet<String>(result);
        List list=new ArrayList();
        for (String x : finalResult)
            list.add(x);
        JSONObject jsonObject=new JSONObject();
        JSONArray jsonArray = new JSONArray(list);
        jsonObject.put("count",list.size());
        jsonObject.put("data",jsonArray);
        System.out.println(jsonObject);
        return ResponseEntity.ok(jsonObject.toString());
    }
    private static String createResponse(int count, LinkedHashSet<String> data) {
        return "{\n" +
                "\tcount : " + count + "\n\tdata : " + data + "}";
    }

}
