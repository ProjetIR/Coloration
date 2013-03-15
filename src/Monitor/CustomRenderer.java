package Monitor;

import java.awt.Color;
import java.awt.Paint;

import org.jfree.chart.renderer.category.BarRenderer;

class CustomRenderer extends BarRenderer {

    /** The colors. */
    private Color[] colors;

    /**
     * Creates a new renderer.
     *
     * @param colors  the colors.
     */
    public CustomRenderer( Color[] colors) {
        this.colors = colors;
    }

    /**
     * Returns the paint for an item.  Overrides the default behaviour inherited from
     * AbstractSeriesRenderer.
     *
     * @param row  the series.
     * @param column  the category.
     *
     * @return The item color.
     */
    public Paint getItemPaint(final int row, final int column) {
        return this.colors[column % this.colors.length];
    }
}
