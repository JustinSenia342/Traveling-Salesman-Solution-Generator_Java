/*
 * Name: Justin Senia
 * E-Number: E00851822
 * Date: 10/6/2017
 * Class: COSC 461
 * Project: #1
 */
import java.util.LinkedList;
import java.io.*;
import java.util.*;

public class TravelTester
{

    //Main method for testing
    public static void main(String[] args) throws IOException
    {
      //creating buffered reader for getting user input
      java.io.BufferedReader keyIn = new BufferedReader(new InputStreamReader(System.in));

      System.out.println("*****************************************");
      System.out.println("*      Travelling Salesman program      *");
      System.out.println("*****************************************");
      System.out.println("*  Please enter in a filename to start  *");
      System.out.println("* or type quit to terminate the program *");
      System.out.println("*****************************************");


      while (true)
      {
        System.out.print("Please make your entry now: ");
        String userIn = "";

        userIn = keyIn.readLine();

        if (userIn.equalsIgnoreCase("quit"))
          break;
        else{
              try
              {
                //PATH filePath = QueensTester.class.getResource(userIn);
                String currentDir = System.getProperty("user.dir");
                File fIn = new File(currentDir + '\\' + userIn);
                Scanner scanIn = new Scanner(fIn);

                File fOut = new File("output_" + userIn);
                PrintWriter PWOut = new PrintWriter(fOut, "UTF-8");

                int numOfVerts = scanIn.nextInt();
                int bRows = scanIn.nextInt();
                int bCols = 3;

                int[][] edges = new int[bRows][bCols];

                for (int i = 0; i < bRows; i++)
                {
                  for (int j = 0; j < bCols; j++)
                  {
                    edges[i][j] = scanIn.nextInt();
                    System.out.println(" " + initialBoard[i][j]);
                  }
                  System.out.println(""); //used to see if arrays transferred correctly
                }

                System.out.println("Output for " + userIn);
                System.out.println("Rows: " + bRows + "  Cols: " + bCols);

                PWOut.println("Output for " + userIn);
                PWOut.println("Rows: " + bRows + "  Cols: " + bCols);

                Travel t = new Travel(numOfVerts, edges, PWOut);
                t.timedSolve(true);

                t = new Travel(numOfVerts, edges, PWOut);
                t.timedSolve(false);

                //q.toStringQ();
                //System.out.println(fIn);
                //test case
                //Queens q = new Queens(8, 8, PWOut);
                //q.verifyData();
                //q.testBoard(bRows, bCols);

              //  Random rnd = new Random(342342);
              //  for (int i = 0; i < 100; i++){
              //    System.out.println(rnd.nextInt(2));
              //  }

                scanIn.close();
                PWOut.close();
              }
              catch (IOException e)
              {
                ;
              }
            }

        //int boardSize = 8;
        //int boardRows = 10;

        //solve queens problem
        //Queens q = new Queens(boardSize);
        //q.solve();

        //fIn.close();
      }



    }
}
