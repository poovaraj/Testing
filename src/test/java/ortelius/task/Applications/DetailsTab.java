package ortelius.task.Applications;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.screenplay.Consequence;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.pages.components.HtmlTable;
import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ortelius.utilities.ReusableMethod;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.Dropdown;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;

public class DetailsTab {

    public static Target lstFullDomainName = Target.the("Full Domain Name").located(By.name("fulldomain_val"));
    public static Target txtName = Target.the("Name").located(By.name("name_val"));
    public static Target txtDescription = Target.the("Description").located(By.name("summary_val"));

    public static Target lstChangeRequestDataSource = Target.the("Change Request Data Soruce")
            .located(By.name("bt_datasource_val"));

    public static Target lstPreAction = Target.the("Pre Action").located(By.name("preaction_val"));
    public static Target lstPostAction = Target.the("Post Action").located(By.name("postaction_val"));
    public static Target lstCustomAction = Target.the("Custom Action").located(By.name("customaction_val"));

    public static Target lstSuccessfulDeploymentTemplate = Target.the("Successful Deployment Template")
            .located(By.name("template_val"));

    public static Target lstFailedDeploymentTemplate = Target.the("Failed Deployment Template")
            .located(By.name("fail_template_val"));

    public static By tblDetail = By.id("summ");


    public static Performable openVersionFromWebTable(String version) {

        System.out.println(version);

        HtmlTable table = new HtmlTable(BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).$("#applist"));
        WebElement obj = table.findFirstRowWhere()
                .findElement(By.xpath("//td[text()='Hipster Store;July 4th Sale;4 - Testing']"));
        System.out.println(obj.getText());
        BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).withAction().doubleClick(obj);
        //evaluateJavascript("$('#firstname').click()");

        return  Task.where("{0} Open Specific Version " + version);

    }

    public static Performable clickOnEditButton() {

        return  Task.where("Click on Edit Button ", Click.on(Button.called("Edit")));
    }

    public static Performable changeValues(DataTable dataTable) {

        List<Map<String, String>> details = dataTable.asMaps(String.class, String.class);

        String fullDomainName = details.get(0).get("Full Domain");
        String name = details.get(0).get("Name");
        String description = details.get(0).get("Description");
        String changeRequestDataSource = details.get(0).get("Change Request Data Source");
        String preAction = details.get(0).get("Pre-Action");
        String postAction = details.get(0).get("Post-Action");
        String customAction= details.get(0).get("Custom-Action");
        String successfullDeploymentTemplate = details.get(0).get("Successfull Deployment Template");
        String failedDeploymentTemplate = details.get(0).get("Failed Deployment Template");

        return Task.where("Enter values in Detail tab",

                Check.whether(fullDomainName.isEmpty())
                        .otherwise(SelectFromOptions.byVisibleText(fullDomainName)
                               .from(lstFullDomainName)),

                Check.whether(name.isEmpty())
                        .otherwise(SelectFromOptions.byVisibleText(name)
                                .from(txtName)),

                Check.whether(description.isEmpty())
                        .otherwise(SelectFromOptions.byVisibleText(description)
                                .from(txtDescription)),

                Check.whether(changeRequestDataSource.isEmpty())
                        .otherwise(SelectFromOptions.byVisibleText(changeRequestDataSource)
                                .from(lstChangeRequestDataSource)),

                Check.whether(preAction.isEmpty())
                        .otherwise(SelectFromOptions.byVisibleText(preAction)
                                .from(lstPreAction)),

                Check.whether(postAction.isEmpty())
                        .otherwise(SelectFromOptions.byVisibleText(postAction)
                                .from(lstPostAction)),

                Check.whether(customAction.isEmpty())
                        .otherwise(SelectFromOptions.byVisibleText(customAction)
                                .from(lstCustomAction)),

                Check.whether(successfullDeploymentTemplate.isEmpty())
                        .otherwise(SelectFromOptions.byVisibleText(successfullDeploymentTemplate)
                                .from(lstSuccessfulDeploymentTemplate)),

                Check.whether(failedDeploymentTemplate.isEmpty())
                        .otherwise(SelectFromOptions.byVisibleText(failedDeploymentTemplate)
                                .from(lstFailedDeploymentTemplate))
                );
    }

    public static Performable clickOnSaveButton() {
        return  Task.where("Click on Save Button ", Click.on(Button.called("Save")));
    }

    public static Performable verifyChangeValues(DataTable dataTable) {

        List<Map<String, String>> details = dataTable.asMaps(String.class, String.class);

        String fullDomainName = details.get(0).get("Full Domain");
        String name = details.get(0).get("Name");
        String description = details.get(0).get("Description");
        String changeRequestDataSource = details.get(0).get("Change Request Data Source");
        String preAction = details.get(0).get("Pre-Action");
        String postAction = details.get(0).get("Post-Action");
        String customAction= details.get(0).get("Custom-Action");
        String successfullDeploymentTemplate = details.get(0).get("Successfull Deployment Template");
        String failedDeploymentTemplate = details.get(0).get("Failed Deployment Template");

        HtmlTable table = new HtmlTable(BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).$(tblDetail));
        Map<Object,String> colValue = table.getRows().get(1);
        System.out.println(colValue.values());

        return  Task.where("Verify values in Detail tab ");
    }


}