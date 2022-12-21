package leo.task.func;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTeste {
	
	public WebDriver accesarAplicacao () {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
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
		public void naodeveSalvarTarefaSemData() {
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
		public void naoDeveSalvarTarefaComDataPassada() {
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

