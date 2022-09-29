package day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MethodSourceForParameterizedTest {
    @ParameterizedTest
    @MethodSource("getManyNames")
    public void testPrintNames(String name){
        System.out.println("name = " + name);
        assertTrue(name.length()>=1);
    }

//
//    public static Stream<String>getManyNames(){
//        List<String> nameList = Arrays.asList("mini", "elli", "Chris", "Sophie");
//        return nameList;
//    }
    public static List<String>getManyNames() {
       List<String> nameList = Arrays.asList("mini", "elli", "Chris", "Sophie");
        return nameList;
    }
}
