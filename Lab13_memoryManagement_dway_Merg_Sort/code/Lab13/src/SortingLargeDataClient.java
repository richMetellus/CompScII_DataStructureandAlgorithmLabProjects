
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * This class take a unsorted file of size 1 billion double, sort it and put
 * it to a new file as sorted in ascending order.
 * @author Richelin Metellus
 * @version 05/04/2017
 */
public class SortingLargeDataClient {

    static int staticVar = 0;
    static long cummulativeTime = 0;

    public static void main(String[] args) throws IOException {
        
        String folderDir = "C:\\Users\\Rich\\Documents\\TempFiles\\" ; // root directory for where to put all files.
        File[] fileArray;
        File sortedFile = new File(folderDir + "sortedData.txt");
        /**
         * Max memory jvm will attempt to use.
         */
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("max memory " + maxMemory);

        /**
         * total memory currently in use by jvm.
         */
        System.out.println(" Total memmory(bytes) currently in used:"
                + Runtime.getRuntime().totalMemory());

        /**
         * free memory available for future use
         */
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("Free memory(bytes): " + freeMemory);
        long totalNumberOfElement = 1000000000;

        double blockSizeByte = freeMemory * 70/100;         // using only x amount of free memory per block/file
        int numberOfBlock = (int) ((Double.BYTES * totalNumberOfElement) / blockSizeByte);        // number of file needed after dissection.
        System.out.printf(" number of block/file: %s files, block/file size: %s bytes ", numberOfBlock, blockSizeByte);
        // number of double value per block/file
        int elementPerBlock = (int) ((totalNumberOfElement / numberOfBlock));
        System.out.printf("elementPerBlock = %s \n\n ", elementPerBlock);

        fileBuilder(folderDir,"data.txt", 1000000000);
        File f = new File(folderDir + "data.txt");
        Scanner scan = new Scanner(f);
        System.out.println("the 1st 20 values of the unsorted text file");
        for (int i = 0; i < 20; i++) {
            System.out.print(i + ":");
            if (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        }

        fileArray = createSortedBlock(folderDir,f, totalNumberOfElement, numberOfBlock, elementPerBlock);
        
        long sortedFileStartTime = System.currentTimeMillis();
        nWayMerge(sortedFile,fileArray);
        long sortedFileEnd  = System.currentTimeMillis();
        long sortedElapsedTime = sortedFileEnd -sortedFileStartTime;
        System.out.printf("Runtime for writing sorted data set of size     \t for N\t = %,10d values\t                time \t= %,10d miliseconds \n", totalNumberOfElement, sortedElapsedTime);
      
        System.out.println("The first 20 element of sorted data text file");
        Scanner  sortedFileScan = new Scanner(sortedFile);
        for (int i = 0; i < 20; i++) {
            System.out.print(i + ":");
            if (sortedFileScan.hasNextDouble()) {
                System.out.println(i+1 +":" + sortedFileScan.nextDouble());
            }
        }
        System.out.printf("Final Total  Time spent so far in sorting the file:   \t                            sorting cummulative\ttime   \t= %,10d milliseconds\n", cummulativeTime );
    }

    /**
     *
     * @param folderDirective : directive where to put the file
     * @param fileName : name of file in form "fileName.ext" ext can be .txt or
     * whatever.
     * @param N : the number of double element that should be in the file.
     */
    public static void fileBuilder(String folderDirective, String fileName, int N) throws IOException {
        try {
            String filePath = folderDirective + fileName; 
            File f = new File(filePath);

            if (f.createNewFile())
            {
                System.out.println("File has been created");
                try {
                    FileOutputStream fos = new FileOutputStream(filePath, false); // false to write to file; true to append
                    PrintWriter pw = new PrintWriter(fos);
                    Random rand = new Random();

                    long startTime = System.currentTimeMillis();
                    for (int i = 0; i < N; i++) {
                        pw.println(rand.nextDouble());
                    }
                    pw.close();
                    long endTime = System.currentTimeMillis();
                    
                    long elapsedTime = endTime - startTime;
                    System.out.printf("Runtime for writing data set of size   for N     \t = %,10d values\t                       time \t= %,10d miliseconds \n", N, elapsedTime);
                } 
                catch (FileNotFoundException fnfe) {
                    System.out.printf("unable to find %s file\n", fileName);
                }
            } 
            else 
            {
                System.out.println("File already exists");
            }
        } catch (IOException e)
        {
            System.out.println("Cannot create the new file");
            e.printStackTrace();
        }
    }

    /**
     * This method will create a sorted file for however file specified by the
     * user. the number of file/chunks passed as args should be dynamically determined based on
     * the fraction free of memory memory and optimized block size (#elements).
     *
     * @param parentFile : the unsorted large document to sort
     * @param totalNumberOfElements : number of elements in the file to sort.
     * @param numberOfFile : the number of file chunk(a block) to dissect the
     * file into.
     * @param elementsPerBlock : the minimun size/ number of element a file
     * chunk can have.
     * @throws FileNotFoundException
     */
    public static File[] createSortedBlock(String folderDirective,File parentFile, long totalNumberOfElements, int numberOfFile, int elementsPerBlock) throws FileNotFoundException {
        Scanner scanFile = new Scanner(parentFile);
        double[] tempArray;
        File[] tempFileList = new File[numberOfFile];
        int maxElementPerBlock = (int) (elementsPerBlock + (totalNumberOfElements - (elementsPerBlock * numberOfFile)));
        for (int i = 0; i < numberOfFile; i++) 
        {
            int arraySize = (i < numberOfFile - 1) ? elementsPerBlock : maxElementPerBlock;
            tempArray = new double[arraySize];
            for (int j = 0; j < elementsPerBlock; j++) 
            {
                if (scanFile.hasNextLine()) {
                    tempArray[j] = Double.parseDouble(scanFile.nextLine());
                }
            }
            
//            System.out.println("\n\nprinting newly created temp array\n");
//            for (int k = 0; k < 10; k++) {
//                System.out.println(tempArray[k]);
//            }
            // loop ends so sort array and write to new file.
            System.out.println("\n\nstarting to create file " + i);
            tempFileList[i] = sortedBlkToFile(folderDirective,"temp",tempArray);

        }
        return tempFileList;
    }
/**
*
    * @param folderDirective
    * @param fileN
    * @param unsortedArr an unsorted array that will be sorted and write to a
    * new file block
    * @return the sorted file.
*/
    public static File sortedBlkToFile(String folderDirective,String fileN, double[] unsortedArr) {
        String fileName = fileN + staticVar++ + ".txt";
        System.out.println("\tsorting file/block");
        long quickStartTime = System.currentTimeMillis();
        quickSortInPlace(unsortedArr, 0, unsortedArr.length - 1);
        long quickEndTime = System.currentTimeMillis();
        long quickElapsedTime = quickEndTime - quickStartTime;
        cummulativeTime  += quickElapsedTime;
       
        System.out.printf("\tRuntime to sort block: %12s of SIZE               \t = %,7d double numbers using quickSort\ttime \t= %,10d milliseconds\n", fileName, unsortedArr.length, quickElapsedTime);
        System.out.printf("\tTotal  Time spent so far in sorting the blocks:   \t                                sorting cummulative\ttime   \t= %,10d milliseconds\n", cummulativeTime );
        File f = null;
        try {

            String filePath = folderDirective + fileName;                   // absolute path of the file;
             f = new File(filePath);

            if (f.createNewFile()) 
            {
                System.out.println("\tDestination File for block has been created");
                try {
                    FileOutputStream fos = new FileOutputStream(filePath, false); // false to write to file; true to append
                    PrintWriter pw = new PrintWriter(fos);
                    
                    System.out.println("\twriting sorted array to file\t\t" + fileName);
                    long wStartTime = System.currentTimeMillis();
                    for (int i = 0; i < unsortedArr.length; i++) {              // unsorted array now become a sorted Array;
                        pw.println(unsortedArr[i]);
                    }
                    pw.close();
                    long wEndTime = System.currentTimeMillis();
                    long wElapsedTime = wEndTime - wStartTime;
               
                    System.out.printf("\tRuntime to send sorted array to file:%5s \t  time \t= %,10d milliseconds\n",fileName,wElapsedTime); 

                } 
                catch (FileNotFoundException fnfe) {
                    System.out.printf("unable to find %s file\n", fileName);
                }
            } 
            else 
            {
                System.out.println("File already exists");
                
            }
        } 
        catch (IOException e) {
            System.out.println("Cannot create the new file");
        }
     return f;   
    }
    
    public static void nWayMerge(File sortedMaster, File[] sortedChunks) throws FileNotFoundException 
    {
    Scanner[] chunkScanner = new Scanner[sortedChunks.length];  // if 189 files, 189 scanners
    Double buffer[] = new Double[chunkScanner.length];         // if 189 file, 189 Doubles values

    FileOutputStream fos;
    PrintWriter pw;
    try 
    {
        fos = new FileOutputStream(sortedMaster, false); // false to write to file; true to append
        pw = new PrintWriter(fos);
    } 
    catch(FileNotFoundException fnfe) {
        System.out.printf("unable to find %s file\n", sortedMaster);
        return;                 // if we can't open file so we don't have to do below steps.
    }

    for (int i = 0; i < chunkScanner.length; i++) {
        chunkScanner[i] = new Scanner(sortedChunks[i]);         //  for whatever file, create an associate scanner.
        if (chunkScanner[i].hasNextDouble()) {
            buffer[i] = chunkScanner[i].nextDouble();           // populate the buffer to take the 1st element of each file. since by default they are null initially.
        }
    }
/*------------ updating buffer for each minimun value. -------------- */
    do {
        int indexOfMin = -1;        // index of minVal for which value to update the scanner at.
        double minVal = 0;

        // find min value in buffer and min index of that value.
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] != null && (indexOfMin == -1 || buffer[i] < minVal)) {
                minVal = buffer[i];
                indexOfMin = i;
            }
        }

