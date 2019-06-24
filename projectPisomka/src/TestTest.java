import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class TestTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(Test.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @org.junit.Test
    public void isPrimeNumber() {
        Test prime = new Test();
        assertEquals(false,prime.isPrimeNumber(4));
        assertEquals(true,prime.isPrimeNumber(3));
    }

    @org.junit.Test
    public void removeVowels() {
        Test test = new Test();
        assertNotEquals("Skl",test.removeVowels("skl"));
       assertEquals("Skl",test.removeVowels("Skola"));
       assertNotEquals("skola",test.removeVowels("Skola"));
      assertEquals("",test.removeVowels("aou"));
      assertNotEquals("skl",test.removeVowels("skola"));


    }

    @org.junit.Test
    public void maxPrimeNumberBetweenAandB() {
        Test number = new Test();
        assertEquals(11,number.maxPrimeNumberBetweenAandB(0,12));
        assertEquals(3,number.maxPrimeNumberBetweenAandB(0,4));


    }

    @org.junit.Test
    public void convertHHMMtoHH() {
    }
}
