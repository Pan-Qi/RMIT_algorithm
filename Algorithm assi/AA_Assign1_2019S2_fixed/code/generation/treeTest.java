import java.util.ArrayList;

public class treeTest {



    public treeTest() {

    }

    /**
     * test by different size generated data set
     * @param treeSize size of tree
     * @param testSize size of testing data
     */
    public void testByGeneratedData(int treeSize, int testSize){


        System.out.println("This is the testing for seqTree and linkTree with new generated various size testing data.");
        System.out.println("The tree size is: " + treeSize + ". The testing data size is: " + testSize);

        ArrayList<Long> seqAddTimeList = new ArrayList<>();
        ArrayList<Long> seqFindNodeTimeList = new ArrayList<>();
        ArrayList<Long> seqFindNodeParentTimeList = new ArrayList<>();
        ArrayList<Long> seqFindNodeChildrenTimeList = new ArrayList<>();
        ArrayList<Long> seqInorderTraverseList = new ArrayList<>();
        ArrayList<Long> seqPreorderTraverseList = new ArrayList<>();
        ArrayList<Long> seqPostorderTraverseList = new ArrayList<>();


        ArrayList<Long> linkAddTimeList = new ArrayList<>();
        ArrayList<Long> linkFindNodeTimeList = new ArrayList<>();
        ArrayList<Long> linkFindNodeParentTimeList = new ArrayList<>();
        ArrayList<Long> linkFindNodeChildrenTimeList = new ArrayList<>();
        ArrayList<Long> linkInorderTraverseList = new ArrayList<>();
        ArrayList<Long> linkPreorderTraverseList = new ArrayList<>();
        ArrayList<Long> linkPostorderTraverseList = new ArrayList<>();
        long time;

        for (int i = 0; i < 10; i++) {
            testFunction test = new testFunction(treeSize,testSize);
            test.generateTreeData();
            test.generateTestData();

            //seqTree add/split time
            time = test.seqAddTime();
            seqAddTimeList.add(time);

            //linkTree add/split time
            time = test.linkAddTime();
            linkAddTimeList.add(time);

            //seqTree finding node time
            time = test.findSeqTreeNodeTime();
            seqFindNodeTimeList.add(time);

            //linkTree finding node time
            time = test.findLinkTreeNodeTime();
            linkFindNodeTimeList.add(time);

            //seqTree finding node parent time
            time = test.findSeqTreeNodeParentTime();
            seqFindNodeParentTimeList.add(time);

            //linkTree finding node parent time
            time = test.findLinkTreeNodeParentTime();
            linkFindNodeParentTimeList.add(time);

            //seqTree finding node children time
            time = test.findSeqTreeNodeChildrenTime();
            seqFindNodeChildrenTimeList.add(time);

            //linkTree finding node children time
            time = test.findLinkTreeNodeChildrenTime();
            linkFindNodeChildrenTimeList.add(time);

            //seqTree inOrder traverse time
            time = test.seqInorderTraverseTime();
            seqInorderTraverseList.add(time);

            //linkTree inOrder traverse time
            time = test.linkInorderTraverseTime();
            linkInorderTraverseList.add(time);

            //seqTree preorder traverse time
            time = test.seqPreorderTraverseTime();
            seqPreorderTraverseList.add(time);

            //linkTree preorder traverse time
            time = test.linkPreorderTraverseTime();
            linkPreorderTraverseList.add(time);

            //seqTree postorder traverse time
            time = test.seqPostorderTraverseTime();
            seqPostorderTraverseList.add(time);

            //linkTree postorder traverse time
            time = test.linkPostorderTraverseTime();
            linkPostorderTraverseList.add(time);

        }

        testFunction test = new testFunction(0,0);
        System.out.println();
        System.out.println("SeqTree result ==========================================================");
        System.out.println("Ten times testing for seqTree add/split result is:");
        System.out.println(seqAddTimeList);
        System.out.println("Average add/split time is: " + test.averageCalculator(seqAddTimeList));

        System.out.println("Ten times running time for seqTree finding 1000 nodes:");
        System.out.println(seqFindNodeTimeList);
        System.out.println("Average seqTree find node time is: " + test.averageCalculator(seqFindNodeTimeList));

        System.out.println("Ten times running time for seqTree finding 1000 nodes' parent:");
        System.out.println(seqFindNodeParentTimeList);
        System.out.println("Average seqTree find node's parent time is: " + test.averageCalculator(seqFindNodeParentTimeList));

        System.out.println("Ten times running time for seqTree finding 1000 nodes' children:");
        System.out.println(seqFindNodeChildrenTimeList);
        System.out.println("Average seqTree find node's children time is: " + test.averageCalculator(seqFindNodeChildrenTimeList));

        System.out.println("Ten times running time for seqTree inorder traverse is: ");
        System.out.println(seqInorderTraverseList);
        System.out.println("Average seqTree inoreder traverse time is:" + test.averageCalculator(seqInorderTraverseList));

        System.out.println("Ten times running time for srqTree preorder traverse is: ");
        System.out.println(seqPreorderTraverseList);
        System.out.println("Average seqTree preorder traverse time is:" + test.averageCalculator(seqPreorderTraverseList));

        System.out.println("Ten times running time for seqTree postorder traverse is: ");
        System.out.println(seqPostorderTraverseList);
        System.out.println("Average seqTree postorder traverse time is: " + test.averageCalculator(seqPostorderTraverseList));


        System.out.println("LinkTree result ===============================================");
        System.out.println("Ten times testing for linkTree add/split result is:");
        System.out.println(linkAddTimeList);
        System.out.println("Average linkTree add/split time is: " + test.averageCalculator(linkAddTimeList));

        System.out.println("Ten times running time for linkTree finding 1000 nodes:");
        System.out.println(linkFindNodeTimeList);
        System.out.println("Average linkTree find node time is: " + test.averageCalculator(linkFindNodeTimeList));

        System.out.println("Ten times running time for linkTree finding 1000 nodes' parent:");
        System.out.println(linkFindNodeParentTimeList);
        System.out.println("Average linkTree find node's parent time is: " + test.averageCalculator(linkFindNodeParentTimeList));

        System.out.println("Ten times running time for linkTree finding 1000 nodes' children:");
        System.out.println(linkFindNodeChildrenTimeList);
        System.out.println("Average linkTree find node's children time is: " + test.averageCalculator(linkFindNodeChildrenTimeList));

        System.out.println("Ten times running time for linkTree inorder traverse is: ");
        System.out.println(linkInorderTraverseList);
        System.out.println("Average linkTree inoreder traverse time is:" + test.averageCalculator(linkInorderTraverseList));

        System.out.println("Ten times running time for linkTree preorder traverse is: ");
        System.out.println(linkPreorderTraverseList);
        System.out.println("Average linkTree preorder traverse time is:" + test.averageCalculator(linkPreorderTraverseList));

        System.out.println("Ten times running time for linkTree postorder traverse is: ");
        System.out.println(linkPostorderTraverseList);
        System.out.println("Average linkTree postorder traverse time is: " + test.averageCalculator(linkPostorderTraverseList));
    }

