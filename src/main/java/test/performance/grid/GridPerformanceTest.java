package test.performance.grid;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("test.performance.grid.MyAppWidgetset")
public class GridPerformanceTest extends UI {

    private static final long serialVersionUID = 1L;

    private Grid gridWith20Columns;
    private Grid gridWith50Columns;
    private Grid gridWith100Columns;
    private Grid gridWith150Columns;

    private VerticalLayout layout;
    private Label lblGrid;
    private TabSheet tabsheet;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        layout = new VerticalLayout();
        lblGrid = new Label("Vaadin Grid Performance");
        tabsheet = new TabSheet();

        gridWith20Columns = new Grid();
        gridWith20Columns.setWidth("100%");
        gridWith20Columns.setCaption("Grid With 20 Columns");
        prepareColumnsForGrid(gridWith20Columns, 20);
        fillDataInGrid(gridWith20Columns, 20, 100);

        gridWith50Columns = new Grid();
        gridWith50Columns.setWidth("100%");
        gridWith50Columns.setCaption("Grid With 50 Columns");
        prepareColumnsForGrid(gridWith50Columns, 50);
        fillDataInGrid(gridWith50Columns, 50, 100);

        gridWith100Columns = new Grid();
        gridWith100Columns.setWidth("100%");
        gridWith100Columns.setCaption("Grid With 100 Columns");
        prepareColumnsForGrid(gridWith100Columns, 100);
        fillDataInGrid(gridWith100Columns, 100, 100);

        gridWith150Columns = new Grid();
        gridWith150Columns.setWidth("100%");
        gridWith150Columns.setCaption("Grid With 150 Columns");
        prepareColumnsForGrid(gridWith150Columns, 150);
        fillDataInGrid(gridWith150Columns, 150, 100);

        tabsheet.addComponents(gridWith20Columns, gridWith50Columns, gridWith100Columns,
                gridWith150Columns);

        layout.addComponents(lblGrid, tabsheet);
        layout.setMargin(true);
        layout.setSpacing(true);

        setContent(layout);
    }

    private void prepareColumnsForGrid(Grid grid, int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            grid.addColumn("Column " + (i + 1));
        }
    }

    private void fillDataInGrid(Grid grid, int columnCount, int rowCount) {
        for (int i = 0; i < rowCount; i++) {
            ArrayList<String> list = new ArrayList<>(columnCount);
            for (int j = 0; j < columnCount; j++) {
                list.add("Row " + (i + 1) + " Column " + (j + 1));
            }
            grid.addRow(list.toArray());
        }
    }

    @WebServlet(urlPatterns = "/*", name = "GridPerformanceTestServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = GridPerformanceTest.class, productionMode = false)
    public static class GridPerformanceTestServlet extends VaadinServlet {

        private static final long serialVersionUID = 1L;
    }

}
