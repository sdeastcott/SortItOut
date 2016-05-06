import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The Utility class contains several methods that I consider to be "utilities" for the rest
 * of my project to properly function.
 */
public class Utility
{
    String sortField;
    int fileSize;
    int numPass;

    /**
     * Read in the file line by line, organizing information given from each line into an object.
     *
     * @param fileName  the file we're getting data from and will ultimately have to sort later
     * @return          an array of 'Record' objects
     *                  Each 'Record' object will consist of a name, DOB, and SSN
     */
    public Record[] getInputData(String fileName)
    {
        Record[] data = null;

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            fileSize = Integer.parseInt(reader.readLine().split(",")[0]);

            if (fileSize == 0 || fileSize < numPass)
            {
                System.out.println("There's nothing for me to sort! Either the file is empty, or you wanted me to make more passes than feasible.");
                System.exit(1);
            }

            String line;
            String[] splitLine;
            data = new Record[fileSize];
            for (int i = 0; i < fileSize; i++)
            {
                // Read in each line and split by comma
                line = reader.readLine();
                splitLine = line.split(",");

                // Initialize 'Record' field
                // Convert both SSN and DOB from string to int
                // Store record into the 'records' array
                Record record = new Record();
                record.setName(splitLine[0]);
                record.setDOB(Integer.parseInt(splitLine[1]));
                int socialSecurityNumber = Integer.parseInt(splitLine[2].substring(0, 3) + splitLine[2].substring(4, 6) + splitLine[2].substring(7));
                record.setSSN(socialSecurityNumber);
                data[i] = record;
            }
        }
        catch (FileNotFoundException exception)
        {
            // TODO: Is it correct to use exception.getMessage()?
            System.out.println("FileNotFound Exception: " + exception.getMessage());
            System.exit(1);
        }
        catch (IOException exception)
        {
            System.out.println("IO Exception: " + exception.getMessage());
            System.exit(1);
        }

        return data;
    }


    /**
     * The method that takes in our sorted array as a parameter and formats the
     * output into a presentable form
     *
     * @param data  the sorted array that we need to format and output to std
     */
    public void outputSortedData(Record[] data)
    {
        System.out.println("- Sorted array");
        String name = "";
        String SSN = "";
        String DOB = "";
        String formattedDOB = "";
        String formattedSSN = "";

        for (Record record : data)
        {
            name = record.getName();
            DOB = Integer.toString(record.getDOB());
            SSN = Integer.toString(record.getSSN());
            formattedSSN = SSN.substring(0, 3) + "-" + SSN.substring(3, 5) + "-" + SSN.substring(5, 9);

            if (DOB.length() == 7)
            {
                DOB = "0" + DOB;
            }

            formattedDOB = "DOB:" + DOB.substring(0, 2) + "/" + DOB.substring(2, 4) + "/" + DOB.substring(4, 8);
            System.out.printf("%-12s %-13s %2s\n", name, formattedSSN, formattedDOB);
        }
    }


    /**
     * argReader reads in the arguments given by user and verifies that they're valid
     *
     * @param args  the arguments the user entered from the command line
     */
    public void argReader(String[] args)
    {
        // -n followed by a number and either '-B' or '-S' is required
        if (args.length != 3)
        {
            System.out.println("I was expecting: -n <count> and -B or -S");
            System.exit(1);
        }

        for (int i = 0; i < args.length; i++)
        {
            switch (args[i])
            {
                case "-B":
                    sortField = "DOB";
                    break;

                case "-S":
                    sortField = "SSN";
                    break;

                case "-n":
                    if (integerCheck(args[i + 1]))
                    {
                        i++;
                        break;
                    }

                default:
                    System.out.println("Something's off... did you enter everything correctly?");
                    System.exit(1);
            }
        }
    }


    /**
     * The integerCheck method uses a regex expression to verify that the argument
     * given is indeed an integer. It will also check that the integer is > 0, because
     * if given 0 passes, there would simply be no work to do.
     *
     * @param arg  the argument that we want to verify as a valid integer
     * @return     a boolean indicating whether or not the argument was verifiable
     */
    public boolean integerCheck(String arg)
    {
        if (arg.matches("^-?\\d+$"))
        {
            numPass = Integer.parseInt(arg);
            if (numPass > 0)
            {
                return true;
            }
        }
        return false;
    }

    public String getSortField()
    {
        return sortField;
    }

    public int getFileSize() { return fileSize; }

    public int getNumPass() { return numPass; }

    public int getSortIncrement()
    {
        return (fileSize / numPass);
    }
}
