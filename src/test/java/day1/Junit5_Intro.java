package day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Junit5_Intro {

    @Test
    public void test(){
        System.out.println("Learning JUnit5");
        assertEquals(1,1);
      //  assertEquals(1,2,"Something is wrong!!");
    }
    //add one more test,

    //to assert your name first character start with letterA
    @Test
    public void test2(){

        String name="Mini";

//assertEquals("M",name.substring(0,1));
        assertEquals('M',name.charAt(0));
assertTrue(name.startsWith("M"));
    }

    //Test Lifecycle annotations
    //@BeforeAll @AfterAll @BeforeEach @AfterEach





}
