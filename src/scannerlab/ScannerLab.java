package scannerlab;
import org.graphstream.graph.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import java.util.ArrayList;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
enum state {START, INNUM, INID, INASSIGN, DONE, INCOMMENT};
class node{
    ArrayList<node> list = new ArrayList<>() ;
    String name;
    String type;
    Graph graph = new SingleGraph("Tutorial 1");
    String cs;
    public node(String n,String type)
    {     
        this.name = n;
        this.type=type;
        
    }
    public void print()
    {
      //  reverse();
        for(int i=0;i<list.size();i++)
        {
         //  String s = graph.getAttribute(" ",list.get(i));
            System.out.println("node" + i +list.get(i).name);
        }
    }
    public void reverse()
    {
        node temp= new node("","");
        int y;
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).type.equals("op"))
            {
                temp.name= list.get(i).name;
                temp.type= list.get(i).type;

                y = (i+1);
               
                list.get(i).name= list.get(y).name;
                list.get(i).type= list.get(y).type;
list.get(y).name= temp.name;
list.get(y).type= temp.type;

                
            }
        }
    }
    public void draw(){
//Viewer viewer = graph.display();
//viewer.disableAutoLayout();


     int index =0;
      
     int indexrepeat=0;
     int i=0;
     graph.addNode(list.get(i).name+i);

        for(i=1;i<list.size();i++)
        {
              if(list.get(i).name.equals("if")||("ifstmt").equals(cs))
                {
                if (!(list.get(i).name.equals("then"))) {
                    cs = "ifstmt";
                    if(list.get(i).type.equals("read")||list.get(i).name.equals("write")||list.get(i).name.equals("repeat")||list.get(i).type.equals("assign"))
               {
                   cs = "";
               }
                    else
                    {
                    graph.addNode(list.get(i).name+i);
                    
                    if(list.get(i).name.equals("if"))
                    {
                        graph.addEdge(list.get(index).name+list.get(i).name,list.get(index).name+index,list.get(i).name+i);

                        index=i;
                    }
                     if(list.get(i).type.equals("op")||list.get(i-1).type.equals("op"))
                     {
                      graph.addEdge(list.get(i).name+i+list.get(i-1).name+(i-1),list.get(i).name+i,list.get(i-1).name+(i-1));
                     }
                
                
                  if(list.get(i).type.equals("op"))
                    {
                     graph.addEdge(list.get(index).name+index+list.get(i).name+i,list.get(index).name+index,list.get(i).name+i);

                    }
                       
                    }
                } 
                
               
                }
       if(list.get(i).type.equals("read"))
         {
        cs = "read";
           if(list.get(i).type.equals("assign")||list.get(i).name.equals("write")||list.get(i).name.equals("repeat")||list.get(i).name.equals("if")||list.get(i).name.equals("until"))
               {
                   cs = "";
               }
           else{
             
                  graph.addNode(list.get(i).name+i); 
                  if(list.get(i).type.equals("read"))
                    {
                        graph.addEdge(list.get(i).name+list.get(index).name,list.get(i).name+i,list.get(index).name+index);

                        index = i;
                    }
                  
         }
         }
         if(list.get(i).type.equals("assign")||("assignstmt").equals(cs))
         {
                    cs = "assignstmt";
                    
                    if(list.get(i).type.equals("read")||list.get(i).name.equals("write")||list.get(i).name.equals("repeat")||list.get(i).name.equals("if")||list.get(i).name.equals("until"))
               {
                   cs = "";
               }
                    else
                    {
                    graph.addNode(list.get(i).name+i);
                    if(list.get(i).type.equals("assign"))
                    {
                        graph.addEdge(list.get(i).name+list.get(index).name,list.get(i).name+i,list.get(index).name+index);

                        index = i;
                    }
                    else if(list.get(i).type.equals("op")||list.get(i-1).type.equals("op"))
                     {
                         if(list.get(i).type.equals("op"))
                         {
                                                  graph.addEdge(list.get(index).name+list.get(i).name,list.get(index).name+index,list.get(i).name+i);

                         }
                      graph.addEdge(list.get(i).name+i+list.get(i-1).name+(i-1),list.get(i).name+i,list.get(i-1).name+(i-1));
                     }
                    else if(!(list.get(i+1).type.equals("op")))
                    {
                       graph.addEdge(list.get(i).name+i+list.get(i-1).name+(i-1),list.get(i).name+i,list.get(i-1).name+(i-1));

                    }
                    }
                  
                    
         }
       if(list.get(i).name.equals("repeat")||("repeatstmt").equals(cs)||list.get(i).name.equals("until"))
           {
              cs= "repeatstmt";
                   if(list.get(i).type.equals("read")||list.get(i).name.equals("write")||list.get(i).type.equals("assign")||list.get(i).name.equals("if"))
                   {
                   cs = "";
                    }
                else
                   {
                     if(list.get(i).name.equals("until"))
                     {
                     }
                     else if(list.get(i-1).name.equals("until"))
                      {
                                               graph.addNode(list.get(i).name+i); 
                       }
                     else if(list.get(i-2).name.equals("until"))
                     {
                                          graph.addNode(list.get(i).name+i); 

                          if(list.get(i).type.equals("op")||list.get(i-1).type.equals("op"))
                                {
                                    if(list.get(i).type.equals("op"))
                                    {
                                                               graph.addEdge(list.get(i).name+list.get(indexrepeat).name,list.get(i).name+i,list.get(indexrepeat).name+indexrepeat);
                                    }

                                 graph.addEdge(list.get(i).name+i+list.get(i-1).name+(i-1),list.get(i).name+i,list.get(i-1).name+(i-1));
                                }
                     }
                     else if(list.get(i).name.equals("repeat"))
                      {
                                               graph.addNode(list.get(i).name+i); 

                      graph.addEdge(list.get(i).name+list.get(index).name,list.get(i).name+i,list.get(index).name+index);
                       index = i;
                       indexrepeat = i;
                    }
                     else
                     {
                         if(!list.get(i).name.equals("until"))
                                                  graph.addNode(list.get(i).name+i); 

                            graph.addEdge(list.get(i).name+list.get(i-1).name,list.get(i).name+i,list.get(i-1).name+(i-1));
                     }
                          
                   
         }
           }
       if(list.get(i).name.equals("write")||("writestmt").equals(cs))
         {
             cs= "writestmt";
                   if(list.get(i).type.equals("read")||list.get(i).name.equals("repeat")||list.get(i).type.equals("assign")||list.get(i).name.equals("if"))
                   {
                   cs = "";
                    }
                else
                   {
                     graph.addNode(list.get(i).name+i);  
                     if(list.get(i).name.equals("write"))
                      {
                       graph.addEdge(list.get(i).name+list.get(indexrepeat).name,list.get(i).name+i,list.get(indexrepeat).name+indexrepeat);
                       index = i;
                       }
                     else
                     {
                        graph.addEdge(list.get(i).name+list.get(i-1).name,list.get(i).name+i,list.get(i-1).name+(i-1));

                     }
             
         }  
         }
       
        }       
        int count=0;
        //int cordy=y;
          for (Node n : graph) {
              Node nn= graph.getNode(n.getId());
              if(n.getId().substring(0, 2).equals("re")||n.getId().substring(0, 2).equals("wr")||n.getId().substring(0, 2).equals("if")||n.getId().substring(0, 2).equals("as"))
              {
                  n.addAttribute("ui.style","shape:box;" +
                          "size: 55px, 55px;" +
                          "fill-mode: plain;" +
                          "fill-color: white;" +
                          "stroke-mode: plain;" +
                          "stroke-color: black;");
                 // n.addAttribute("xy", x, y);
                 //  x-=2.75;
              }
              else
              {
                  n.addAttribute("ui.style","shape:circle;" +
                          "size: 55px, 55px;" +
                          "fill-mode: plain;" +
                          "fill-color: white;" +
                          "stroke-mode: plain;" +
                          "stroke-color: black;");
                 // n.addAttribute("xy", x, y);
                // x+=1;
                 //y+=1;         
              }
              count++;
              if(count>9)
              {
               n.addAttribute("ui.label", n.getId().substring(0,n.getId().length()-2));
               if(n.getId().substring(0, 2).equals("re")||n.getId().substring(0, 2).equals("wr")||n.getId().substring(0, 2).equals("if")||n.getId().substring(0, 2).equals("as"))
              {
                  n.addAttribute("ui.style","shape:box;" +
                          "size: 55px, 55px;" +
                          "fill-mode: plain;" +
                          "fill-color: white;" +
                          "stroke-mode: plain;" +
                          "stroke-color: black;");
                 // n.addAttribute("xy", x, y);
                 //  x-=2.75;
                  // y+=0.5;  
              }
              else
              {
                  n.addAttribute("ui.style","shape:circle;" +
                          "size: 55px, 55px;" +
                          "fill-mode: plain;" +
                          "fill-color: white;" +
                          "stroke-mode: plain;" +
                          "stroke-color: black;");
                //  n.addAttribute("xy", x, y);
                // x+=1;
                                  // y+=1;
                  
              }
                 // n.addAttribute("xy", x, y);
                //  x+=1;
                                  //  y+=1;

               //n.addAttribute("layout.frozen");
              }
              else
              {
                   
               
                 // n.addAttribute("xy", x, y);
                 // x+=1;
                              //      y+=1;

                 
              n.addAttribute("ui.label", n.getId().substring(0,n.getId().length()-1));
            // n.addAttribute("xyz", x, y);
            //   n.addAttribute("layout.frozen");

              }
          }
                graph.addAttribute("ui.quality");
               graph.addAttribute("ui.antialias");

        
                      System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

                                         graph.display();

         } 
}
class parserr {
            String cs = " ";
public static char []Symbol={'+','-','*','/','=','<','(',')',';'};
public static String []reserved={"if","then","else","repeat","end","until","read","write"};
public static String [] type = new String [100];
public static int j=0;
public static int counter=0;
public static String[] a = new String[100];
 java.io.File file = new java.io.File("myFile.txt");
 java.io.PrintWriter output = new java.io.PrintWriter(file);   
 node x = new node("node x","don't care");
   public parserr(String [] s,String[] type) throws IOException
    {
        parserr.a=s;
        parserr.type=type;
        parser();
    }
   public void parser() throws IOException
    {
            program();
            output.close();
            x.print();
            x.draw();
    }
   public void program() throws IOException
    {
        //System.out.println("program is found");
        output.println( "program is found");
        
        stmt_sequence();
    }
   public void stmt_sequence() throws IOException
   {
      //System.out.println("Statement seq is found");
      output.println( "Statement seq is found");
      
      statement(); 
       while(a[j].equals(";"))
       {
     
            boolean bsemi = match(";",a[j]);
            if(bsemi)
            {
                    output.println( "; is found");
            } else
            {
               output.println( "Error: expected ; ");

            }

            statement(); 
           if(a[j]==null)
           {
               break;
           }
       } 
       }
   public boolean match(String expected,String token)
   {
       
   boolean flag;
       
   
       if(expected.equalsIgnoreCase(token)||expected.equalsIgnoreCase(" "+token)||expected.equalsIgnoreCase(token+" "))
       {
           flag=true;
           //if(token.equalsIgnoreCase(";")==false)
           //{
           j++;
           //}
       }
       else
       {
           flag=false;
         //  file.write("Expected;"+expected);
       }
       
       return flag;
   }
   public void statement() throws IOException
   {
           output.println("Statement is found");

            if("if".equalsIgnoreCase(a[j]))
           {
               cs= "ifstmt";
               if_stmt();
           }
           else if("repeat".equalsIgnoreCase(a[j]))
           {
               repeat_stmt();
           }
           else if("ID".equalsIgnoreCase(type[j]))
           {
               assign_stmt();
           }
           else if(a[j].equalsIgnoreCase("read"))
           {
               read_stmt();
           }
           else if(a[j].equalsIgnoreCase("write"))
           {
               write_stmt();
           }
           
   }
   public void exp()
   {
       //System.out.println("exp is found");
       output.println("exp is found");
       simple_exp();
       if(match("<",a[j])||(match("=",a[j])))
       {
          output.println( "comparaison is found");
          output.println(a[j-1]);
            
           node ncomp = new node("op"+"("+a[j-1]+")","op");
           x.list.add(ncomp);
           simple_exp();
       }
   }
   public  void if_stmt() throws IOException, IOException
   {
       boolean bif =match("if",a[j]);
       if(bif)
            {
               output.println( "if is found");
            }
       node nif = new node("if","if");
       x.list.add(nif);
      // nif.list.add(nnu);
               
      // System.out.println("if statement");
       output.println("if statement");
       exp();
       boolean bthen =match("then",a[j]);
       if(bthen)
            {
                    output.println( "then is found");
            }
       //node nthen = new node("then");
      // x.list.add(nthen);
               
       stmt_sequence();
       if(match("else",a[j]))
       {
            output.println( "else is found");

          // node nelse = new node("else");
         //  x.list.add(nelse);
           stmt_sequence();
       }
       boolean bend =match("end",a[j]);
       if (bend)
       {
        output.println( "end is found");
       }

      // node nend = new node("end");
      // x.list.add(nend);
   }
   public void repeat_stmt() throws IOException, IOException
   {
        output.println("repeat statement");

       
       boolean brepeat =match("repeat",a[j]);
       if (brepeat)
       {
        output.println( "repeat is found");
       }
       node nrepeat = new node("repeat","repeat");
       x.list.add(nrepeat);
             // System.out.println("repeat statement");
       stmt_sequence();
       boolean buntil = match("until",a[j]);
       if (buntil)
       {
        output.println( "until is found");
       }
       
       node nuntil = new node("until","until");
       x.list.add(nuntil);
       exp();
              //j++;

       
   }
   public void assign_stmt()
   {
       output.println("assign statement");   

       boolean bidd = match("ID",type[j]);
       if (bidd)
       {
        output.println(" ID is found");
       }
      // node nid = new node("id"+"("+a[j-1]+")","id");
      // x.list.add(nid);
       boolean beq=match(":=",a[j]);
       if (beq)
       {
        output.println(" := is found");
       }
       node nassign = new node("assign" +" "+a[j-2],"assign");
       x.list.add(nassign);
       exp();
   }
   public  void read_stmt()
   {
        output.println("Read statement");

       boolean bread =match("read",a[j]);

       if (bread)
       {
        output.println(" read is found");
       }
       
       boolean bid =match("ID",type[j]);
              node nread = new node("read"+" "+a[j-1],"read");

       if (bid)
       {
        output.println(" ID is found");
       }
       else
       {
         output.println(" Error : Expected Id ");

       }
       x.list.add(nread);
       counter++;
   }
   public  void write_stmt()
   {
       output.println("write statement");

       boolean bwrite = match("write",a[j]);
       if (bwrite)
       {
        output.println(" write is found");
       }
       node nwrite = new node("write","write");
       x.list.add(nwrite);
       exp();
             // System.out.println("write statement");

   }
   public void simple_exp()
      {
          //System.out.println("simple exp is found");
          output.println("simple exp is found");
          term();
          while(match("+",a[j])||match("-",a[j]))       
          {
             output.println(" add op is found");
              output.println(a[j-1]);


              node ncomp1 = new node("op"+ "("+a[j-1]+ ")","op");
              x.list.add(ncomp1);
              term();
          }
      }
   public void term()
      {
          output.println(" term is found");

          factor();
          while(match("*",a[j])||match("/",a[j]))
          {
                   output.println("mul op is found");
                   output.println(a[j-1]);

              node ncomp2 = new node("op"+ "("+a[j-1]+ ")","op");
              x.list.add(ncomp2);
              factor();
          }
      }
   public void factor()
      {
        output.println(" factor is found");

          if(match("(",a[j]))
          {
              
              output.println(" ( is found");
       
              node nleftbrac = new node("(","brac");
              x.list.add(nleftbrac);
              exp();
              boolean bbrac =match(")",a[j]);  
              if (bbrac)
       {
        output.println(" ) is found");
       }
              else{
                          output.println(" Error expected ) ");

              }
              node nrightbrac = new node(")","brac");
              x.list.add(nrightbrac);
              
          }
           else if(match("number",type[j]))
          {
               output.println("number is found");

              node nnumber = new node("number"+"("+a[j-1]+")","number");
              x.list.add(nnumber);

          }
           else if(match("ID",type[j]))
          {
              output.println("ID is found");

              node nid = new node("id"+ "("+a[j-1]+ ")","id");
              x.list.add(nid);
              
          }   
      }
}
public class ScannerLab {
public  static String [] a = new String [100];
public static char []Symbol={'+','-','*','/','=','<','(',')',';'};
public static String []reserved={"if","then","else","repeat","end","until","read","write"};
public static String [] type = new String [100];
public static int j=0;
public static boolean isSymbol(char c)
{
    boolean count =false;
    for(int x=0;x<Symbol.length;x++)
           {
               if(c==Symbol[x])
               {
                   count = true;
               }
           }
return count;
}
public static boolean isReserved(String Arr)
{
    boolean f = false;
    for(int y=0;y<reserved.length;y++)
                    
                    if(Arr.equalsIgnoreCase(" "+reserved[y])||Arr.equalsIgnoreCase(reserved[y])||Arr.equalsIgnoreCase(reserved[y]+""))
                    {
                        f = true;
                    } 
    return f;
                     
}
public static String[] getToken(String s)throws exception, FileNotFoundException, IOException{
    state MYstate = state.START;
    String c_token ="";
    //String [] a = new String [10000];
    //int j=0;
    int i;
    for(i=0;i<s.length();i++)
    {   
         //start state
        if(MYstate==state.START)
         {      
             if(isDigit(s.charAt(i)))
                    {
                        MYstate = state.INNUM;
                    }
            if(isLetter(s.charAt(i)))
            {
                MYstate = state.INID;
            }
            else if(s.charAt(i)==':')
            {
                MYstate = state.INASSIGN;
            }
            else if(isSymbol(s.charAt(i)))
            {
                MYstate = state.START;
                type[j]= "Symbol";
                c_token = c_token + s.charAt(i);
                ScannerLab.a[j]=c_token;
                c_token = "";
                j++;
            }
             if(s.charAt(i)==':')
            {
                MYstate = state.INASSIGN;
            }
                
             else if(s.charAt(i)=='{')
                //comment
            {
                MYstate = state.INCOMMENT;
            }  
           }
    if(MYstate==state.INNUM)//digit , other
    {
        if(isDigit(s.charAt(i)))//digit
                    {
                        c_token = c_token+s.charAt(i);            
                    }
        else if(s.charAt(i)==' ')
            
        {
            MYstate = state.DONE;
            MYstate = state.START;
            type[j]="Number";

            ScannerLab.a[j]=c_token;
            c_token = "";
            j++;
        }
        else
        {
            MYstate = state.DONE;
            MYstate = state.START;
            type[j]="Number";
            ScannerLab.a[j]=c_token;
            c_token = "";
            j++;
            i--;
        }


    }
    if(MYstate==state.INID)//letter , other
    {
        if(isLetter(s.charAt(i)))
            {
                c_token = c_token+s.charAt(i);            
            }
        else if(s.charAt(i)==' ')
            
        {
            MYstate = state.START;
            if(isReserved(c_token))
            {
                type[j]="Reserved";
                ScannerLab.a[j]=c_token;
            c_token = "";
            j++;
            }
            else
            {
            type[j]="ID";
            ScannerLab.a[j]=c_token;
            c_token = "";
            j++;
            }
            
        }
    else
        {
            MYstate = state.DONE;
            MYstate = state.START;
            type[j]="ID";
            ScannerLab.a[j]=c_token;
            c_token = "";
            j++;
            i--;
        }  
    }
    if(MYstate==state.INASSIGN)//= , other
    {
        if(s.charAt(i)=='=')
        {
          c_token = c_token+s.charAt(i);
          ScannerLab.a[j]=c_token;
          type[j]= "Assignment";
          c_token = "";
          j++;
          MYstate = state.START;
 
        }
        //other
        else
        {
        c_token = c_token+s.charAt(i);            

        }
    }
    if(MYstate==state.INCOMMENT)//other , }
    {
        if(s.charAt(i)=='}')
        {
                     // c_token = c_token + s.charAt(i);   
                      MYstate = state.START;         
                    //type[j]="Comment";
                     // a[j]=c_token;
                     c_token = "";
                      //j++;
        }
        else
        {
                      c_token = c_token+s.charAt(i);            

        }
    }
    }
    return ScannerLab.a;
    }  
public static void main(String[] args) throws FileNotFoundException, IOException, exception {
         File file;

        file  = new File("tiny_sample_code.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String sCurrentLine;

        String Mine="" ;
        while ((sCurrentLine = br.readLine()) != null) {
            System.out.println(sCurrentLine);
            Mine = Mine+sCurrentLine;    
        }
        String [] res = {};
        res =getToken(Mine);
           for (int k=0;k<res.length;k++) {
           if(res[k]!=null)
            {
               System.out.println(res[k] +" "+ type[k] ); 
            }   
           }
           parserr p = new parserr(res,type);
         //  Graph graph = new SingleGraph("Graph 1");
           //graph.addNode("A");
           //graph.addNode("B");
		//graph.addNode("C");
		//graph.addEdge("AB", "A", "B");
		//graph.addEdge("BC", "B", "C");
		//graph.addEdge("CA", "C", "A");

		//graph.display();
          
    }
    }
    