public class RadixSort
{
    private int rsAssignment;

    private int sortSize;
    private String sortField;
    Record[] data;

    /**
     * @param sortSize   how much of the data we'll be sorting
     *                   Example: sortSize = 10; the first 10 indexes will be sorted
     * @param sortField  what field we'll be sorting by (SSN or DOB)
     */
    public RadixSort(Record[] data, int sortSize, String sortField)
    {
        this.data = data;
        this.sortSize = sortSize;
        this.sortField = sortField;
    }

    /**
     * @return  the sorted data array
     */
    public Record[] sort()
    {
        int numberLength = getMaxNumberLength();

        for (int position = 1; position <= numberLength; position++)
        {
            data = countingSort(position);
            rsAssignment++;
        }

        return data;
    }


    /**
     * Counting sort is the stable sort used in our radix sort for each digit position.
     *   First loop -  accumulate the frequency of each digit found in our data array
     *   Second loop - fill the auxillary array with the running total of frequencies
     *   Third loop -  place each digit in sorted order in our output array
     *
     * @param position  position of the digit to be sorted
     * @return          sorted array at specified position
     */
    private Record[] countingSort(int position)
    {
        int[] C = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // Auxillary array
        rsAssignment++;
        Record[] B = new Record[sortSize];          // Sorted array

        for (int i = 0; i < sortSize; i++)
        {
            int digit = extractDigit(data[i].getField(sortField), position);
            C[digit] += 1;
            rsAssignment += 2;
        }

        for (int i = 1; i < 10; i++)
        {
            C[i] += C[i - 1];
            rsAssignment++;
        }

        for (int i = sortSize - 1; i >= 0; i--)
        {
            int digit = extractDigit(data[i].getField(sortField), position);
            B[C[digit] - 1] = data[i];
            C[digit]--;
            rsAssignment += 3;
        }

        return B;
    }


    /**
     * This method uses modular arithmetic to extract a digit from a given number given a position.
     *
     * @param number    the number containing the digit we'd like to extract
     * @param position  position we wish to extract the digit from
     * @return
     */
    public int extractDigit(int number, int position)
    {
        int digit = (int) ((number / Math.pow(10, position - 1)) % 10);
        rsAssignment++;
        return digit;
    }


    /**
     * This method gets the max length for any value we come across during our sort
     * The max SSN should be no greater than 999-99-9999. Thus, length of 9.
     * The max DOB should be no greater than: 12/31/9999. Thus, length of 8.
     *
     * @return  max possible length of our sorting field's values
     */
    public int getMaxNumberLength()
    {
        if (sortField.equals("SSN"))
        {
            return 9;
        }

        return 8;
    }

    public int getAssignmentRS() { return rsAssignment; }

}



