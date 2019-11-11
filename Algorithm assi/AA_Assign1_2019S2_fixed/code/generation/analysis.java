
public class analysis {

    public static void main(String [] args){


        treeTest test = new treeTest();

        //basic information of BSP_combined.txt data
        test.givenDataInformation();

        //test the seq tree with given data
        test.seqTreeTestWithGivenData();

//        //test linked tree with given data
        test.linkTreeTestWithGivenData();


        //test  both trees in different tree size
        test.testByGeneratedData(100,1000);

        test.testByGeneratedData(1000,1000);
//
//        test.testByGeneratedData(10000,1000);
//
//        test.testByGeneratedData(100000,1000);
//
//        test.testByGeneratedData(1000000,1000);
//
//        test.testByGeneratedData(10000000,1000);
//
//        test.testByGeneratedData(9999999,1000);
    }

}


