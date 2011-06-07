package script;

import static org.testng.AssertJUnit.*;
import java.io.File;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.openqa.selenium.server.SeleniumServer;

import org.testng.annotations.*;
import com.thoughtworks.selenium.*;
import org.junit.AfterClass;



public class Google extends SeleneseTestCase {

 //private Selenium selenium;

 @BeforeClass
 public void setUp() throws Exception {
     SeleniumServer seleniumserver=new SeleniumServer();
     //seleniumserver.boot();
     //seleniumserver.start();
     setUp("http://www.google.co.uk/", "*firefox");
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
 @Test(dataProvider = "DP1")
 @Parameters({"leonel","leonel ndi"})
 public void testDataProviderExample(String movieTitle,
            String directorName, String moviePlot, String actorName) throws Exception {
  try {
   //selenium.type("name=q", "leonel");
   selenium.type("xpath=//input[@name='q']", movieTitle);
   //selenium.click("name=btnG");
   selenium.click("xpath=//input[@name='btnG']");
   selenium.waitForPageToLoad("6000");
   assertTrue(selenium.isTextPresent(moviePlot));
   Thread.sleep(4000);
  } catch (SeleniumException e) {
   fail(e.getMessage());
  }
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
}

