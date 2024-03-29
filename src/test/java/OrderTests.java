import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OrderTests {
    private WebDriver driver;
    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999/");
    }
    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
    @Test
    void shouldTestPositivResult() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Усаева Элина");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79996705891");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void shouldTestPositivResultDoubleSurname() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Бобрищев-Пушкин Александр");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79916705891");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void shouldTestPositivResultDoubleName() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Николаева Анна-Мария");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79916705891");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void shouldTestPositivResultLongSurname() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("СЕМИПОПОЛОВИГЕРОВЕРСАЛОФЕДИРАКОВСКИЙ Апполинария");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79916705891");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void shouldTestPositivResultShortSurnameName() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Ия Яи");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79916705891");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button__text")).click();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        Assertions.assertEquals(expected,actual);
    }

}
