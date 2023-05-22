import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OrderTestsNegative {
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
    }
    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
    @Test
    void shouldTestNameInvaulible() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Usaeva Elina");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).clear();
        driver.findElement(By.className("button__text")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        Assertions.assertEquals(expected,actual);

    }
    @Test
    void shouldTestPhoneInvaulible() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Усаева Элина");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7 (999) 670 58 91");
        driver.findElement(By.className("button__text")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        Assertions.assertEquals(expected,actual);

    }
    @Test
    void shouldTestEmpty() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.className("button__text")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.className("input__sub")).getText();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void shouldTestPhoneEmpty() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Усаева Элина");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).clear();
        driver.findElement(By.className("button__text")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText();
        Assertions.assertEquals(expected,actual);
    }
    @Test
    void shouldTestNameEmpty() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79996705891");
        driver.findElement(By.className("button__text")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText();
        Assertions.assertEquals(expected,actual);
    }
}
