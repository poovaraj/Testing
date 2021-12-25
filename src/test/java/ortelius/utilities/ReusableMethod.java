package ortelius.utilities;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ReusableMethod {

    public static String getEnvironmentValue(String key)
    {
        EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
        return EnvironmentSpecificConfiguration.from(variables).getProperty(key);
    }

    public static Performable jsEnterValue(By locator, String value)
    {
        Performable obj = new Performable() {
            @Override
            public <T extends Actor> void performAs(T actor) {
                  Performable  performable = (Performable) BrowseTheWeb.as(actor)
                            .evaluateJavascript("arguments[0].value='" + value + "';"
                                    , BrowseTheWeb.as(actor).$(locator));
            }
        };
       return obj;
    }

    public static Performable jsEnterValue(WebElement element, String value)
    {
        Performable obj = new Performable() {
            @Override
            public <T extends Actor> void performAs(T actor) {
                Performable  performable = (Performable) BrowseTheWeb.as(actor)
                        .evaluateJavascript("arguments[0].value='" + value + "';"
                                ,element);
            }
        };
        return obj;
    }

    public static Performable jsSelectByVisibleText(By locator, String value) {
        Performable obj = new Performable() {
            @Override
            public <T extends Actor> void performAs(T actor) {

                    WebElementFacade select = BrowseTheWeb.as(actor).$(locator);
                    Performable performable = (Performable) BrowseTheWeb.as(actor)
                            .evaluateJavascript(
                                    "var select = arguments[0]; "
                                            + "for(var i = 0; i < select.options.length; i++)"
                                            + "{ if(select.options[i].text == arguments[1])"
                                            + "{ select.options[i].selected = true; } }"
                                    , select, value);

            }
        };
        return obj;
    }

    public static Performable clickOnAllElement(By locator)
    {
        Performable obj = new Performable() {
            @Override
            public <T extends Actor> void performAs(T actor) {
                List<WebElementFacade> elementFacades = BrowseTheWeb.as(actor).$$(locator);
                // elementFacades.stream().forEach(e -> JavaScriptClick.on(e));
                // BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click();", BrowseTheWeb.as(actor).$$(locator).get(0));
                for (int i=0; i<elementFacades.size()-1;i++)
                {
                    BrowseTheWeb.as(actor).evaluateJavascript("arguments[0].click();", BrowseTheWeb.as(actor).$$(locator).get(i));
                }
            }
        };
        return obj;
    }

}
