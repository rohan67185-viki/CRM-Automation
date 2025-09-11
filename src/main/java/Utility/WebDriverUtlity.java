package Utility;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class WebDriverUtlity {
	
	WebDriver driver;
	Actions act;
	
//Constructor
	public WebDriverUtlity(WebDriver driver) {
		this.driver = driver;
		this.act = new Actions(driver);
		
	}

//Hover
	public void hover(WebElement element) {
		act.moveToElement(element).build().perform();
		
	}
	public void hover(int xOffset, int yOffset) {
		act.moveByOffset(xOffset, yOffset).build().perform();
	}
	
	public void hover(WebElement element, int xOffset, int yOffset) {
		act.moveToElement(element, xOffset, yOffset).build().perform();
	}
//Click Method
	public void click() {
		act.click().build().perform();
		
	}
	public void click(WebElement element) {
		act.click().build().perform();
	}
//Right Click
	public void rightClick() {
		act.contextClick().build().perform();
		
	}
	public void rightClick(WebElement element) {
		act.contextClick(element).build().perform();
		
	}
//Double Click
	public void doubleClick() {
		act.doubleClick().build().perform();
	}
	public void doubleClick(WebElement element) {
		act.doubleClick(element).build().perform();

	}
//Click And Hold
	public void clickAndHold() {
		act.clickAndHold().build().perform();
		
	}
	public void clickAndHold(WebElement element) {
		act.clickAndHold(element).build().perform();
		
	}
//Release
	public void release() {
		act.release().build().perform();
		
	}
	public void release(WebElement element) {
		act.release(element).build().perform();
		
	}
//Drag And Drop
	public void dragAndDrop(WebElement draggable, WebElement dropable) {
		act.dragAndDrop(draggable, dropable).build().perform();
		
	}
	public void dragAndDrop(WebElement draggable, int xOffset, int yOffset) {
		act.dragAndDropBy(draggable, xOffset, yOffset).build().perform();
		
	}
//Send keys
	public void sendKeys(String keys) {
		act.sendKeys(keys).build().perform();
		
	}
	public void sendKeys(WebElement element,String keys) {
		act.sendKeys(keys).build().perform();
	}
//KeyDown
	public void keyDown(Keys key) {
		act.keyDown(key).build().perform();
		
	}
//Key Up
	public void keyUp(Keys key) {
		act.keyUp(key).build().perform();
		
	}
//Scroll
	public void scroll(int deltaX, int deltaY) {
		act.scrollByAmount(deltaX, deltaY).build().perform();
	}
	public void scroll(WebElement element) {
		act.scrollToElement(element).build().perform();
		
		
	}

}
