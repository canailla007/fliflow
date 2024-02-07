package application;


	

	import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	
	public class Selenium {
		
		public Selenium() {}
		
	public Resultados construyeResultados(String duplicidad,ArrayList<String> codesmells,ArrayList<String> bugs , ArrayList<String> vulnerabilities) {
		String totalcodesmells = String.valueOf(codesmells.size());
		CodeSmells codeSmelss=new CodeSmells(totalcodesmells,codesmells);
		String totalbugs = String.valueOf(bugs.size());
		String totalvulnerabilities = String.valueOf(vulnerabilities.size());
		
		
		Resultados resultado=new Resultados(totalbugs,duplicidad,totalvulnerabilities,codeSmelss);
		return resultado;
		
		
	}

	 public Proyecto Ejecutar(Proyecto proyecto) throws InterruptedException {
		
        Proyecto nuevo=new Proyecto();
        int total=0;
    	ArrayList<String> CodeSmells=new ArrayList<String>() ;
    	ArrayList<String> Vulnerabilities=new ArrayList<String>() ;
    	ArrayList<String> Bugs=new ArrayList<String>() ;
    	
    	
    	System.setProperty("webdriver.chrome.driver", "C:\\chrome\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
    	WebDriver driver  = new ChromeDriver();
		driver.navigate().to("http://127.0.0.1:8005/project/information?id=prueba");
		Thread.sleep(3000); 
		driver.navigate().to("http://localhost:8005/dashboard?id=prueba");
		Thread.sleep(3000); 
		
		WebElement login = driver.findElement(By.xpath("//*[@id=\"login\"]"));
		login.click();
		login.sendKeys("admin");
		Thread.sleep(3000); 
		WebElement password = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		password.click();
		password.sendKeys("admin1234");
		Thread.sleep(3000); 
		WebElement button = driver.findElement(By.xpath("//button[contains(.,'Log in')]"));
		button.click();
		Thread.sleep(3000); 
		WebElement element5 = driver.findElement(By.xpath("//a[contains(@href, '/project/issues?inNewCodePeriod=false&resolved=false&types=BUG&id=prueba')]"));
		String bug = element5.getText();
		System.out.println("bug :: "+bug);
		WebElement element6 = driver.findElement(By.xpath("//a[contains(@href, '/project/issues?inNewCodePeriod=false&resolved=false&types=CODE_SMELL&id=prueba')]"));
		String codesmells = element6.getText();
		System.out.println("CODE_SMELL :: "+codesmells);
		WebElement element7 = driver.findElement(By.xpath("//a[contains(@href, '/project/issues?inNewCodePeriod=false&resolved=false&types=VULNERABILITY&id=prueba')]"));
		String vulnerability = element7.getText();
		System.out.println("Vulnerabilities :: "+vulnerability);
		WebElement element8 = driver.findElement(By.xpath("//a[contains(@href, '/component_measures?id=prueba&metric=duplicated_lines_density&view=list')]"));
		String duplications = element8.getText();
		System.out.println("Duplications :: "+duplications);
		Thread.sleep(3000); 
		
		driver.navigate().to("http://localhost:8005/project/issues?inNewCodePeriod=false&resolved=false&types=CODE_SMELL&id=prueba");
		Thread.sleep(3000); 
		  // Find all the links that contain "code_smell"
        List<WebElement> codeSmellLinks = driver.findElements(By.xpath("//a[contains(@href, 'CODE_SMELL')]"));
        System.out.println("************");
        System.out.println("Code_SMELLS");
        System.out.println("************");
        // Print the text of each link
        for (WebElement link : codeSmellLinks) {
        	String nuevolink=link.getText();
            System.out.println(nuevolink);
            CodeSmells.add(nuevolink);
        }
        
        driver.navigate().to("http://localhost:8005/project/issues?inNewCodePeriod=false&resolved=false&types=BUG&id=prueba");
		Thread.sleep(3000); 
		  // Find all the links that contain "code_smell"
        List<WebElement> bugs= driver.findElements(By.xpath("//a[contains(@href, 'BUG')]"));
        System.out.println("************");
        System.out.println("BUGS");
        System.out.println("************");
        // Print the text of each link
        for (WebElement link : bugs) {
        	String nuevolink=link.getText();
            System.out.println(nuevolink);
            Bugs.add(nuevolink);
        }
        driver.navigate().to("http://localhost:8005/project/issues?inNewCodePeriod=false&resolved=false&types=VULNERABILITY&id=prueba");
     		Thread.sleep(3000); 
     		  // Find all the links that contain "code_smell"
             List<WebElement> vulnerabilities= driver.findElements(By.xpath("//a[contains(@href, 'VULNERABILITY')]"));
             System.out.println("************");
             System.out.println("VULNERABILITIES");
             System.out.println("************");
             // Print the text of each link
             for (WebElement link : vulnerabilities) {
             	String nuevolink=link.getText();
                 System.out.println(nuevolink);
                 Vulnerabilities.add(nuevolink);
             }
        
		
		
		
		driver.quit();
		
		Resultados resultados=construyeResultados(duplications,CodeSmells,Vulnerabilities,Bugs);
		nuevo=proyecto;
		nuevo.setResultados(resultados);
		proyecto=nuevo;
		return proyecto;
	}
	}


