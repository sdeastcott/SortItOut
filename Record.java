public class Record
{
    private String name;
    private int SSN;
    private int DOB;

    public void setName(String name) {
        this.name = name;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public void setDOB(int DOB) {
        this.DOB = DOB;
    }

    public String getName() {
        return name;
    }

    public int getSSN() { return SSN; }

    public int getDOB() {
        return DOB;
    }

    public int getField(String sortingField)
    {
        if (sortingField.equals("DOB")) { return DOB; }
        return SSN;
    }
}
