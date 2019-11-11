import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class testFunction {
    private ArrayList<String> treeData;
    private SequentialRepresentation seqTree;
    private LinkedRepresentation linkTree;
    private int treeSize;//the node amount of the tree
    private int testSize;//testing dataset size
    private String[] sampleTestData;//data sampled from the whole dataset for finding nodes or its parents/children


    /**
     * Generate testing tree data
     */
    public void generateTreeData(){
        for (int i = 1;i <= treeSize;i++){
            treeData.add(String.valueOf(i));
        }
        Collections.shuffle(treeData);
    }

    /**
     * Generate one sample, using sampling with replacement.
     */
    private int sampleWithReplacement(int mEndOfRange,int mStartOfRange) {
        Random mRandGen = new Random();
        return mRandGen.nextInt(mEndOfRange - mStartOfRange + 1) + mStartOfRange;
    } // end of sampleWithReplacement()

    /**
     * Generate testing data for finding nodes
     *
     */
    public void generateTestData() {
        String[] samples = new String[testSize];
        int number;
        for (int i = 0; i < testSize; i++) {
            number = sampleWithReplacement(treeSize-2,1);
            samples[i] = treeData.get(number);
        }

        sampleTestData = samples;
    }

    /**
     * generate the tree data based on BSP_combined.text
     */
    public void generateTreeDataByGivenData(){
        ArrayList<String[]> dataToken = readData();
        ArrayList<String> data = new ArrayList<>();

        for(int i =0;i < dataToken.size();i++){
            String[] token = dataToken.get(i);
            for (int j = 0; j < token.length; j++){
                if(!data.contains(token[j])) {
                    data.add(token[j]);
                }
            }
        }
        treeData=data;
    }

    /**
     * non default constructor
     * @param treeSize tree size
     * @param testSize test data size
     */
    public testFunction(int treeSize, int testSize) {
        treeData = new ArrayList<String>(); // data for tree
        seqTree = new SequentialRepresentation();
        linkTree = new LinkedRepresentation();
        this.treeSize = treeSize;
        this.testSize = testSize;
        sampleTestData = new String[0];
    }


    /**
     * seqTree inorder traverse test
     * @return totalTime
     */
    public long seqInorderTraverseTime(){
        long startTime = System.nanoTime();
        PrintWriter printWriter = new PrintWriter(System.out);
        seqTree.printInInorder(printWriter);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * linkTree inorder traverse test
     * @return totalTime
     */
    public long linkInorderTraverseTime(){
        long startTime = System.nanoTime();
        PrintWriter printWriter = new PrintWriter(System.out);
        linkTree.printInInorder(printWriter);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * seqTree preorder traverse test
     * @return totalTime
     */
    public long seqPreorderTraverseTime(){
        long startTime = System.nanoTime();
        PrintWriter printWriter = new PrintWriter(System.out);
        seqTree.printInPreorder(printWriter);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * linkTree preorder traverse test
     * @return totalTime
     */
    public long linkPreorderTraverseTime(){
        long startTime = System.nanoTime();
        PrintWriter printWriter = new PrintWriter(System.out);
        linkTree.printInPreorder(printWriter);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * seqTree postorder traverse test
     * @return totalTime
     */
    public long seqPostorderTraverseTime(){
        long startTime = System.nanoTime();
        PrintWriter printWriter = new PrintWriter(System.out);
        seqTree.printInPostorder(printWriter);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * linkTree postorder traverse test
     * @return totalTime
     */
    public long linkPostorderTraverseTime(){
        long startTime = System.nanoTime();
        PrintWriter printWriter = new PrintWriter(System.out);
        linkTree.printInPostorder(printWriter);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }


    /**
     * time of finding a node in seqTree
     */
    public long findSeqTreeNodeTime(){
        long startTime = System.nanoTime();

        for (int i = 0;i < testSize;i++){
            seqTree.findNode(sampleTestData[i]);
        }
        long endTime = System.nanoTime();
        return endTime-startTime;
    }

    /**
     * time of finding a node in linkTree
     */
    public long findLinkTreeNodeTime(){
        long startTime = System.nanoTime();

        for (int i = 0;i < testSize;i++){
            linkTree.findNode(sampleTestData[i]);
        }
        long endTime = System.nanoTime();
        long runTime = endTime-startTime;
        return runTime;
    }

    /**
     * time of finding a node's parent in seqTree
     */
    public long findSeqTreeNodeParentTime(){

        long startTime = System.nanoTime();
        for (int i = 0;i < testSize;i++){
            seqTree.findParent(sampleTestData[i]);
        }
        long endTime = System.nanoTime();
        return endTime-startTime;
    }

    /**
     * time of finding a node's parent in linkTree
     */
    public long findLinkTreeNodeParentTime(){

        long startTime = System.nanoTime();
        for (int i = 0;i < testSize;i++){
            linkTree.findParent(sampleTestData[i]);
        }
        long endTime = System.nanoTime();
        return endTime-startTime;
    }

    /**
     * time of finding a node's children in seqTree
     */
    public long findSeqTreeNodeChildrenTime(){
        long startTime = System.nanoTime();
        for (int i = 0;i < testSize;i++){
            seqTree.findChildren(sampleTestData[i]);
        }
        long endTime = System.nanoTime();
        return endTime-startTime;
    }

    /**
     * time of finding a node's children in linkTree
     */
    public long findLinkTreeNodeChildrenTime(){
        long startTime = System.nanoTime();
        for (int i = 0;i < testSize;i++){
            linkTree.findChildren(sampleTestData[i]);
        }
        long endTime = System.nanoTime();
        return endTime-startTime;
    }


    /**
     * add data to sequential tree
     */
    private void addSeqTree(){
        seqTree.setRootNode(treeData.get(0));
        int parentIndex = 0;
        int childIndex = 1;
        while(childIndex + 1 < treeData.size()){
            seqTree.splitNode(treeData.get(parentIndex), treeData.get(childIndex),treeData.get(childIndex+1));
            parentIndex += 1;
            childIndex += 2;
        }
    }

    /**
     * add data to linked tree
     */
    private void addLinkTree(){
        linkTree.setRootNode(treeData.get(0));
        int parentIndex = 0;
        int childIndex = 1;
        while(childIndex + 1 < treeData.size()){
            linkTree.splitNode(treeData.get(parentIndex), treeData.get(childIndex),treeData.get(childIndex+1));
            parentIndex += 1;
            childIndex += 2;
        }
    }

    /**
     * Calculate the average value of a arraylist
     * @param list
     * @return average value
     */
    public long averageCalculator(ArrayList<Long> list){
        if (list.size()==0){
            return 0;
        }
        long total = 0;
        for (int i = 0;i<list.size();i++){
            total += list.get(i);
        }
        return total/list.size();
    }


    /**
     * seqTree construct time
     * @return totalTime
     */
    public long seqAddTime(){

        long startTime = System.nanoTime();
        addSeqTree();
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return totalTime;
    }

    /**
     * linkTree construct time
     * @return totalTime
     */
    public long linkAddTime(){

        long startTime = System.nanoTime();
        addLinkTree();
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        return totalTime;
    }


    /**
     * Know the basic information from the given conbined.test data
     */
    public void informationOfCombined(){
        //read the given BSP_conmbined.txt data set
        ArrayList<String[]> dataToken = readData();
        //data holder for all data
        ArrayList<Integer> allVariables = new ArrayList<>();
        //data holder for no duplicated data
        ArrayList<Integer> noDuplicate = new ArrayList<>();

        System.out.println("Token amount is:" + dataToken.size());


        for(int i =0;i < dataToken.size();i++){
            String[] token = dataToken.get(i);
            for (int j = 0; j < token.length; j++){
                allVariables.add(Integer.parseInt(token[j]));
            }
        }

        System.out.println("Total variables amount is:" + allVariables.size());

        for(int i =0;i < allVariables.size();i++){
            if (!noDuplicate.contains(allVariables.get(i))){
                noDuplicate.add(allVariables.get(i));
            }
        }

        Collections.sort(noDuplicate);

        System.out.println("Total " + noDuplicate.size() + " different variables.");
        System.out.println("First variable is: " + noDuplicate.get(0));
        System.out.println("Last variable is: " + noDuplicate.get(noDuplicate.size()-1));
    }

    /**
     * read dataset from BSP_combined.txt file
     */
    private ArrayList<String[]> readData(){
        ArrayList<String[]> dataList = new ArrayList<>();
        String inputFilename = "BSP_combined.txt";

        // if file specified, then load file
        if (inputFilename != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename))) {
                String line;
                String delimiter = "[ \t,]+";
                String[] tokens;
                String srcLabel, leftChild, rightChild;
                boolean hasRoot = false;
                while ((line = reader.readLine()) != null) {
                    tokens = line.split(delimiter);
                    dataList.add(tokens);
                }
            } catch (FileNotFoundException ex) {
                System.err.println("File " + inputFilename + " not found.");
            } catch (IOException ex) {
                System.err.println("Cannot open file " + inputFilename);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return dataList;
    }

    /**
     * set tree data
     * @param data
     */
    public void setTreeData(ArrayList<String> data) {
        this.treeData = treeData;
    }

    /**
     * get tree data
     * @return tree data
     */
    public ArrayList<String> getTreeData() {
        return treeData;
    }

    /**
     * get tree node amount
     * @return treeSize
     */
    public int getTreeSize() {
        return treeSize;
    }


    /**
     * set tree node amount
     * @param treeSize
     */
    public void setTreeSize(int treeSize) {
        this.treeSize = treeSize;
    }


    /**
     * set test data size
     * @return
     */
    public int getTestSize() {
        return testSize;
    }

    /**
     * get test data size
     * @param testSize
     */
    public void setTestSize(int testSize) {
        this.testSize = testSize;
    }

    /**
     * get sample data for testing
     * @return sampleTestData
     */
    public String[] getSampleTestData() {
        return sampleTestData;
    }

    /**
     * set sample data for testing
     * @param sampleTestData sample data for testing
     */
    public void setSampleTestData(String[] sampleTestData) {
        this.sampleTestData = sampleTestData;
    }
}