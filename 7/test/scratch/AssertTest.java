package scratch;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.io.*;
import java.util.*;
import org.junit.*;
import org.junit.rules.*;

public class AssertTest {

    static class InsufficientFundsException extends RuntimeException {

        public InsufficientFundsException(String message) {
            super(message);
        }

        private static final long serialVersionUID = 1L;
    }

    static class Account {

        int balance;
        String name;

        Account(String name) {
            this.name = name;
        }

        void deposit(int dollars) {
            balance += dollars;
        }

        void withdraw(int dollars) {
            if (balance < dollars) {
                throw new InsufficientFundsException("残高不足です");
            }
            balance -= dollars;
        }

        public String getName() {
            return name;
        }

        public int getBalance() {
            return balance;
        }

        public boolean hasPositiveBalance() {
            return balance > 0;
        }
    }

    private Account account;

    @Before
    public void createAccount() {
        account = new Account("an account name");
    }

    // 残高がゼロ以上
    @Test
    public void hasPositiveBalance() {
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }

    @Test
    public void depositIncreasesBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertTrue(account.getBalance() > initialBalance);
        assertThat(account.getBalance(), equalTo(100));
    }

    @Test
    public void depositIncreasesBalance_hamcrestAssertTrue() {
        account.deposit(50);
        assertThat(account.getBalance() > 0, is(true));
    }

    @Test
    @ExpectToFail
    @Ignore
    public void assertFailure() {
        assertTrue(account.getName().startsWith("xyz"));
    }

    @Test
    @ExpectToFail
    @Ignore
    public void matchesFailure() {
        assertThat(account.getName(), startsWith("xyz"));
    }

    @Test
    @Ignore
    public void testWithWorthlessAssertionComment() {
        account.deposit(50);
        assertThat("口座の残高は100です", account.getBalance(), equalTo(50));
    }

    // 残高以上の出金は例外を発生させる
    @Test(expected = InsufficientFundsException.class)
    public void throwsWhenWithdrawingTooMuch() {
        account.withdraw(100);
    }

    @Test
    public void throwsWhenWithdrawingTooMuchTry() {
        try {
            account.withdraw(100);
            fail();
        }
        catch (InsufficientFundsException expected) {
            assertThat(expected.getMessage(), equalTo("残高不足です"));
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void exceptionRule() {
        thrown.expect(InsufficientFundsException.class);
        thrown.expectMessage("残高不足です");

        account.withdraw(100);
    }

    @Test
    public void readsFromTestFile() throws  IOException {
        String filename = "test.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write("test data");
        writer.close();
        // ...
    }

    @After
    public void deleteForReadsFromTestFile() {
        new File("test.txt").delete();
    }
}