        // if no min found, picture all buffer cells as null. then should break. we're done.
        if (indexOfMin == -1) {
            break;
        }

        pw.println(buffer[indexOfMin]);                 // write min to sortedMaster file.
        // update/replace the value of buffer at the index we find it.
        if (chunkScanner[indexOfMin].hasNextDouble()) {
            buffer[indexOfMin] = chunkScanner[indexOfMin].nextDouble();
        } else {
            buffer[indexOfMin] = null;          // if we don't have a next value in file at buffer[i];
        }

    } while (true);

    pw.close();                                 // done, close to save file.
}


    /**
     *
     * @param S
     * @param a start of the segment
     * @param b end of the segment
     */
    public static void quickSortInPlace(double[] S, int a, int b) // 1st call Qs(S,0,7) for an array of size 8.
    {
        if (a >= b) {
            return;         // subarray is trivially sorted. Base case
        }                                   // when we have only 1 element into the subarray. (a=0, b=0) or the segment  is invalid

        /*start of partionning logic(where element lesser than the pivot on left of pivot
        and element greater than the pivot on the right of pivot.
         */
        int left = a;               // the index 1st element in subarray
        int right = b - 1;           // index element before pivot 
        double pivot = S[b];           // last element in orginal segment as pivot
        double temp;                   // temp object for swapping

        while (left <= right) {
            // scan until reaching value equal or larger than pivot(or right marker)
            while (left <= right && S[left] < pivot) //:- (85) > pivot(50) in comp result: -1 > 0..false do not incremnt left index position. 
            {
                left++;
            }
            // scan until reaching value equal or smaller than pivot(or left marker)
            while (left <= right && (S[right] > pivot)) // (96) > 50 comp: -1 < 0 true
            {
                right--;
            }

            if (left <= right) // indices did not strictly cross
            {
                // swap values and shring range
                temp = S[left];
                S[left] = S[right];
                S[right] = temp;

                left++;
                right--;
            }
        }
        //put pivot into its final place(currently marked by left index)
        temp = S[left];
        S[left] = S[b];
        S[b] = temp;
        //----- End of partitioning---

        //make recursive calls
        quickSortInPlace(S, a, left - 1);
        quickSortInPlace(S, left + 1, b);
    }
   
    
//    System.out.printf("Runtime for writing sorted data set of size     \t for N\t = %,10d values\t                time \t= %,10d miliseconds \n", totalNumberOfElement, sortedElapsedTime);
//    System.out.printf("Runtime for writing data set of size   for N     \t = %,10d values\t                       time \t= %,10d miliseconds \n", N, elapsedTime);
//    System.out.printf("Runtime to sort block: %5s of SIZE               \t = %,7d double numbers using quickSort\ttime \t= %,10d milliseconds \n", fileName, unsortedArr.length, quickElapsedTime);
//    System.out.printf("Total  Time so Far in sorting block               \t                                        time\t= %,10d milliseconds\n: ", cummulativeTime );
//    System.out.printf("Runtime to send sorted array to file: %5s of SIZE  \t = %,7d double numbers \t  time \t= %,10d milliseconds\n",fileName,unsortedArr.length,wElapsedTime); 
}