    public void givenDataInformation(){
        testFunction test = new testFunction(0,0);
        test.informationOfCombined();
    }

    //time testing for seqtree with given data set
    public void seqTreeTestWithGivenData() {
        testFunction test = new testFunction(1000, 1000);
        test.setTreeSize(3999);
        ArrayList<Long> addTimeList = new ArrayList<>();
        ArrayList<Long> findNodeTimeList = new ArrayList<>();
        ArrayList<Long> findNodeParentTimeList = new ArrayList<>();
        ArrayList<Long> findNodeChildrenTimeList = new ArrayList<>();
        ArrayList<Long> inorderTraverseList = new ArrayList<>();
        ArrayList<Long> preorderTraverseList = new ArrayList<>();
        ArrayList<Long> postorderTraverseList = new ArrayList<>();

        long time;

        for (int i = 0; i < 10; i++) {
            test = new testFunction(1000, 1000);//construct a new test object
            test.generateTreeDataByGivenData();//generate tree data by given txt file
            test.generateTestData();//sample testing data

            //seqTree add/split time
            time = test.seqAddTime();
            addTimeList.add(time);

            //finding node time
            time = test.findSeqTreeNodeTime();
            findNodeTimeList.add(time);

            //finding node parent time
            time = test.findSeqTreeNodeParentTime();
            findNodeParentTimeList.add(time);

            //finding node children time
            time = test.findSeqTreeNodeChildrenTime();
            findNodeChildrenTimeList.add(time);

            //seqTree inOrder traverse time
            time = test.seqInorderTraverseTime();
            inorderTraverseList.add(time);

            //seqTree preorder traverse time
            time = test.seqPreorderTraverseTime();
            preorderTraverseList.add(time);

            //seqTree postorder traverse time
            time = test.seqPostorderTraverseTime();
            postorderTraverseList.add(time);

        }
        System.out.println(" ");

        System.out.println("This is the result of seqTree with given BSP_combined.txt data");

        System.out.println("Ten times testing for seqTree add/split result is:");
        System.out.println(addTimeList);
        System.out.println("Average add/split time is: " + test.averageCalculator(addTimeList));

        System.out.println("Ten times running time for finding 1000 nodes:");
        System.out.println(findNodeTimeList);
        System.out.println("Average seqTree find node time is: " + test.averageCalculator(findNodeTimeList));

        System.out.println("Ten times running time for finding 1000 nodes' parent:");
        System.out.println(findNodeParentTimeList);
        System.out.println("Average seqTree find node's parent time is: " + test.averageCalculator(findNodeParentTimeList));

        System.out.println("Ten times running time for finding 1000 nodes' children:");
        System.out.println(findNodeChildrenTimeList);
        System.out.println("Average seqTree find node's children time is: " + test.averageCalculator(findNodeChildrenTimeList));

        System.out.println("Ten times running time for inorder traverse is: ");
        System.out.println(inorderTraverseList);
        System.out.println("Average seqTree inoreder traverse time is:" + test.averageCalculator(inorderTraverseList));

        System.out.println("Ten times running time for preorder traverse is: ");
        System.out.println(preorderTraverseList);
        System.out.println("Average seqTree preorder traverse time is:" + test.averageCalculator(preorderTraverseList));

        System.out.println("Ten times running time for postorder traverse is: ");
        System.out.println(postorderTraverseList);
        System.out.println("Average seqTree postorder traverse time is: " + test.averageCalculator(postorderTraverseList));
    }


