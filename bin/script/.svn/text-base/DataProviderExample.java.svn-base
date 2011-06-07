package script;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;
import java.util.*;
import com.thoughtworks.selenium.*;
import org.junit.AfterClass;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.annotations.*;
import java.io.File;
import jxl.*; 


public class DataProviderExample extends SeleneseTestCase{
    
    @BeforeClass
    public void setUp() throws Exception {
        SeleniumServer seleniumserver=new SeleniumServer();
        //if seleniumserver.
        seleniumserver.boot();
        seleniumserver.start();
        selenium = new DefaultSelenium("localhost", 4443, "*firefox", "http://www.imdb.com/" );
        setUp("http://www.imdb.com/", "*firefox");
        selenium.open("/");
        selenium.windowMaximize();
        selenium.windowFocus();
    }

    	
    @DataProvider(name = "DP1")
    public Object[][] createData1() throws Exception{
        Object[][] retObjArr=getTableArray("C:/MyEclipseWorkspace/DataDrivenProject/test/resources/data/data1.xls",
                "datapool", "imdbTestData");
        return(retObjArr);
    }
    
    @Test (dataProvider = "DP1")
    public void testDataProviderExample(String movieTitle, 
            String directorName, String moviePlot, String actorName) throws Exception {    
        //enter the movie title 
        selenium.type("xpath=//input[@name='q']", movieTitle);
       // selenium.js_eval("window.location.href");
        selenium.getEval("window.location.href");
        //Thread.sleep(3000);
        //they keep switching the go button to keep the bots away
        if (selenium.isElementPresent("btnG"))
            selenium.click("xpath=//input[@name='btnG']");
        else
        	//selenium.click("xpath=/");
        	selenium.click("xpath=//button[@type='submit']");
        //selenium.attachFile("//input[@id='content_0']", fileServer + "resources.xls");

        	//selenium.click("xpath=/descendant::button[@type='submit']");
            //selenium.clcick("xpath=/descendant::button[@type='submit']");
         
        selenium.waitForPageToLoad("6000");
        //selenium.fireEvent(arg0, arg1);
        //click on the movie title in the search result page
        //assertEquals("on", selenium.getValue("//input[@value='Blue']"));

        
        /*assertTrue(selenium.getValue("//input[@name='birthday']").matches("^[\\s\\S]*$"));
        selenium.check("//input[@value='Classical']");
        assertEquals("on", selenium.getValue("//input[@value='Classical']"));
        selenium.check("//input[@value='Blue']");*/
        
        
        
        selenium.click("xpath=/descendant::a[text()='"+movieTitle+"']");
        selenium.waitForPageToLoad("30000");
        
        //verify director name is present in the movie details page 
        verifyTrue(selenium.isTextPresent(directorName));
        //verify movie plot is present in the movie details page
        //verifyTrue(selenium.isTextPresent(moviePlot));
        //verify movie actor name is present in the movie details page
        //verifyTrue(selenium.isTextPresent(actorName));
        
        
		//selenium.waitForPageToLoad("30000");
		//assertTrue(selenium.isTextPresent("test tool"));
	}
	public String[] getAllCheckboxIds () {
        String script = "var inputId  = new Array();";// Create array in java script.
        script += "var cnt = 0;"; // Counter for check box ids.
        script += "var inputFields  = new Array();"; // Create array in java script.
        script += "inputFields = window.document.getElementsByTagName('input');"; // Collect input elements.
        script += "for(var i=0; i<inputFields.length; i++) {"; // Loop through the collected elements.
        script += "if(inputFields[i].id !=null " +
        "&& inputFields[i].id !='undefined' " +
        "&& inputFields[i].getAttribute('type') == 'checkbox') {"; // If input field is of type check box and input id is not null.
        script += "inputId[cnt]=inputFields[i].id ;" + // Save check box id to inputId array.
        "cnt++;" + // increment the counter.
        "}" + // end of if.
        "}"; // end of for.
        script += "inputId.toString();" ;// Convert array in to string.
        String[] checkboxIds = selenium.getEval(script).split(","); // Split the string.
        return checkboxIds;
}
   
    
    
    @AfterClass
    public void tearDown(){
        selenium.close();
        selenium.stop();
    } 
    
    public String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception{
        String[][] tabArray=null;
        
            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
            Sheet sheet = workbook.getSheet(sheetName); 
            int startRow,startCol, endRow, endCol,ci,cj;
            Cell tableStart=sheet.findCell(tableName);
            startRow=tableStart.getRow();
            
            startCol=tableStart.getColumn();
            System.out.println(startRow);
            System.out.println(startCol);

            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);
            //Cell tabelend= sheet.findCell

            endRow=tableEnd.getRow();
            endCol=tableEnd.getColumn();
            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                    "startCol="+startCol+", endCol="+endCol);
            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
            ci=0;

            for (int i=startRow+1;i<endRow;i++,ci++){
                cj=0;
                for (int j=startCol+1;j<endCol;j++,cj++){
                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
                }
            }
        

        return(tabArray);
    }
    
    
}//end of class

