package DataLayer;

public enum Profitability {
    ONE(1, "Text 1"),
    TWO(2, "Text 2"),
    THREE(3, "Text 3");

    private final int value;
    private final String displayValue;

    Profitability(int value, String displayValue) {
        this.value = value;
        this.displayValue = displayValue;
    }

    public int getValue() {
        return value;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    @Override
    public String toString() {
        return getDisplayValue();
    }
}
