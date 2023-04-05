package uk.co.automationtesting.elements;

import static base.ExtentTestManager.startTest;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.ExtentTestManager;
import base.Hooks;
import pageObjects.homepage.ActionsPage;
import pageObjects.homepage.Homepage;

@Listeners(resources.Listeners.class)
public class ActionsTest extends Hooks {
	final String java_script_drag_drop = "var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe"
			+ "ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun"
			+ "ction(format,data){this.items[format]=data;this.types.append(for"
			+ "mat);},getData:function(format){return this.items[format];},clea"
			+ "rData:function(format){}};var emit=function(event,target){var ev"
			+ "t=document.createEvent('Event');evt.initEvent(event,true,false);"
			+ "evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('"
			+ "dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);emit(" + "'drop',tgt);emit('dragend',src);";

	public ActionsTest() throws IOException {
		super();
	}

	@Test(groups = { "Regression", "Smoke" })
	public void doubleClickAction(Method method) throws IOException, InterruptedException {
		startTest("Double click action", method.getName());
		ExtentTestManager.log("Starting actions...");
		Homepage home = new Homepage();
		ActionsPage actions = new ActionsPage();
		Actions act = new Actions(getDriver());
		home.getActionsLink().click();
		act.doubleClick(actions.getDoubleClickArea()).perform();
		waitForElementText(actions.getDoubleClickArea(), "Well Done!", 5);
		ExtentTestManager.pass("Double click test passed successfully!");
	}

	@Test(groups = { "Regression" })
	public void shiftClickAction(Method method) throws IOException, InterruptedException {
		startTest("Shift click action", method.getName());
		Homepage home = new Homepage();
		ActionsPage actions = new ActionsPage();
		Actions act = new Actions(getDriver());
		home.getActionsLink().click();
		act.keyDown(Keys.SHIFT).click(actions.getShiftHoldButton()).keyUp(Keys.SHIFT).perform();
		String alertText = waitForAlert(5);
		Assert.assertEquals(alertText, "The SHIFT key was pressed!");
		ExtentTestManager.pass("Shift click test passed successfully!");
	}

	@Test(groups = { "Regression" })
	public void holdClick(Method method) throws IOException, InterruptedException {
		startTest("Click and hold action", method.getName());
		Homepage home = new Homepage();
		ActionsPage actions = new ActionsPage();
		Actions act = new Actions(getDriver());
		home.getActionsLink().click();
		act.clickAndHold(actions.getClickHoldArea()).perform();
		waitForElementText(actions.getClickHoldArea(), "Keep holding down!", 4);
		act.release(actions.getClickHoldArea()).perform();
		waitForElementText(actions.getClickHoldArea(), "No, don't let go :(", 4);
		ExtentTestManager.pass("Hold click test passed successfully!");
	}

	@Test(groups = { "Regression" })
	public void dragAndDropTest(Method method) throws IOException, InterruptedException {
		startTest("Drag and drop action", method.getName());
		Homepage home = new Homepage();
		ActionsPage actions = new ActionsPage();
		home.getActionsLink().click();
		// Actions act = new Actions(getDriver());
		// act.dragAndDrop(actions.getDraggableElement(),
		// actions.getDropTarget2()).build().perform();
		// hack that I don't like, but works

		((JavascriptExecutor) getDriver()).executeScript(java_script_drag_drop, actions.getDraggableElement(),
				actions.getDropTarget2());
		// check if drop area 2 has an element
		waitForElementText(actions.getDraggingInfoText(), "The p element was dropped into an accepted rectangle", 1);
		Assert.assertFalse(actions.getDropTarget1().getText().equals("Drag me!"));
		Assert.assertTrue(actions.getDropTarget2().getText().equals("Drag me!"));

		((JavascriptExecutor) getDriver()).executeScript(java_script_drag_drop, actions.getDraggableElement(),
				actions.getDropTarget1());
		// check if drop area 1 has an element
		waitForElementText(actions.getDraggingInfoText(), "The p element was dropped into an accepted rectangle", 1);
		Assert.assertTrue(actions.getDropTarget1().getText().equals("Drag me!"));
		Assert.assertFalse(actions.getDropTarget2().getText().equals("Drag me!"));
		ExtentTestManager.pass("Drag and drop test passed successfully!");
	}
}