    //time testing for linkTree with given data set
    public void linkTreeTestWithGivenData() {
        testFunction test = new testFunction(1000, 1000);
        test.setTreeSize(3999);
        ArrayList<Long> addTimeList = new ArrayList<>();
        ArrayList<Long> findNodeTimeList = new ArrayList<>();
        ArrayList<Long> findNodeParentTimeList = new ArrayList<>();
        ArrayList<Long> findNodeChildrenTimeList = new ArrayList<>();
        ArrayList<Long> inorderTraverseList = new ArrayList<>();
        ArrayList<Long> preorderTraverseList = new ArrayList<>();
        ArrayList<Long> postorderTraverseList = new ArrayList<>();

        long time;

        for (int i = 0; i < 10; i++) {
            //construct a new test object
            test = new testFunction(1000, 1000);
            test.generateTreeDataByGivenData();

            //linkTree add/split time
            time = test.linkAddTime();
            addTimeList.add(time);

            //sample testing data
            test.generateTestData();

            //finding node time
            time = test.findLinkTreeNodeTime();
            findNodeTimeList.add(time);

            //finding node parent time
            time = test.findLinkTreeNodeParentTime();
            findNodeParentTimeList.add(time);

            //finding node children time
            time = test.findLinkTreeNodeChildrenTime();
            findNodeChildrenTimeList.add(time);

            //linkTree inOrder traverse time
            time = test.linkInorderTraverseTime();
            inorderTraverseList.add(time);

            //linkTree preorder traverse time
            time = test.linkPreorderTraverseTime();
            preorderTraverseList.add(time);

            //linkTree postorder traverse time
            time = test.linkPostorderTraverseTime();
            postorderTraverseList.add(time);

        }
        System.out.println(" ");

        System.out.println("This is the result of linkTree with given BSP_combined.txt data");

        System.out.println("Ten times testing for linkTree add/split result is:");
        System.out.println(addTimeList);
        System.out.println("Average add/split time is: " + test.averageCalculator(addTimeList));

        System.out.println("Ten times running time for finding 1000 nodes:");
        System.out.println(findNodeTimeList);
        System.out.println("Average linkTree find node time is: " + test.averageCalculator(findNodeTimeList));

        System.out.println("Ten times running time for finding 1000 nodes' parent:");
        System.out.println(findNodeParentTimeList);
        System.out.println("Average linkTree find node's parent time is: " + test.averageCalculator(findNodeParentTimeList));

        System.out.println("Ten times running time for finding 1000 nodes' children:");
        System.out.println(findNodeChildrenTimeList);
        System.out.println("Average linkTree find node's children time is: " + test.averageCalculator(findNodeChildrenTimeList));

        System.out.println("Ten times running time for inorder traverse is: ");
        System.out.println(inorderTraverseList);
        System.out.println("Average linkTree inoreder traverse time is:" + test.averageCalculator(inorderTraverseList));

        System.out.println("Ten times running time for preorder traverse is: ");
        System.out.println(preorderTraverseList);
        System.out.println("Average linkTree preorder traverse time is:" + test.averageCalculator(preorderTraverseList));

        System.out.println("Ten times running time for postorder traverse is: ");
        System.out.println(postorderTraverseList);
        System.out.println("Average linkTree postorder traverse time is: " + test.averageCalculator(postorderTraverseList));
    }
}
