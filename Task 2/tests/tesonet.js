const {By,Key,Builder} = require("selenium-webdriver");
require("chromedriver");
let carrers = By.linkText("Career");
let items = By.xpath("//div[@class='career-lever career-lever-v3']/section[2]/div/div[2]//*[contains(@class,'col-lg-3')]");

async function tesonetTest(){
    let driver = await new Builder().forBrowser("chrome").build();
    driver.manage().window().maximize();
    await driver.get("https://www.tesonet.com");
    await driver.findElement(carrers).click();
    console.log("Available catogories: " + 
    await driver.findElements(items).then(elements => elements.length));
    driver.quit();
}

tesonetTest();