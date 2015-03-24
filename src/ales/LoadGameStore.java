/* LoadGameStore.java */
/*  <creaturestore,
    <creature,int,int,int,int,int,int,int,int,int,int,int,int,/>,
/   />,
*/ 

/*
 * Created on Nov 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ales;

import ales.creature.Barrier;
import ales.creature.Creature;
import ales.creature.QuadDidium;
import ales.creature.Roundamecium;
import ales.creature.TestCreature;
import ales.creature.YellowAlgae;
import java.awt.*;
import java.util.*;
import java.io.*;
import java.io.FileReader;
import java.io.StreamTokenizer;
import java.lang.*;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import ales.structure.CreatureStore;
/**
 * @author Ede
 *
 */

public class LoadGameStore 
{
  private static Reader reader;
  private static FileReader inFile;
  private static PrintWriter outFile;
  private static boolean inFileOpen = false;
  private static boolean outFileOpen = false;
  private static String outputFileName = "savedGame.dat";
  private static String inputFileName = "defaultGame.dat";
  private static StreamTokenizer strTok;
  private static String creatureCheck ="creature";         
  private static String tokenEndCheck = "/>";
  private static String pointCheck ="java.awt.Point";         
  private static ALES_Game ales;
  
	public static void main(String[] args) 
	{	
		GameManager gm = new GameManager(ales);
		CreatureStore cs = new CreatureStore(gm);
		loadGame( cs, inputFileName);
		
		saveGame(cs, outputFileName);

	} // end of Main
                                               
	public static void loadGame( CreatureStore cs, String s)
	{
	  try {
			inFile = new FileReader(s); 
		   reader = new BufferedReader(inFile); 
	  } catch ( FileNotFoundException e ) 
	 	   {
	      System.out.println("loadGame: "+e); 
			System.exit(50);
	  		}
		strTok = new StreamTokenizer(reader); 
		String Token;
		boolean stop = true;
		try {
			strTok.whitespaceChars( 0x2C, 0x2E ); 
			strTok.whitespaceChars( 0x3C, 0x3C ); 
			strTok.whitespaceChars( 0x3E, 0x3E); 
//			strTok.whitespaceChars( 0x61, 0x91); 
			strTok.nextToken();
			while (strTok.ttype != strTok.TT_EOF)
			{
				if (strTok.ttype == strTok.TT_WORD)
				{
			  		Token = strTok.sval ;
					if (Token.compareTo(creatureCheck) == 0)
					{
		            extractCreature( cs, strTok); 
//						System.out.println("Token end="+Token);
					} else {
						System.out.println("Unexpected Token="+Token);
						System.exit (999);
					}
				} else {
					if (strTok.ttype == strTok.TT_NUMBER)
					{
				  		System.out.println("Error: numeric value found in loadGame "+strTok.nval);	 
					} else 
					{
						System.out.println("Token not known "+strTok.ttype);
					}
				}
				strTok.nextToken();

			}
			System.out.println("EOF detected, load complete");
			} catch ( IOException e ) 
		   {
		      System.out.println(e); 
				System.exit(250);
		   }
	  }
	  
  private static void extractCreature( CreatureStore cs, StreamTokenizer strTok )
  {
		int type;
		int xp;
		int yp;
		int age;
		int health;
		int energy;
		int state;
		int lastMoveTime;
		Point location;
		Point offset;
		Point speed;
		Creature c;
		try {
	   strTok.nextToken();
/*		if (strTok.ttype == strTok.TT_WORD) 			{
				System.out.println("Word=" + strTok.sval);
			} else if (strTok.ttype == strTok.TT_NUMBER) {
				System.out.println("Number=" + ((int) strTok.nval));
			} else {
				System.out.println("Type=" + strTok.ttype);
	   }
*/
      if (strTok.ttype != strTok.TT_NUMBER) System.exit(200);
		type = (int) strTok.nval;		/* type */
		strTok.nextToken();
      if (strTok.ttype != strTok.TT_NUMBER) System.exit(201);
		age = (int) strTok.nval;
		strTok.nextToken();
      if (strTok.ttype != strTok.TT_NUMBER) System.exit(202);
		health = (int) strTok.nval;
		strTok.nextToken();
      if (strTok.ttype != strTok.TT_NUMBER) System.exit(203);
		energy = (int) strTok.nval;
		strTok.nextToken();
      if (strTok.ttype != strTok.TT_NUMBER) System.exit(204);
		state = (int) strTok.nval;
		strTok.nextToken();
      if (strTok.ttype != strTok.TT_NUMBER) System.exit(205);
		lastMoveTime= (int) strTok.nval;
		strTok.nextToken();
      if (strTok.ttype != strTok.TT_NUMBER) System.exit(206);
		xp = (int) strTok.nval;
		strTok.nextToken();
      if (strTok.ttype != strTok.TT_NUMBER) System.exit(206);
		yp = (int) strTok.nval;
		strTok.nextToken();
		location = new Point(xp,yp);
      if (strTok.ttype != strTok.TT_NUMBER) System.exit(207);
		xp = (int) strTok.nval;
		strTok.nextToken();
      if (strTok.ttype != strTok.TT_NUMBER) System.exit(207);
		yp = (int) strTok.nval;
		offset = new Point(xp,yp);
		strTok.nextToken();
      if (strTok.ttype != strTok.TT_NUMBER) System.exit(2081);
		xp = (int) strTok.nval;
		strTok.nextToken();
      if (strTok.ttype != strTok.TT_NUMBER) {
			System.exit(2082);
			}
		yp = (int) strTok.nval;
		speed = new Point(xp,yp);
		if (type == 0)
			c = new TestCreature(location,cs);
		else if (type == 3) 
		{
			c = new QuadDidium(location,cs);
			System.out.println("Carnivore added");
		}			
		else if (type == 2)
		{
			c = new Roundamecium(location,cs);
			System.out.println("Herbivore added");
		}			
		else if (type == 1) 
		{
			c = new YellowAlgae(location,cs);
			System.out.println("Plant added");
		}			
		else if (type == 9)
		{
			c = new Barrier(location,cs);
			System.out.println("Barrier added");
		}
		else 	
		{
			c = new TestCreature(location,cs);
			System.out.println("TestCreature added");
		}			
		c.rebuildCreature(type,age,health,energy,state,lastMoveTime,location,offset,speed);
	   cs.addVertex(c);
		} catch ( IOException e ) 
	   {
	      System.out.println(e); 
			System.exit(250);
	   }
	}

	public static void saveGame(CreatureStore cs, String s ) 
	{
		boolean result = true;
		Creature c;
		String  str;
		  
	try {
	   rewrite(s);
		if (!outFileOpen) {
		    System.out.println("output file not created - "+outputFileName);
			 System.exit(300);
			 }
			
		while (cs.hasNext()) 
		{
			c = (Creature) cs.getNextVertex();
			str = c.printCreature();
			System.out.println(str);
		   outFile.println(str);
		}
		close();
	} catch ( IOException e ) 
	{
		System.out.println("xxxxxxxxxxxxxxx"+e); 
		System.exit(80);
	}
	} 	
	
	public static void rewrite(String s) throws IOException
  // reset file for writing 
    {
    if (inFileOpen) inFile.close();
    if (outFileOpen) outFile.close();
    outFile = new PrintWriter(new FileWriter(s));
    outFileOpen = true;
  }

  public static void close() throws IOException
  // Closes house info file
  {
    if (inFileOpen) inFile.close();
    if (outFileOpen) outFile.close();
    inFileOpen = false;
    outFileOpen = false;
  }
}

