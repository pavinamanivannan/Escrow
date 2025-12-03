1. Project Structure Check
   /FlipkartAutomation
 ├─ src/
 │   ├─ test/
 │   │   └─ java/
 │   │       └─ flipkart/
 │   │           └─ Script_FlipkartProject.java
 └─ Screenshots/
     └─ result.png
2. Requirements

   Requirement     Version                     
 --------------  --------------------------- 
 Java            8 or higher ✅               
 Maven           Latest ✅                    
 TestNG          Latest ✅                    
 Chrome Browser  Latest ✅                    
 ChromeDriver    Must match Chrome version ✅ 

3. Add Libraries
   <dependencies>
    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.9.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Selenium -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.14.0</version>
    </dependency>
</dependencies>

4. Running the Program in Eclipse
   Run from the main method (if your script has one), use Run As → Java Application.
   Screenshots will be saved in /Screenshots/result.png if your code specifies that path.
