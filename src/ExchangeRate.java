
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * {@code ExchangeRate} represented as a {@code Double} with implementations of
 * primary methods.
 *
 * @convention <pre>
 * [all characters of $this.rep are '0' through '9']
 * </pre>
 * @correspondence <pre>
 * this = [the decimal number whose ordinary depiction is $this.rep]
 * </pre>
 *
 * @author Ryan Ouimet
 *
 */

public class ExchangeRate {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private Double rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = new Double(0);
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public ExchangeRate() {
        this.createNewRep();
    }

    /**
     * Constructor from {@code double}.
     *
     * @param d
     *            {@code double} to initialize from
     */
    public ExchangeRate(double d) {
        this.rep = new Double(d);
    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public ExchangeRate(int i) {
        this.rep = new Double(i);
    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public ExchangeRate(String s) {
        assert s != null : "Violation of: s is not null";
        assert s.matches(
                "-?\\d+(\\.\\d{1,2})?") : "Violation of: s must be a double value";

        this.rep = new Double(s);
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final ExchangeRate newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(ExchangeRate source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof ExchangeRate : ""
                + "Violation of: source is of dynamic type NaturalNumberExample";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        ExchangeRate localSource = (ExchangeRate) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    /**
     * Sets an exchange rate based on the ratio of currency2 to currency1.
     *
     * @param currency1
     *            the currency in the denominator
     *
     * @param currency2
     *            the currency in the numerator
     *
     */
    @Override
    public final void setRate(double currency1, double currency2) {
        this.rep = Double.valueOf(currency2 / currency1);
    }

    /**
     * Determines whether or not an ExchangeRate is worthless.
     *
     * @return true if the ExchangeRate is <= 0.
     */
    @Override
    public boolean isWorthless() {
        return this.rep.compareTo(0.0) <= 0;
    }

    /*
     * Secondary methods
     */

    /**
     * Inflates or deflates {@code this} by a given {@code ExchangeRate}.
     *
     * @param r
     */
    @Override
    public void multiplyRate(ExchangeRate r) {
        assert r != null : "Violation of: r is not null";
        this.rep *= r.rep;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println(
                "Welcome! Let's see some the ExchangeRate methods in action. The three we will see today are:");
        out.println("setRate, isWorthless, and multiplyRate");
        out.println();
        out.println("Let's start with setRate.");
        out.println();

        out.print(
                "Enter two currencies to make an ExchangeRate! The rate will be the ratio of the second entry to the first!");
        double currency1 = Double.valueOf(in.nextLine());
        double currency2 = Double.valueOf(in.nextLine());

        ExchangeRate r = new ExchangeRate();

        r.setRate(currency1, currency2);

        out.println("The exchange rate of " + currency2 + " to " + currency1
                + " is " + r.toString());

        out.println("Let's check out the isWorthless method!");
        if (r.isWorthless()) {
            out.println(r.toString() + "is ≤ 0, so it's worthless!");
        } else {
            out.println(r.toString() + "is ≥ 0, so it is NOT worthless!");
        }

        out.println("Finally, let's see the multiplyRate method.");
        out.println(
                "Enter an ExchangeRate to multiply your previous ExchangeRate by:");
        String inflationRateString = in.nextLine();
        ExchangeRate inflRate = new ExchangeRate1(inflationRateString);
        r.multiplyRate(inflRate);

        out.println("Your old rate is now " + r.toString()
                + "! That's all for now! Goodbye!");

        in.close();
        out.close();
    }

}
