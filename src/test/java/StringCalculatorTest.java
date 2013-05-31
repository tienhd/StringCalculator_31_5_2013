
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created with IntelliJ IDEA.
 * User: sqv-nbt
 * Date: 5/31/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */

public class StringCalculatorTest {

    @Test
    public void TestWithEmptyStringInput() {
        StringCalculator sc = new StringCalculator();
        Assert.assertEquals(0,sc.Add(""));
    }

    @Test
    public void TestWithTwoInputNumber() {
        StringCalculator sc = new StringCalculator();
        Assert.assertEquals(3,sc.Add("1,2"));
    }

    @Test
    public void TestWithMoreInputNumber() {
        StringCalculator sc = new StringCalculator();
        Assert.assertEquals(10,sc.Add("1,2,3,4"));
    }

    @Test
    public void TestWithEndLineDelimiterInTheString() {
        StringCalculator sc = new StringCalculator();
        Assert.assertEquals(7,sc.Add("1,2\n4"));
    }

    @Test
    public void TestWithDifferentDelimiter(){
        StringCalculator sc = new StringCalculator();
        Assert.assertEquals(5,sc.Add("//;\n2;3"));
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void TestExceptionHandlerWhenNegativeNumberInString() {
        exception.expect(NumberFormatException.class);
        exception.expectMessage("negatives not allowed");
        StringCalculator sc = new StringCalculator();
        sc.Add("1;-2,3");
    }

    @Test
    public void TestWithNumberOverThanOneThousand() {
        StringCalculator sc = new StringCalculator();
        Assert.assertEquals(3, sc.Add("//;\n1,2,1001;"));
    }

    @Test
    public void TestWithDefineStringDelimiter() {
        StringCalculator sc = new StringCalculator();
        Assert.assertEquals(6,sc.Add("//[***]\n1***2***3"));
    }

    @Test
    public void TestGetDefineDelimiterModule() {
        StringCalculator sc = new StringCalculator();
        Assert.assertEquals("***",sc.getDefineDelimiter("//[***]\n1***2***3)"));
    }
}
