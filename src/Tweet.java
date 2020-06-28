import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.lang.Thread;
import java.io.*;

public class Tweet extends Thread {

	static File twp = new File("****");
	static String pw;

	public static String getPass() {
		try {
			RandomAccessFile raTwp = new RandomAccessFile(twp, "rw");
			raTwp.seek(0);
			pw = raTwp.readLine();
			raTwp.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return pw;
	}

	public void run() {
		try {

			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(isr);
			System.out.println("Tweet:");
			String tweet = br.readLine();

			System.setProperty("webdriver.chrome.driver", "****\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.get("https://twitter.com/login");
			sleep(1000);
			WebElement username;
			username = driver.findElement(By.xpath(
					"//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/form/div/div[1]/label/div/div[2]/div/input")); // js-username-field
			username.sendKeys("****");
			WebElement password;
			password = driver.findElement(By.xpath(
					"//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/form/div/div[2]/label/div/div[2]/div/input"));
			password.sendKeys(getPass());
			sleep(1000);
			WebElement button;
			button = driver.findElement(
					By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/form/div/div[3]/div/div"));
			button.click();
			sleep(1000);
			button = driver.findElement(
					By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/header/div/div/div/div/div[3]/a/div"));
			button.click();
			sleep(1000);

			WebElement textbox = driver.findElement(By.xpath(
					"//*[@id=\"react-root\"]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[3]/div/div/div/div[1]/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div[1]/div/div/div/div[2]/div/div/div/div"));
			textbox.sendKeys(tweet);
			sleep(1000);
			button = driver.findElement(By.xpath(
					"//*[@id=\"react-root\"]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[3]/div/div/div/div[1]/div/div/div/div/div[2]/div[2]/div/div/div[2]/div/div/span/span"));
			button.click();
			sleep(1000);
			driver.close();
			
		} catch (InterruptedException | IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Tweet t = new Tweet();
		t.start();
	}
}
