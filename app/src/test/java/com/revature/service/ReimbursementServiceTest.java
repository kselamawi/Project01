package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.junit.After;
import org.junit.Before;
//import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

public class ReimbursementServiceTest {

    @Mock
    UserDao ud;
    ReimbursementDao rd;

    @InjectMocks
    UserService us;
    ReimbursementService rs;
    User u;
    Reimbursement r;

    /*
        //BeforeClass will run some method before the entire test class
        @BeforeClass
        public static void setupBeforeClass() throws Exception{
            System.out.println("This runs before any test once");
            calc = new Calculator();
        }

        @AfterClass
        public static void tearDownAfterClass() throws Exception {
            System.out.println("This runs after the entire class is done");
        }
    */
    @Before
    public void setup() throws Exception {
        //We can use MockitoAnnotations.openMocks() to essentially allow mocking in our test class
        System.out.println("This one is before every test method");
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("This runs after every test");
    }

    @Test
    public void testCreate() {
        r = new Reimbursement();
        when(rd.createReimbursement(r)).thenReturn(true);
        assertTrue(rs.createReimbursement(r));
    }
}
/*
    @Test
    public void negativeAdditionTest(){
        assertNotEquals(4, calc.addition(2,3));
    }

    //If we for some reason want to ignore a test
    //We can use the @Ignore annotation
    //@Ignore
    @Test
    public void subTest(){
        assertEquals(2, calc.subtraction(3,1));
    }

    //If we want to check if an exception was thrown
    @Test(expected = ArithmeticException.class)
    public void testDivideByZero(){
        calc.division(2,0);
    }

    //Mockito Quick Examples
    //We can use the @Mock to mock an object, in our case we will our calculator
    @Mock
    Calculator mockedCalc;

    @Test
    public void mockedCalculatorTest(){
        //Sense we are mocking our calculator we can things like intercepting method calls
        //and returning whatever we like
        //mockedCalc.addition(1,1);

        //We can tell mockito when this addition method is called, return whatever we want
        //This example is obviously trivial, but imagine we have a method that makes a call to
        //a database, we dont want to actually hit that production database everytime we run the test
        //with this mocked object we can intercept this call to the database, and instead return some set data
        //to test with the dependent method
        when(mockedCalc.addition(1,1)).thenReturn(2);
        assertEquals(2, mockedCalc.addition(1,1));

    }

    @Spy
    Calculator spiedCalc;

    @Test
    public void spiedCalcTest(){
        //Now we have setup mockito to watch for calls to the spied calculator, and if we
        //make a call to division, we simply want to return the number 2
        Mockito.doReturn(8).when(spiedCalc).division(8,1);

        assertEquals(8, spiedCalc.division(8,1));
    }


}





/*package com.revature.service;

import com.revature.models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class ReimbursementServiceTest {
    @Mock
    ReimbursementService reimbursementService = new ReimbursementService();

    User employee;
    User manager;
    Reimbursement reimbursement;

    @Before
    public void testObjects(){
        MockitoAnnotations.openMocks(this);
        employee = new User(1, "isaiah", "payne", UserRole.EMPLOYEE, "isaiah.payne@email.com","passWord");
        manager = new User(2, "selamawi", "kahsai", UserRole.MANAGER, "selamawi.kahsai@email.com", "wordPass");
        reimbursement = new Reimbursement(1, 50, ReimbursementType.FOOD, ReimbursementStatus.PENDING, employee, manager, "Had to buy food during meeting");
    }

    @Test
    public void approveReimbursementStatus() {

        reimbursementService.changeReimbursementStatus(1, 2, true);

        assertEquals(ReimbursementStatus.APPROVED, reimbursement.getReimbursementStatus());


    }




    @Test
    public void getAllPendingReimbursementsByAuthor() {
    }

    @Test
    public void getAllResolvedReimbursementsByAuthor() {
    }

    @Test
    public void getAllPendingReimbursements() {
    }

    @Test
    public void getAllResolvedReimbursements() {
    }

    @Test
    public void getAllReimbursementsByAuthor() {
    }

    @Test
    public void createReimbursement() {
    }

    @Test
    public void deleteReimbursement() {
    }

    @Test
    public void updateReimbursement() {
    }

    @Test
    public void getAllReimbursements() {
    }

    @Test
    public void getReimbursementById() {
    }
}

 */