//****************************
// Project #1 - Sort It Out
//
// Author: Steven Eastcott
// CWID:   11491519
// Course: CS 360
//****************************


public class Main
{

    public static void main(String[] args)
    {
        Utility utility = new Utility();

        // Check for invalid input
        // Determine field to sort by along with the sorting increment
        utility.argReader(args);

        // Get data from .csv file
        Record[] data = utility.getInputData("personnel.csv");
        String sortField = utility.getSortField();
        int sortIncrement = utility.getSortIncrement();
        int fileSize = utility.getFileSize();
        int numPass = utility.getNumPass();

        QuickSort qsSortPortion;
        RadixSort rsSortPortion;
        Record[] qsSorted = null;
        Record[] rsSorted = null;
        String qsAssignment = "";
        String qsComparison = "";
        String rsAssignment = "";
        String rsComparison = "";

        for (int i = sortIncrement; i <= fileSize; i += sortIncrement)
        {
            // If number of passes doesn't divide evenly into the file size, we need to do this.
            // This adds to i whatever is remaining on the last pass
            if ((i / sortIncrement) == numPass)
            {
                i += (fileSize % numPass);
            }

            qsSortPortion = new QuickSort(data, i, sortField);
            rsSortPortion = new RadixSort(data, i, sortField);

            qsSorted = qsSortPortion.sort(0, i - 1);
            rsSorted = rsSortPortion.sort();

            qsAssignment += qsSortPortion.getAssignmentQS() + " ";
            qsComparison += qsSortPortion.getComparisonQS() + " ";

            rsAssignment += rsSortPortion.getAssignmentRS() + " ";
        }

        System.out.println("* Quicksort");
        utility.outputSortedData(qsSorted);
        System.out.println("\n- Assignments:\n" + qsAssignment);
        System.out.println("\n- Comparisons:\n" + qsComparison);

        System.out.println();

        System.out.println("* Radix sort");
        utility.outputSortedData(rsSorted);
        System.out.println("\n- Assignments:\n" + rsAssignment);
    }
}
