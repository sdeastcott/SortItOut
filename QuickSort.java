public class QuickSort
{
    private int qsAssignment;
    private int qsComparison;
    private int sortSize;
    private String sortField;
    private Record[] data;

    /**
     * @param data       What we're sorting. 'data' is an array of 'Record' objects.
     *                   Each 'Record' has a name, SSN, and DOB.
     * @param sortSize   how much of the data we'll be sorting
     *                   Example: sortSize = 10; the first 10 values will be sorted
     * @param sortField  what field we'll be sorting by (SSN or DOB)
     */
    public QuickSort(Record[] data, int sortSize, String sortField)
    {
        this.data = data;
        this.sortSize = sortSize;
        this.sortField = sortField;
    }


    /**
     * @param lower  starting index indicating where we start our sort
     * @param upper  stopping index indiciating where we finish  our sort
     * @return       the sorted data array
     */
    public Record[] sort(int lower, int upper)
    {
        if (lower < upper)
        {
            int partition = partition(lower, upper);
            qsAssignment++; qsComparison++;
            sort(lower, partition - 1);
            sort(partition + 1, upper);
        }
        return data;
    }


    /**
     * Modifies the array segment in a way such that all the values to the left of the
     * partition are less than the partition, and all the values to the right are greater.
     *
     * @param lower  starting index indicating where we start our sort
     * @param upper  stopping index indicating where we finish our sort
     * @return       The index in which the partition was swapped to.
     *               This enables us to now sort the left and right of that position.
     */
    public int partition(int lower, int upper)
    {
        int i = lower - 1;
        int pivot = data[upper].getField(sortField);
        qsAssignment += 2;

        for (int j = lower; j < upper; j++)
        {
            if (data[j].getField(sortField) <= pivot)
            {
                ++i;
                exchange(i, j);
                qsAssignment++; qsComparison++;
            }
        }

        // Move pivot into correct position and return position index
        exchange(i + 1, upper);
        return i + 1;
    }


    /**
     * Swaps two Record objects.
     *
     * @param i  some object index
     * @param j  some object index
     */
    public void exchange(int i, int j)
    {
        Record temp = data[i];
        data[i] = data[j];
        data[j] = temp;
        qsAssignment += 3;
    }

    public int getAssignmentQS() { return qsAssignment; }

    public int getComparisonQS() { return qsComparison; }
}
