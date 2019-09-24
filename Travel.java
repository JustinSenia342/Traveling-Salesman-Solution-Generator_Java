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

//this program solves travelling salesman problem
public class Travel
{
  //Path class (inner class)
  private class Path
  {
    private LinkedList<Integer> list;
    private int cost;

    //constructor of path class
    private Path()
    {
      list = new LinkedList<Integer>();
      cost = 0;
    }

    //Copy constructor
    private Path(Path other)
    {
      list = new LinkedList<Integer>();

      for (int i = 0; i < other.list.size(); i++)
        list.addLast(other.list.get(i));

      cost = other.cost;
    }

    //method adds vertex to path
    private void add(int vertex, int weight)
    {
      list.addLast(vertex);
      cost += weight;
    }

    //Method finds last vertex of path
    private int last()
    {
      return list.getLast();
    }

    //Method finds cost of path
    private int cost()
    {
      return cost;
    }

    //Method finds length of path
    private int size()
    {
      return list.size();
    }

    //Method decides whether path contains a given vertex
    private boolean contains(int vertex)
    {
      for (int i = 0; i < list.size(); i++)
        if (list.get(i) == vertex)
          return true;

      return false;
    }

    //Method displays path and it's cost
    private void display()
    {
      for (int i = 0; i < list.size(); i++)
        System.out.print(list.get(i) + " ");
      System.out.println(": " + cost);
    }
  }

  private int size;
  private int[][] matrix;
  private int initial;
  private PrintWriter pW;
  private statesAtMin = 0;

  //Constructor of Travel class
  public Travel(int vertices, int[][] edges, PrintWriter PWOut)
  {
    size = vertices;
    this.pW = PWOut;

    matrix = new int[size][size];
    for (int i = 0; i < size; i++)
      for (int j = 0; j < size; j++)
        matrix[i][j] = 0;

    for (int i = 0; i < edges.length; i++)
    {
      int u = edges[i][0];
      int v = edges[i][1];
      int weight = edges[i][2];
      matrix[u][v] = weight;
      matrix[v][u] = weight;
    }

    initial = edges[0][0];
  }

  //Method finds shortest cycle
  public void solve()
  {
    LinkedList<Path> openList = new LinkedList<Path>();
    LinkedList<Path> closedList = new LinkedList<Path>();

    Path shortestPath = null;
    int minimumCost = Integer.MAX_VALUE;

    LinkedList<Path> list = new LinkedList<Path>();

    Path path = new Path();
    path.add(initial, 0);
    list.addFirst(path);

    while (!list.isEmpty())
    {
      path = list.removeFirst();

      if (complete(path))
      {
        if (path.cost() < minimumCost)
        {
          minimumCost = path.cost();
          shortestPath = path;
        }
      }
      else
      {
        LinkedList<Path> children = generate(path);

        for (int i = 0; i < children.size(); i++)
          list.addFirst(children.get(i));
      }
    }

    if (shortestPath == null)
      System.out.println("no solution");
    else
      shortestPath.display();
  }

  //Method generates children of path
  private LinkedList<Path> generate(Path path)
  {
    LinkedList<Path> children = new LinkedList<Path>();

    int lastVertex = path.last();

    for (int i = 0; i < size; i++)
    {
      if (matrix[lastVertex][i] != 0)
      {
        if (i == initial)
        {
          if (path.size() == size)
          {
            Path child = new Path(path);

            child.add(i, matrix[lastVertex][i]);

            children.addLast(child);
          }
        }
        else
        {
          if (!path.contains(i))
          {
            Path child = new Path(path);

            child.add(i, matrix[lastVertex][i]);

            children.addLast(child);
          }
        }
      }
    }

    return children;
  }

  //Method decides whether a path is complete
  boolean complete(Path path)
  {
    return path.size() == size + 1;
  }
}
