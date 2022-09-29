package day1;


import org.junit.jupiter.api.*;

@DisplayName("Learning Test Lifecycle Annotations")
public class TestLifecycleAnnotations {

   @BeforeAll
   public static void init(){
       System.out.println("Before all is running");
   }
   @AfterAll
   public static void close(){
       System.out.println("After all is running");
   }

   @BeforeEach
   public void initEach(){
       System.out.println("Before Each is running");
   }
   @AfterEach
   public void TearDownEach(){
       System.out.println("After Each is running");
   }

    @Test
    public void test1(){
        System.out.println("Test1  is running ");


    }
    @Disabled
    @Test
    public void test2(){
        System.out.println("Test2 is running");


    }
    //ignoring certain test using @Disabled annotation











}
