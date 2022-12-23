package leo.task.func;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTeste {
	
	private Object cap;
	private WebDriver driver;

	public WebDriver accesarAplicacao () throws MalformedURLException {
		WebDriver driver = new ChromeDriver();
		//DesiredCapabilities cap = DesiredCapabilities.chrome();
		//RemoteWebDriver WebDriver = new RemoteWebDriver(new URL("http://192.168.1.107:4444/wd/hub"), cap);
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = accesarAplicacao();
		try {
		//clicar em add todo
		driver.findElement(By.id("add todo")).click();
		
		//escrever descrição
		driver.findElement(By.id("task")).sendKeys("Teste via Selenuim");
		
		//escrever data
		driver.findElement(By.id("task")).sendKeys("10/10/2030");
		
		//clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Succes!", message);
		} finally {
		
		//fechar o browser
		driver.quit();
		}
	}
		
		@Test
		public void naodeveSalvarTarefaSemData() throws MalformedURLException {
			WebDriver driver = accesarAplicacao();
			try {
			//clicar em add todo
			driver.findElement(By.id("add todo")).click();
			
			//escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenuim");	
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
			} finally {
			
			//fechar o browser
			driver.quit();
			}
		}
		
		@Test
		public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
			WebDriver driver = accesarAplicacao();
			try {
			//clicar em add todo
			driver.findElement(By.id("add todo")).click();
			
			//escrever descrição
			driver.findElement(By.id("task")).sendKeys("Teste via Selenuim");
			
			//escrever data passada
			driver.findElement(By.id("task")).sendKeys("10/10/2010");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem do erro
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
			} finally {
			
			//fechar o browser
			driver.quit();
			}
		}	
	}

